package com.frogniche.nichesmobs.entity.end_tadpole;

import com.frogniche.nichesmobs.entity.end_frog.EndFrog;
import com.frogniche.nichesmobs.entity.furry.FurryEntity;
import com.frogniche.nichesmobs.entity.sp.SPEntity;
import com.frogniche.nichesmobs.entity.wolfie.WolfieEntity;
import com.frogniche.nichesmobs.item.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EndTadpole extends AbstractFish implements IAnimatable {
    public EndTadpole(EntityType<? extends AbstractFish> p_27461_, Level p_27462_) {
        super(p_27461_, p_27462_);
    }

    public static final String CONTROLLER_NAME = "controller";

    public static final AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.MOVEMENT_SPEED, 1d)
                .add(Attributes.ARMOR, 3d);
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WolfieEntity.class,
                6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, FurryEntity.class,
                6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this) {
        });


        this.targetSelector.addGoal(6, (new HurtByTargetGoal(this)).setAlertOthers());

    }

    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.TADPOLE_FLOP;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(Items.TADPOLE_BUCKET);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME, 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private PlayState predicate(AnimationEvent<EndFrog> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.end_tadpole.swimming", true));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }
}
