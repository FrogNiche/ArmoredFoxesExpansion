package com.frogniche.nichesmobs.entity.wavy;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WavyModel extends AnimatedGeoModel<WavyGeo> {
    @Override
    public ResourceLocation getModelResource(WavyGeo object) {
        return NichesMobs.modLoc("geo/wavy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WavyGeo object) {
        return NichesMobs.modLoc("textures/entity/wavy/w.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WavyGeo animatable) {
        return NichesMobs.modLoc("animations/wavy.animation.json");
    }
}

