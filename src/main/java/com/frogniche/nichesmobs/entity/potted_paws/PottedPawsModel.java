package com.frogniche.nichesmobs.entity.potted_paws;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PottedPawsModel extends AnimatedGeoModel<PottedPaws> {
    @Override
    public ResourceLocation getModelResource(PottedPaws object) {
        return NichesMobs.modLoc("geo/potted_paws.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PottedPaws object) {
        return NichesMobs.modLoc("textures/entity/potted_paws.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PottedPaws animatable) {
        return NichesMobs.modLoc("animations/potted_paws.animation.json");
    }
}
