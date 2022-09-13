package com.frogniche.nichesmobs.entity.end_frog;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EndFrogModel extends AnimatedGeoModel<EndFrog> {
    @Override
    public ResourceLocation getModelResource(EndFrog object) {
        return NichesMobs.modLoc("geo/end_frog.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EndFrog object) {
        return NichesMobs.modLoc("textures/entity/end_frog/frog.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EndFrog animatable) {
        return NichesMobs.modLoc("animations/end_frog.animation.json");
    }
}
