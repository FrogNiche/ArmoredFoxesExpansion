package com.frogniche.nichesmobs.entity.badger.crimsom_badger;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CrimsomBadgerModel extends AnimatedGeoModel<CrimsomBadger> {
    @Override
    public ResourceLocation getModelResource(CrimsomBadger object) {
        return NichesMobs.modLoc("geo/badger.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CrimsomBadger object) {
        return NichesMobs.modLoc("textures/entity/badger/crimpy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CrimsomBadger animatable) {
        return NichesMobs.modLoc("animations/badger.animation.json");
    }
}

