package com.frogniche.nichesmobs.entity.runt;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RuntModel extends AnimatedGeoModel<Runt> {
    @Override
    public ResourceLocation getModelResource(Runt object) {
        return NichesMobs.modLoc("geo/runt.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Runt object) {
        return object.getTextureType().getTexture();
    }

    @Override
    public ResourceLocation getAnimationResource(Runt animatable) {
        return NichesMobs.modLoc("animations/runt.animation.json");
    }
}
