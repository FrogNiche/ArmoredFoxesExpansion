package com.frogniche.nichesmobs.entity.potted_paws;

import com.frogniche.nichesmobs.entity.RunawayFromPlayerGoal;
import com.frogniche.nichesmobs.entity.sp_medic.SPMedic;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class PottedPaws extends PathfinderMob implements IAnimatable {

    public static AttributeSupplier createAttributes(){
        return AmbientCreature.createMobAttributes().add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.6d)
                .add(Attributes.ARMOR, 9)
                .build();
    }

    protected AnimationFactory factory = new AnimationFactory(this);

    public PottedPaws(EntityType<? extends PathfinderMob> p_27403_, Level p_27404_) {
        super(p_27403_, p_27404_);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RunawayFromPlayerGoal(this, 3d, 30, 0.8f));

        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 10f));

        this.goalSelector.addGoal(10, new RandomStrollGoal(this, 0.5f, 10));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
    }

    protected PlayState predicate(AnimationEvent<SPMedic> event){
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.potted_paws.walk"));
        }else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.potted_paws.idle"));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller1", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
