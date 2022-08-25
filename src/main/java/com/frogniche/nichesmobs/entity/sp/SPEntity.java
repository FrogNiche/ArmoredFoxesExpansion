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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class SPEntity extends Monster implements IAnimatable {
    // is it okay?
    protected ServerBossEvent bossBar = (ServerBossEvent) new ServerBossEvent(this.getDisplayName(),

            BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS);
    {

        bossBar.setProgress(this.getHealth() / this.getMaxHealth());
    }

    public static final AttributeSupplier createAttributes(){
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 300)
                .add(Attributes.MOVEMENT_SPEED, 0.25d)
                .add(Attributes.ATTACK_DAMAGE, 20)
                .add(Attributes.ARMOR, 21)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2).build();

    }
    public static final String CONTROLLER_NAME = "controller";


    private AnimationFactory factory = new AnimationFactory(this);

    public SPEntity(EntityType<SPEntity> type, Level world){
        super(type, world);
    }

    protected SPEntity(Level world){
        this(EntityInit.SP.get(), world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.1d, false){

        });
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true){

        });
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, Monster.class, true){

        });
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal(this, IronGolem.class, true){
        });
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this,Player.class, 8f){

        });
        this.goalSelector.addGoal(10, new RandomStrollGoal(this, 1f){

        });
    }



    @Override
    public boolean doHurtTarget(Entity opfer) {
        if(super.doHurtTarget(opfer)){
            this.level.broadcastEntityEvent(this, (byte)10);
            return true;
        }
        return false;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if(id == 10){
            AnimationController<SPEntity> controller = GeckoLibUtil.getControllerForID(this.factory, this.getId(), CONTROLLER_NAME);
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.sp.attack"));
        }else
            super.handleEntityEvent(id);
    }


    private PlayState predicate(AnimationEvent event){
        if(event.getController().getCurrentAnimation() != null && event.getController().getCurrentAnimation().animationName.equals("animation.sp.attack"))
            return PlayState.CONTINUE;

        if (isSleeping()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sp.sleep", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sp.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sp.idle", true));
        return PlayState.CONTINUE;

    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME, 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.FOX_AMBIENT, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAT_STRAY_AMBIENT;
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
    public void stopSeenByPlayer(ServerPlayer stopSeenByPlayer) {
        super.stopSeenByPlayer(stopSeenByPlayer);
        this.bossBar.removePlayer(stopSeenByPlayer);
    }

    public void setCustomName(@Nullable Component customName) {
        super.setCustomName(customName);
        this.bossBar.setName(this.getDisplayName());
    }
}