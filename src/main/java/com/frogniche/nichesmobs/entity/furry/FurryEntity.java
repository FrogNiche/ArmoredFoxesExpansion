package com.frogniche.nichesmobs.entity.furry;

import com.frogniche.nichesmobs.effect.ModEffects;
import com.frogniche.nichesmobs.entity.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
public class FurryEntity extends Monster implements IAnimatable {

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


    public FurryEntity(EntityType<FurryEntity> type, Level world) {
        super(type, world);
    }

    protected FurryEntity(Level world) {
        this(EntityInit.FURRY.get(), world);
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
    public void handleEntityEvent(byte id) {
        if (id == 10) {
            AnimationController<FurryEntity> controller = GeckoLibUtil.getControllerForID(this.factory, this.getId(), CONTROLLER_NAME);
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.furry.attack"));
        } else
            super.handleEntityEvent(id);
    }

    private PlayState predicate(AnimationEvent event) {
        if (event.getController().getCurrentAnimation() != null && event.getController().getCurrentAnimation().animationName.equals("animation.wolfie.attack"))
            return PlayState.CONTINUE;
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.furry.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.furry.idle", true));
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
}

