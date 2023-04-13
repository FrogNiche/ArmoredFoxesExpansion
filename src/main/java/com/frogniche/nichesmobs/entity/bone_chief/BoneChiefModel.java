package com.frogniche.nichesmobs.entity.bone_chief;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BoneChiefModel extends AnimatedGeoModel<BoneHordeChief> {
    @Override
    public ResourceLocation getModelResource(BoneHordeChief object) {
        return NichesMobs.modLoc("geo/bone_horde_chief.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BoneHordeChief object) {
        return NichesMobs.modLoc("textures/entity/bone_horde_chief.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BoneHordeChief animatable) {
        return NichesMobs.modLoc("animations/bone_horde_chief.animation.json");
    }
}
