package com.frogniche.nichesmobs.entity.elite_paws;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ElitePawsModel extends AnimatedGeoModel<ElitePaws> {
    @Override
    public ResourceLocation getModelResource(ElitePaws object) {
        return NichesMobs.modLoc("geo/elite_paws.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ElitePaws object) {
        return NichesMobs.modLoc("textures/entity/elite_paws/elitepaws.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ElitePaws animatable) {
        return NichesMobs.modLoc("animations/elite_paws.animation.json");
    }
}