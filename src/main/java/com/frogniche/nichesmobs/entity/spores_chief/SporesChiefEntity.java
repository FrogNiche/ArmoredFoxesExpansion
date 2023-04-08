package com.frogniche.nichesmobs.entity.spores_chief;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
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

public class SporesChiefEntity extends Monster implements IAnimatable, RoarEntity {

    public static final EntityDataAccessor<Boolean> ROAR = SynchedEntityData.defineId(SporesChiefEntity.class, EntityDataSerializers.BOOLEAN);

    public static final AttributeSupplier makeAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 200)
                .add(Attributes.ARMOR, 90)
                .add(Attributes.MOVEMENT_SPEED, 0.6d)
                .add(Attributes.ATTACK_DAMAGE, 19)
                .build();
    }

    protected AnimationFactory factory = new AnimationFactory(this);
    private int roarCooldown;

    public SporesChiefEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }


    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide()) {
            if (this.roarCooldown > 0) {
                roarCooldown--;
                if (roarCooldown == 0)
                    this.entityData.set(ROAR, false);
            }
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RoarGoal<>(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6f, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.5d));

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ROAR, false);
    }

    private PlayState predicate(AnimationEvent<SporesChiefEntity> event) {
        if (entityData.get(ROAR)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.horde_spore_chief.roar", false));
            return PlayState.CONTINUE;
        } else if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.horde_spore_chief.walk", true));
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.horde_spore_chief.idle", true));
        }
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent<SporesChiefEntity> event) {
        if (this.swinging && event.getController().getAnimationState() == AnimationState.Stopped) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.horde_spore_chief.attack", false));
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController(this, "attack_controller", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void roar() {
        if (!level.isClientSide()) {
            this.entityData.set(ROAR, true);
            this.roarCooldown = 84;
            this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 84, 99, false, false));
        }
    }

    @Override
    public boolean canRoar() {
        return this.roarCooldown <= 0;
    }

    public static class RoarGoal<T extends Mob & RoarEntity> extends Goal {

        protected final T entity;
        protected LivingEntity prevTarget = null;

        /**
         * @param entity must implement the RoarEntity interface
         */
        public RoarGoal(T entity) {
            this.entity = entity;
        }

        @Override
        public boolean canUse() {
            if (entity.getTarget() == null)
                prevTarget = null;
            return entity.canRoar() && prevTarget == null && entity.getTarget() != prevTarget;
        }

        @Override
        public void start() {
            entity.roar();
            prevTarget = entity.getTarget();
        }
    }
}
