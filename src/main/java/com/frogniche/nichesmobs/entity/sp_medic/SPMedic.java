package com.frogniche.nichesmobs.entity.sp_medic;

import com.frogniche.nichesmobs.NichesMobs;
import com.frogniche.nichesmobs.entity.EntityInit;
import com.frogniche.nichesmobs.entity.NBTUtils;
import com.frogniche.nichesmobs.entity.RunawayFromPlayerGoal;
import com.frogniche.nichesmobs.entity.spores_chief.SporesChiefEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SPMedic extends Animal implements IAnimatable {

    public static AttributeSupplier createAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.6d)
                .add(Attributes.ARMOR, 9)
                .build();
    }

    public static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(SporesChiefEntity.class, EntityDataSerializers.INT);


    protected final AnimationFactory factory = new AnimationFactory(this);

    public SPMedic(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TYPE, getInitialType().ordinal());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RunawayFromPlayerGoal(this, 3d, 30, 0.8f));

        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 10f));

        this.goalSelector.addGoal(10, new RandomStrollGoal(this, 0.5f, 10));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
    }

    protected PlayState predicate(AnimationEvent<SPMedic> event){
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sp_medic.new"));
        }else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sp_medic.idle"));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller1", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        NBTUtils.setIntIfPossible(v -> entityData.set(TYPE, v), "type", nbt);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("type", entityData.get(TYPE));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
        return EntityInit.SP_MEDIC.get().create(level);
    }

    public void setTextureType(Type t){
        this.entityData.set(TYPE, t.ordinal());
    }

    public Type getTextureType(){
        return Type.values()[entityData.get(TYPE)];
    }

    protected Type getInitialType(){
        return Type.values()[this.random.nextInt(Type.values().length)];
    }


    public static enum Type {
        NORMAL(NichesMobs.modLoc("textures/entity/bone_sp_medic.png")),
        BASTION(NichesMobs.modLoc("textures/entity/bastion_sp_medic.png")),
        WARPED(NichesMobs.modLoc("textures/entity/warped_sp_medic.png"));

        private final ResourceLocation texture;

        Type(ResourceLocation texture) {
            this.texture = texture;
        }

        public ResourceLocation getTexture() {
            return texture;
        }
    }
}
