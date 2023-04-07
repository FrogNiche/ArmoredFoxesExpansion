package com.frogniche.nichesmobs.entity.savager;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SavagerGuardModel extends AnimatedGeoModel<SavagerGuardEntity> {
    @Override
    public ResourceLocation getModelResource(SavagerGuardEntity object) {
        return NichesMobs.modLoc("geo/bastion_guard.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SavagerGuardEntity object) {
        return NichesMobs.modLoc("textures/entity/savager_guard.png");

    }

    @Override
    public ResourceLocation getAnimationResource(SavagerGuardEntity animatable) {
        return NichesMobs.modLoc("animations/bastion_guard.animation.json");
    }
}
