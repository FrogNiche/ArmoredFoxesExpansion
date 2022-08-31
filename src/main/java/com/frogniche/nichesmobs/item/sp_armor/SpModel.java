package com.frogniche.nichesmobs.item.sp_armor;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpModel extends AnimatedGeoModel<SpArmorItem> {
    @Override
    public ResourceLocation getModelResource(SpArmorItem object) {
        return NichesMobs.modLoc("geo/sp.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpArmorItem object) {
        return NichesMobs.modLoc("textures/entity/");
    }

    @Override
    public ResourceLocation getAnimationResource(SpArmorItem animatable) {
        return null;
    }
}
