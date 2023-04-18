package com.frogniche.nichesmobs.entity.bone_grunter;

import com.frogniche.nichesmobs.NichesMobs;
import com.frogniche.nichesmobs.entity.savager.SavagerGuardEntity;
import com.frogniche.nichesmobs.entity.sp_medic.SPMedic;
import com.frogniche.nichesmobs.entity.spores_chief.SporesChiefEntity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
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

public class BoneGrunter extends Monster implements IAnimatable {

    public static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(SporesChiefEntity.class, EntityDataSerializers.INT);

    public static AttributeSupplier createAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.6d)
                .add(Attributes.ARMOR, 9)
                .add(Attributes.ATTACK_DAMAGE, 10)
                .build();
    }

    protected AnimationFactory factory = new AnimationFactory(this);

    public BoneGrunter(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
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
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grunter.walk"));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    private PlayState attackPredicate(AnimationEvent<SavagerGuardEntity> event) {
        if (this.swinging && event.getController().getAnimationState() == AnimationState.Stopped) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grunter.attack", false));
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
        BASTION(NichesMobs.modLoc("textures/entity/bastion_grunter.png")),
        BONE(NichesMobs.modLoc("textures/entity/bone_grunter.png")),
        WARPED(NichesMobs.modLoc("textures/entity/warped_grunter.png"));

        private final ResourceLocation texture;

        Type(ResourceLocation texture) {
            this.texture = texture;
        }

        public ResourceLocation getTexture() {
            return texture;
        }
    }
}
