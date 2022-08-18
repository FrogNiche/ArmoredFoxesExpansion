package com.frogniche.nichesmobs.entity.wolfie;

import com.frogniche.nichesmobs.NichesMobs;
import com.frogniche.nichesmobs.entity.wolfie.WolfieEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WolfieModel extends AnimatedGeoModel<WolfieEntity> {
    @Override
    public ResourceLocation getModelResource(WolfieEntity object) {
        return NichesMobs.modLoc("geo/wolfie.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WolfieEntity object) {
        return NichesMobs.modLoc("textures/entity/wolfie/wolfie.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WolfieEntity animatable) {
        return NichesMobs.modLoc("animations/wolfie.animation.json");
    }
}