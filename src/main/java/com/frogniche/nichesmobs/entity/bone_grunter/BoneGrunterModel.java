package com.frogniche.nichesmobs.entity.bone_grunter;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BoneGrunterModel extends AnimatedGeoModel<BoneGrunter> {
    @Override
    public ResourceLocation getModelResource(BoneGrunter object) {
        return NichesMobs.modLoc("geo/bone_grunter_geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BoneGrunter object) {
        return object.getTextureType().getTexture();
    }

    @Override
    public ResourceLocation getAnimationResource(BoneGrunter animatable) {
        return NichesMobs.modLoc("animations/bone_grunter_animation.json");
    }
}
