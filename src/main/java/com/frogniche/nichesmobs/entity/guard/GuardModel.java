package com.frogniche.nichesmobs.entity.guard;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GuardModel extends AnimatedGeoModel<GuardEntity> {
    @Override
    public ResourceLocation getModelResource(GuardEntity object) {
        return NichesMobs.modLoc(
                "geo/royal_guard.geo.json");
    }
    @Override
    public ResourceLocation getTextureResource(GuardEntity object) {
        return NichesMobs.modLoc( "textures/entity/guard/guard.png");
    }
    @Override
    public ResourceLocation getAnimationResource(GuardEntity animatable) {
        return NichesMobs.modLoc( "animations/royal_guard.animation.json");
    }
}