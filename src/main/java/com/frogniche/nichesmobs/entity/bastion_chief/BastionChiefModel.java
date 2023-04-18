package com.frogniche.nichesmobs.entity.bastion_chief;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BastionChiefModel extends AnimatedGeoModel<BastionChief> {
    @Override
    public ResourceLocation getModelResource(BastionChief object) {
        return NichesMobs.modLoc("geo/bastion_horde_chief.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BastionChief object) {
        return NichesMobs.modLoc("textures/entity/bastionchief.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BastionChief animatable) {
        return NichesMobs.modLoc("animations/bastion_horde_chief.animation.json");
    }
}
