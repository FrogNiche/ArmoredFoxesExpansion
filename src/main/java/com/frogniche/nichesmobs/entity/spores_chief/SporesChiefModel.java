package com.frogniche.nichesmobs.entity.spores_chief;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SporesChiefModel extends AnimatedGeoModel<SporesChiefEntity> {
    @Override
    public ResourceLocation getModelResource(SporesChiefEntity object) {
        return NichesMobs.modLoc("geo/horde_spore_chief.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SporesChiefEntity object) {
        return NichesMobs.modLoc("textures/entity/spores_chief.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SporesChiefEntity animatable) {
        return NichesMobs.modLoc("animations/horde_spore_chief.animation.json");
    }
}
