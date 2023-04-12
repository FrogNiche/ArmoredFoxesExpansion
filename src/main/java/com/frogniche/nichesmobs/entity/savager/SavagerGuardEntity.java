package com.frogniche.nichesmobs.entity.savager;

import com.frogniche.nichesmobs.entity.EntityInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
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

public class SavagerGuardEntity extends Monster implements IAnimatable {

    public static final String CONTROLLER_NAME = "controller";

    public static AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.ARMOR, 12)
                .add(Attributes.MOVEMENT_SPEED, 0.6f)
                .add(Attributes.ATTACK_DAMAGE, 9).build();
    }

    protected final AnimationFactory factory = new AnimationFactory(this);

    public SavagerGuardEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    public SavagerGuardEntity(Level level) {
        this(EntityInit.SAVAGER_GUARD.get(), level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.5d, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 10f));
        this.goalSelector.addGoal(10, new RandomStrollGoal(this, 0.3d, 10));
    }

    private PlayState attackPredicate(AnimationEvent<SavagerGuardEntity> event) {
        if (this.swinging && event.getController().getAnimationState() == AnimationState.Stopped) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.wolf_imius.attack", false));
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    private PlayState predicate(AnimationEvent<SavagerGuardEntity> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.wolf_imius.walk", true));
            return PlayState.CONTINUE;
        } else
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.wolf_imius.idle", true));
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
