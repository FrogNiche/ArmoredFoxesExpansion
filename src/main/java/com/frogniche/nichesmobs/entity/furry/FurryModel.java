package com.frogniche.nichesmobs.entity.furry;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FurryModel extends AnimatedGeoModel<FurryEntity> {
    @Override
    public ResourceLocation getModelResource(FurryEntity object) {
        return NichesMobs.modLoc(
                "geo/furry.geo.json");
    }
    @Override
    public ResourceLocation getTextureResource(FurryEntity object) {
        return NichesMobs.modLoc( "textures/entity/furry/furry.png");
    }
    @Override
    public ResourceLocation getAnimationResource(FurryEntity animatable) {
        return NichesMobs.modLoc( "animations/furry.animation.json");
    }
}