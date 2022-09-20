package com.frogniche.nichesmobs.entity.n_cauldron;

import com.frogniche.nichesmobs.entity.guard.GuardEntity;
import com.frogniche.nichesmobs.entity.sp.SPEntity;
import net.minecraft.client.gui.screens.social.PlayerSocialManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;


public class NCauldron extends AmbientCreature implements IAnimatable {

    public static final String CONTROLLER_NAME = "controller1";

    protected static final EntityDataAccessor<Boolean> SLEEP = SynchedEntityData.defineId(NCauldron.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> WAS_SLEEP = SynchedEntityData.defineId(NCauldron.class, EntityDataSerializers.BOOLEAN);
    @OnlyIn(Dist.CLIENT)
    private int awakeTickCounter;
    private ServerBossEvent bossEvent = (ServerBossEvent) new
            ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED,
            BossEvent.BossBarOverlay.PROGRESS).setDarkenScreen(false);

    public static final AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 400)
                .add(Attributes.ATTACK_DAMAGE, 9)
                .add(Attributes.ARMOR, 50)
                .add(Attributes.KNOCKBACK_RESISTANCE, 100).build();
    }

    private AnimationFactory factory = new AnimationFactory(this);

    public NCauldron(EntityType<NCauldron> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true) {
        });
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, Monster.class, true) {
        });
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal(this, IronGolem.class, true) {
        });
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8f) {
        });
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 10) {
            AnimationController<GuardEntity> controller = GeckoLibUtil.getControllerForID(this.factory, this.getId(), CONTROLLER_NAME);
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.n_cauldron.attack"));
        } else
            super.handleEntityEvent(id);
    }

     private PlayState predicate(AnimationEvent<NCauldron> event){
        if (awakeTickCounter > 0){
            awakeTickCounter--;
            return PlayState.CONTINUE;
        }
        if (entityData.get(SLEEP)){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.n_cauldron.sleep", true));
            event.getController().setAnimation(new AnimationBuilder().addAnimation(
                    "animation.n_cauldron.sleep", true));
            return PlayState.CONTINUE;
        }
        if (!entityData.get(SLEEP) && entityData.get(WAS_SLEEP) && this.awakeTickCounter <= 0){
            this.awakeTickCounter = (int) (20f*3.125f);
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.n_cauldron.awake"));
            event.getController().setAnimation(new AnimationBuilder().addAnimation(
                    "animation.n_cauldron.awake"));
            this.entityData.set(WAS_SLEEP, false);
            return PlayState.CONTINUE;
        }
         event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.n_cauldron.idle", true));
         return PlayState.CONTINUE;
     }

    private PlayState attackPredicate(AnimationEvent event) {
        if(this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.n_cauldron.attack", false));
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
        this.entityData.define(SLEEP, true);
        this.entityData.define(WAS_SLEEP, true);
    }

    @Override
    public void knockback(double v, double p_147242_, double p_147243_) {
        net.minecraftforge.event.entity.living.LivingKnockBackEvent event =
                net.minecraftforge.common.ForgeHooks.onLivingKnockBack(this,
                        (float) v, p_147242_, p_147243_);
        if(event.isCanceled()) return;
        v = event.getStrength();
        p_147242_ = event.getRatioX();
        p_147243_ = event.getRatioZ();
        v *= 1.0D - this.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
        if (!(v <= 0.0D)) {
            this.hasImpulse = false;
            Vec3 vec3 = this.getDeltaMovement();
            Vec3 vec31 = (new Vec3(p_147242_, 0.0D, p_147243_)).normalize().scale(v);
            this.setDeltaMovement(vec3.x / 2.0D - vec31.x, vec3.y, vec3.z /
                    2.0D - vec31.z);
        }
    }
    @Override
    public boolean doHurtTarget(Entity opfer) {
        if(super.doHurtTarget(opfer)){
            this.level.broadcastEntityEvent(this, (byte)10);
            return true;
        } else {
            if (opfer instanceof LivingEntity) {
                ((LivingEntity)opfer).addEffect(new MobEffectInstance(MobEffects.REGENERATION,100), this);
            }
        }
        return true;
    }

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        boolean ret = super.hurt(p_21016_, p_21017_);
        if (ret)
            this.entityData.set(SLEEP, false);
        return ret;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) {
        super.readAdditionalSaveData(p_21450_);
        if (this.hasCustomName()) {
            this.bossEvent.setName(this.getDisplayName());
        }
    }


    @Override
    public void tick() {
        super.tick();
        if(!this.level.isClientSide()){
            if (this.entityData.get(SLEEP)) {
                List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class,
                        this.getBoundingBox().inflate(20), e -> e != this &&
                                EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(e));
                if (entities.size() != 0)
                    this.entityData.set(SLEEP, false);
            }

        }
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }


    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public void startSeenByPlayer(ServerPlayer p_31483_) {
        super.startSeenByPlayer(p_31483_);
        this.bossEvent.addPlayer(p_31483_);
    }

    public void stopSeenByPlayer(ServerPlayer p_31488_) {
        super.stopSeenByPlayer(p_31488_);
        this.bossEvent.removePlayer(p_31488_);
    }

    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.bossEvent.setName(this.getDisplayName());
    }
}
