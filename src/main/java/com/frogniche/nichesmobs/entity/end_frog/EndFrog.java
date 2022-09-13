package com.frogniche.nichesmobs.entity.end_frog;

import com.frogniche.nichesmobs.entity.EntityInit;
import com.frogniche.nichesmobs.entity.furry.FurryEntity;
import com.frogniche.nichesmobs.entity.sp.SPEntity;
import com.frogniche.nichesmobs.entity.wolfie.WolfieEntity;
import com.frogniche.nichesmobs.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class EndFrog extends Animal implements IAnimatable {

    public static final String CONTROLLER_NAME = "controller";

    private final AnimationFactory factory = new AnimationFactory(this);

    // sorry about the caps.
    public static final AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 1d)
                .add(Attributes.MOVEMENT_SPEED, 0.5d)
                .add(Attributes.ARMOR, 5d);
    }

    public EndFrog(EntityType<EndFrog> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {


        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, SPEntity.class,
                6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WolfieEntity.class,
                6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, FurryEntity.class,
                6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.7f) {

        });
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F) {

        });
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this) {
        });
        this.targetSelector.addGoal(6, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(10, new RandomStrollGoal(this, 0.7d) {

        });
    }


    // bretzel, and tha animations? I pasted the animation on the animations file on the resources' folder.
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob animal) {
        return EntityInit.SNOW_MOLE.get().create(level);

    }

    private PlayState predicate(AnimationEvent<EndFrog> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.end_frog.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.end_frog.idle", true));
        return PlayState.CONTINUE;
    }

    public boolean fromBucket() {
        return true;
    }

    public void setFromBucket(boolean p_218732_) {
    }

    public void saveToBucketTag(ItemStack p_218725_) {
        Bucketable.saveDefaultDataToBucketTag(this, p_218725_);
        CompoundTag compoundtag = p_218725_.getOrCreateTag();
        compoundtag.putInt("Age", this.getAge());
    }

    public void loadFromBucketTag(CompoundTag p_218715_) {
        Bucketable.loadDefaultDataFromBucketTag(this, p_218715_);
        if (p_218715_.contains("Age")) {
            this.setAge(p_218715_.getInt("Age"));
        }

    }



    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME, 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.CHORUS_FRUIT;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.FROG_AMBIENT, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.FROG_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.FROG_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.FROG_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

    @Override
    public boolean hurt(DamageSource p_27567_, float p_27568_) {


        return super.hurt(p_27567_, p_27568_);
    }
    public ItemStack getBucketItemStack() {
        return new ItemStack(Items.BUCKET);
    }

    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_AXOLOTL;
    }
    }
