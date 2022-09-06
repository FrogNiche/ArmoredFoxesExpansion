package com.frogniche.nichesmobs.entity.badger.warped_badger;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WarpedBadgerModel extends AnimatedGeoModel<WarpedBadger> {
    @Override
    public ResourceLocation getModelResource(WarpedBadger object) {
        return NichesMobs.modLoc("geo/badger.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WarpedBadger object) {
        return NichesMobs.modLoc("textures/entity/badger/warpy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WarpedBadger animatable) {
        return NichesMobs.modLoc("animations/badger.animation.json");
    }
}

