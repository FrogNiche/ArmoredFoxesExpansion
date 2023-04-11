package com.frogniche.nichesmobs.entity.sp_medic;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpMedicModel extends AnimatedGeoModel<SPMedic> {
    @Override
    public ResourceLocation getModelResource(SPMedic object) {
        return NichesMobs.modLoc("geo/sp_medic.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SPMedic object) {
        return object.getTextureType().getTexture();
    }

    @Override
    public ResourceLocation getAnimationResource(SPMedic animatable) {
        return NichesMobs.modLoc("animations/sp_medic.animation.json");
    }
}
