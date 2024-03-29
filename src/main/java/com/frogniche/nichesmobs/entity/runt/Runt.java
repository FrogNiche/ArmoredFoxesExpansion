package com.frogniche.nichesmobs.entity.runt;

import com.frogniche.nichesmobs.NichesMobs;
import com.frogniche.nichesmobs.entity.RunawayFromPlayerGoal;
import com.frogniche.nichesmobs.entity.savager.SavagerGuardEntity;
import com.frogniche.nichesmobs.entity.sp_medic.SPMedic;
import com.frogniche.nichesmobs.entity.spores_chief.SporesChiefEntity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Runt extends PathfinderMob implements IAnimatable {

    public static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(SporesChiefEntity.class, EntityDataSerializers.INT);

    public static AttributeSupplier createAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.6d)
                .add(Attributes.ARMOR, 9)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .build();
    }

    protected AnimationFactory factory = new AnimationFactory(this);
    private boolean attackAnimation;

    public Runt(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6f, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, true));

        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 10f));

        this.goalSelector.addGoal(10, new RandomStrollGoal(this, 0.5f, 10));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
    }

    protected PlayState predicate(AnimationEvent<SPMedic> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.runt.WALK"));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent<SavagerGuardEntity> event) {
        if (this.swinging && event.getController().getAnimationState() == AnimationState.Stopped) {
            event.getController().markNeedsReload();
            if (attackAnimation)
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.runt.ATTACK", false));
            else
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.runt.ATTACK2", false));
            attackAnimation = !attackAnimation;
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller1", 0, this::predicate));
        data.addAnimationController(new AnimationController(this, "controller2", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TYPE, getInitialType().ordinal());
    }

    public void setTextureType(Type t) {
        this.entityData.set(TYPE, t.ordinal());
    }

    public Type getTextureType() {
        return Type.values()[entityData.get(TYPE)];
    }

    protected Type getInitialType() {
        return Type.values()[this.random.nextInt(Type.values().length)];
    }

    public static enum Type {
        BONE(NichesMobs.modLoc("textures/entity/bone_runt.png")),
        BASTION(NichesMobs.modLoc("textures/entity/bastion_runt.png")),
        WARPED(NichesMobs.modLoc("textures/entity/warped_runt.png"));

        private final ResourceLocation texture;

        Type(ResourceLocation texture) {
            this.texture = texture;
        }

        public ResourceLocation getTexture() {
            return texture;
        }
    }
}
