package com.frogniche.nichesmobs.entity.wavy;
import com.frogniche.nichesmobs.ai.AvoidProjectilesGoal;
import com.frogniche.nichesmobs.entity.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
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

public class WavyGeo extends Monster implements IAnimatable {
    private ServerBossEvent bossEvent = (ServerBossEvent) new
            ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.GREEN,
            BossEvent.BossBarOverlay.PROGRESS).setDarkenScreen(false);

    public static final AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 400)
                .add(Attributes.ATTACK_DAMAGE, 15)
                .add(Attributes.ARMOR, 10)
                .add(Attributes.KNOCKBACK_RESISTANCE, 6)
                .add(Attributes.MOVEMENT_SPEED, 0.25d).build();

    }
    public static final String CONTROLLER_NAME = "sculk_controller";

    public static final EntityDataAccessor<Boolean> SLEEP = SynchedEntityData.defineId(WavyGeo.class,
            EntityDataSerializers.BOOLEAN);

    private AnimationFactory factory = new AnimationFactory(this);

    public WavyGeo(EntityType<WavyGeo> type, Level world){
        super(type, world);
    }

    /*
    protected WavyGeo(Level world){
        this(EntityInit.WAVY_GEO.get(), world);
    }

     */

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.1d, false) {

        });
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true) {

        });

        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, Monster.class, true) {

        });

        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, Monster.class, true) {

        });
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal(this, IronGolem.class, true) {
        });

        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal(this, Villager.class, true) {
        });
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 10f));
        this.goalSelector.addGoal(10, new RandomStrollGoal(this, 1f));
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide){
            if(this.isSleeping()) {
                List<Entity> entities = this.level.getEntities(this, new AABB(this.blockPosition()).inflate(2), e -> e instanceof Player);
                if (entities.size() != 0)
                    this.entityData.set(SLEEP, false);
            }else if(this.getTarget() == null && this.getLastHurtByMob() != null && this.getLastHurtByMob().distanceToSqr(this) < 4){
                this.entityData.set(SLEEP, true);
            }
            this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

            }
        }
    @Override
    public boolean hurt(DamageSource source, float damage) {
        return super.hurt(source, damage);

    }


    @Override
    public boolean doHurtTarget(Entity opfer) {
        if(super.doHurtTarget(opfer)){
            this.level.broadcastEntityEvent(this, (byte)10);
            return true;
        } else {
            if (opfer instanceof LivingEntity) {
                ((LivingEntity)opfer).addEffect(new MobEffectInstance(MobEffects.LEVITATION,100), this);
            }
        }
        return true;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SLEEP, true);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("sleeping", isSleeping());
            if (this.hasCustomName()) {
                this.bossEvent.setName(this.getDisplayName());
            }
        }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(SLEEP, tag.getBoolean("sleeping"));
    }
    

       /* if (isSleeping()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.wavy.sleep", true));
            return PlayState.CONTINUE;*/
       @Override
       public void handleEntityEvent(byte id) {
           if (id == 10) {
               AnimationController<WavyGeo> controller = GeckoLibUtil.getControllerForID(this.factory, this.getId(), CONTROLLER_NAME);
               controller.setAnimation(new AnimationBuilder().addAnimation("animation.wavy.attack1"));
           } else
               super.handleEntityEvent(id);
       }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.wavy.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.wavy.idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if(this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.wavy.attack1", false));
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
    public AnimationFactory getFactory() {
        return factory;

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


    public boolean isSleeping(){
        return this.entityData.get(SLEEP);
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.FIRE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.PLAYER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PLAYER_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

}