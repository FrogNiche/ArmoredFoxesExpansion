package com.frogniche.nichesmobs.entity.n_cauldron;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.Level;

import java.util.jar.Attributes;

public class NCauldron extends AmbientCreature {


    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes();
    }

    public NCauldron(EntityType<NCauldron> type, Level level) {
        super(type, level);
    }


}
