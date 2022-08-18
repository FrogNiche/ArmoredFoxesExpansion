package com.frogniche.nichesmobs.entity.sp;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SPModel extends AnimatedGeoModel<SPEntity> {
    @Override
    public ResourceLocation getModelResource(SPEntity object) {
        return NichesMobs.modLoc("geo/sp.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SPEntity object) {
        return NichesMobs.modLoc("textures/entity/sp/sp.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SPEntity animatable) {
        return NichesMobs.modLoc("animations/sp.animation.json");
    }
}