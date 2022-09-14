package com.frogniche.nichesmobs.entity.end_tadpole;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EndTadpoleModel extends AnimatedGeoModel<EndTadpole> {
    @Override
    public ResourceLocation getModelResource(EndTadpole object) {
        return NichesMobs.modLoc("geo/end_tadpole.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EndTadpole object) {
        return NichesMobs.modLoc("textures/entity/end_frog/end_boi.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EndTadpole animatable) {
        return NichesMobs.modLoc("animations/end_tadpole.animation.json");
    }
}
