package com.frogniche.nichesmobs.entity.françois;

import com.frogniche.nichesmobs.entity.EntityInit;
import com.frogniche.nichesmobs.entity.furry.FurryEntity;
import com.frogniche.nichesmobs.entity.sp.SPEntity;
import com.frogniche.nichesmobs.entity.wolfie.WolfieEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
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

public class Francois extends Animal implements IAnimatable {

    public static final String CONTROLLER_NAME = "controller";

    private final AnimationFactory factory = new AnimationFactory(this);


    // sorry about the caps.
    public static final AttributeSupplier.Builder createAttributes(){
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 70)
                .add(Attributes.MOVEMENT_SPEED, 1d)
                .add(Attributes.MOVEMENT_SPEED, 0.25d)
                .add(Attributes.ARMOR, 5d);
    }

    public Francois(EntityType<Francois> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class,

                16f, 1.6f, 1.4f){

        });
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, SPEntity.class,
                6.0F, 1.0D, 1.2D));

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F){

        });
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this){



        });
        this.targetSelector.addGoal(6, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(10, new RandomStrollGoal(this, 0.7d){

        });
    }


    // bretzel, and tha animations? I pasted the animation on the animations file on the resources' folder.
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob animal) {
        return EntityInit.FRANCOIS.get().create(level);

    }


    private PlayState predicate(AnimationEvent<Francois> event){
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.francois.walk", true));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.francois.idle", true));
            return PlayState.CONTINUE;
        }




    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME, 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.FOX_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.FOX_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.FOX_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

    @Override
    public boolean hurt(DamageSource p_27567_, float p_27568_) {

        return super.hurt(p_27567_, p_27568_);
    }

}
