package com.frogniche.nichesmobs.entity.spores_chief;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SporesChiefEntity extends Monster implements IAnimatable {

    public static final AttributeSupplier makeAttributes(){
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 200)
                .add(Attributes.ARMOR, 90)
                .add(Attributes.MOVEMENT_SPEED, 0.7d)
                .build();
    }

    protected AnimationFactory factory = new AnimationFactory(this);

    public SporesChiefEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    private PlayState predicate(AnimationEvent<SporesChiefEntity> event){
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent<SporesChiefEntity> event){
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return null;
    }
}
