package com.frogniche.nichesmobs.entity.bastion_chief;

import com.frogniche.nichesmobs.entity.runt.Runt;
import com.frogniche.nichesmobs.entity.savager.SavagerGuardEntity;
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

public class BastionChief extends Monster implements IAnimatable {


    public static final String CONTROLLER_NAME = "controller";

    public static AttributeSupplier createAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 200)
                .add(Attributes.MOVEMENT_SPEED, 0.6d)
                .add(Attributes.ARMOR, 100)
                .add(Attributes.ATTACK_DAMAGE, 10)
                .build();
    }

    protected AnimationFactory factory = new AnimationFactory(this);

    public BastionChief(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.4f, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, true));

        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 10f));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Runt.class, 10f));

        this.goalSelector.addGoal(11, new RandomStrollGoal(this, 0.3f, 10));
        this.goalSelector.addGoal(11, new RandomLookAroundGoal(this));
    }

    private PlayState attackPredicate(AnimationEvent<SavagerGuardEntity> event) {
        if (this.swinging && event.getController().getAnimationState() == AnimationState.Stopped) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bastion_horde_chief.shoot_attack", false));
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    private PlayState predicate(AnimationEvent<SavagerGuardEntity> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bastion_horde_chief.walk", true));
            return PlayState.CONTINUE;
        } else
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bastion_horde_chief.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME, 0f, this::predicate));
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME + 2, 0f, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
