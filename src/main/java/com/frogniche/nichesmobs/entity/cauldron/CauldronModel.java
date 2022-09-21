package com.frogniche.nichesmobs.entity.cauldron;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CauldronModel extends AnimatedGeoModel<Cauldron> {
    @Override
    public ResourceLocation getModelResource(Cauldron object) {
        return NichesMobs.modLoc("geo/n_cauldron.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Cauldron object) {
        return NichesMobs.modLoc("textures/entity//n_cauldron/cauldron.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Cauldron animatable) {
        return NichesMobs.modLoc("animations/n_cauldron.animation.json");
    }
}
