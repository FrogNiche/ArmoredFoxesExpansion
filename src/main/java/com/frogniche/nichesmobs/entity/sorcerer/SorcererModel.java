package com.frogniche.nichesmobs.entity.sorcerer;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SorcererModel extends AnimatedGeoModel<Sorcerer> {
    @Override
    public ResourceLocation getModelResource(Sorcerer object) {
        return NichesMobs.modLoc("geo/sorcerer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Sorcerer object) {
        return NichesMobs.modLoc("textures/entity/sorcerer/wizardy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Sorcerer animatable) {
        return NichesMobs.modLoc("animations/sorcerer.animation.json");
    }
}