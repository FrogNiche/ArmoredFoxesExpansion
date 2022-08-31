package com.frogniche.nichesmobs.entity.n_cauldron;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NCauldronModel extends AnimatedGeoModel<NCauldron> {
    @Override
    public ResourceLocation getModelResource(NCauldron object) {
        return NichesMobs.modLoc("geo/corrupted_cauldron.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NCauldron object) {
        return NichesMobs.modLoc("textures/entity/n_cauldron.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NCauldron animatable) {
        return NichesMobs.modLoc("animations/corrupted_cauldron.animation.json");
    }
}
