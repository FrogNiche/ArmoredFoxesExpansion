package com.frogniche.nichesmobs.entity.snow_mole;
import com.frogniche.nichesmobs.entity.EntityInit;
import com.frogniche.nichesmobs.entity.furry.FurryEntity;
import com.frogniche.nichesmobs.entity.sp.SPEntity;
import com.frogniche.nichesmobs.entity.wolfie.WolfieEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class EntitySnowMole extends Animal implements IAnimatable {

    public static final String CONTROLLER_NAME = "controller";

    private final AnimationFactory factory = new AnimationFactory(this);

    public static final EntityDataAccessor<Boolean> IN_SNOW = SynchedEntityData.defineId(EntitySnowMole.class,
            EntityDataSerializers.BOOLEAN);
    // sorry about the caps.
    public static final AttributeSupplier.Builder createAttributes(){
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 1d)
                .add(Attributes.MOVEMENT_SPEED, 0.25d)
                .add(Attributes.ARMOR, 5d);
    }

    public EntitySnowMole(EntityType<EntitySnowMole> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new DigDownGoal());
        this.goalSelector.addGoal(0, new DigUpGoal());
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class,

                16f, 1.6f, 1.4f){
            @Override
            public boolean canUse() {
                return !isInSnow() && super.canUse();
            }
        });
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, SPEntity.class,
                6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WolfieEntity.class,
                6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, FurryEntity.class,
                6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.7f){
            @Override
            public boolean canUse() {
                return !isInSnow() &&super.canUse();
            }
        });
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F){
            @Override
            public boolean canUse() {
                return !isInSnow() && super.canUse();
            }
        });
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this){
            @Override
            public boolean canUse() {
                return !isInSnow() && super.canUse();
            }
        });
        this.targetSelector.addGoal(6, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(10, new RandomStrollGoal(this, 0.7d){
            @Override
            public boolean canUse() {
                return !isInSnow() && super.canUse();
            }
        });
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IN_SNOW, false);
    }

    // bretzel, and tha animations? I pasted the animation on the animations file on the resources' folder.
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob animal) {
        return EntityInit.SNOW_MOLE.get().create(level);

    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_27576_) {
        super.readAdditionalSaveData(p_27576_);
        this.entityData.set(IN_SNOW, p_27576_.getBoolean("in_snow"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_27587_) {
        super.addAdditionalSaveData(p_27587_);
        p_27587_.putBoolean("in_snow", this.entityData.get(IN_SNOW));
    }

    private PlayState predicate(AnimationEvent<EntitySnowMole> event){
        if(!isInSnow()) {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.snow_mole.walk", true));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.snow_mole.idle", true));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    public boolean isInSnow(){
        return this.entityData.get(IN_SNOW);
    }

    public void setInSnow(boolean inSnow){
        if (this.isInSnow() != inSnow){
            if (inSnow){
                GeckoLibUtil.getControllerForID(this.factory, getId(), CONTROLLER_NAME).setAnimation(new
                        AnimationBuilder().addAnimation("animation.snow_mole.dig", false));
            }
        }
        this.entityData.set(IN_SNOW, inSnow);
    }
//

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
        return stack.getItem() == Items.APPLE;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.DOLPHIN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.DOLPHIN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

    @Override
    public boolean hurt(DamageSource p_27567_, float p_27568_) {
        if (isInSnow()){
            setInSnow(false);
        }
        return super.hurt(p_27567_, p_27568_);
    }

    protected class DigDownGoal extends Goal{

        @Override
        public boolean canUse() {
            if(!isInSnow() && level.getEntities(EntitySnowMole.this,
                    getBoundingBox().inflate(10),EntitySelector.NO_CREATIVE_OR_SPECTATOR).size() == 0){
                start();
                return true;
            }
            return false;
        }

        @Override
        public void start() {
            setInSnow(true);
        }
    }
    protected class DigUpGoal extends Goal{

        @Override
        public boolean canUse() {
            if(isInSnow() && level.getEntities(EntitySnowMole.this, getBoundingBox().inflate(10),EntitySelector.NO_CREATIVE_OR_SPECTATOR).size() != 0){
                start();
                return true;
            }
            return false;
        }

        @Override
        public void start() {
            setInSnow(false);
        }
    }

}