package com.frogniche.nichesmobs.entity.snow_mole;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SnowMoleModel extends AnimatedGeoModel<EntitySnowMole> {
    @Override
    public ResourceLocation getModelResource(EntitySnowMole object) {
        return NichesMobs.modLoc("geo/snow_mole.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntitySnowMole object) {
        return NichesMobs.modLoc("textures/entity/snow_mole/snowmole.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntitySnowMole animatable) {
        return NichesMobs.modLoc("animations/snow_mole.animation.json");
    }
}