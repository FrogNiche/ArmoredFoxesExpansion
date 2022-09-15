package com.frogniche.nichesmobs.entity.sp;

import com.frogniche.nichesmobs.entity.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.*;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;

import java.util.List;

public class SPEntity extends Monster implements IAnimatable {
    protected ServerBossEvent bossBar = (ServerBossEvent) new ServerBossEvent(this.getDisplayName(),

            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS).setDarkenScreen(true);
    public static final AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.23d)
                .add(Attributes.ATTACK_DAMAGE, 9)
                .add(Attributes.ARMOR, 21)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2).build();

    }

    private AnimationFactory factory = new AnimationFactory(this);

    public static final String CONTROLLER_NAME = "controller";

    public static final EntityDataAccessor<Boolean> Sleeping = SynchedEntityData.defineId(SPEntity.class,
            EntityDataSerializers.BOOLEAN);
    public SPEntity(EntityType<SPEntity> type, Level world) {
        super(type, world);
    }

    protected SPEntity(Level world) {
        this(EntityInit.SP.get(), world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.1d, false) {
        });
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true) {
        });
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, Monster.class, true) {
        });
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal(this, IronGolem.class, true) {
        });
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8f) {
        });
        this.goalSelector.addGoal(10, new RandomStrollGoal(this, 1f) {
            @Override
            public boolean canUse() {
                return super.canUse() && !isSleeping();
            }
        });
    }

    @Override
    public boolean doHurtTarget(Entity opfer) {
        if(super.doHurtTarget(opfer)){
            this.level.broadcastEntityEvent(this, (byte)10);
            return true;
        } else {
            if (opfer instanceof LivingEntity) {
                ((LivingEntity)opfer).addEffect(new MobEffectInstance(MobEffects.DARKNESS,100), this);
            }
        }
        return true;
    }
    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide) {
            if (this.isSleeping()) {
                List<Entity> entities = this.level.getEntities(this, new AABB(this.blockPosition()).inflate(10), e -> e instanceof Player);
                if (entities.size() != 0)
                    this.entityData.set(Sleeping, false);
            } else if (this.getTarget() == null && this.getLastHurtByMob() != null && this.getLastHurtByMob().distanceToSqr(this) > 9) {
                this.entityData.set(Sleeping, true);
            }
            bossBar.setProgress(this.getHealth() / this.getMaxHealth());
        }
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        if (this.isSleeping() && source.getEntity() != null)
            this.entityData.set(Sleeping, false);
        return super.hurt(source, damage);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 10) {
            AnimationController<SPEntity> controller = GeckoLibUtil.getControllerForID(this.factory, this.getId(), CONTROLLER_NAME);
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.sp.attack"));
        } else
            super.handleEntityEvent(id);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sp.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sp.idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if(this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sp.attack", false));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
        data.addAnimationController(new AnimationController(this, "attackController",
                0, this::attackPredicate));
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(Sleeping, true);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("sleeping", isSleeping());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(Sleeping, tag.getBoolean("sleeping"));
        if (this.hasCustomName()) {
            this.bossBar.setName(this.getDisplayName());
            bossBar.setProgress(this.getHealth() / this.getMaxHealth());
        }
    }
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
    public boolean isSleeping() {
        return this.entityData.get(Sleeping);
    }
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 0.15F, 1.0F);
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
    public void startSeenByPlayer(ServerPlayer p31483) {
        super.startSeenByPlayer(p31483);
        this.bossBar.addPlayer(p31483);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer p31488) {
        super.stopSeenByPlayer(p31488);
        this.bossBar.removePlayer(p31488);
    }

    public void setCustomName(@Nullable Component p31476) {
        super.setCustomName(p31476);
        this.bossBar.setName(this.getDisplayName());
    }
}

