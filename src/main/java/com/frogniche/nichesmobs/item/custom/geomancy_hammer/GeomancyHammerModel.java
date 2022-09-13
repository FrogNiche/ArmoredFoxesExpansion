package com.frogniche.nichesmobs.item.custom.geomancy_hammer;

import com.frogniche.nichesmobs.NichesMobs;
import com.frogniche.nichesmobs.item.custom.geomancy_hammer.GeomancyHammerItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeomancyHammerModel extends AnimatedGeoModel<GeomancyHammerItem> {
    @Override
    public ResourceLocation getModelResource(GeomancyHammerItem object) {
        return new ResourceLocation(NichesMobs.MOD_ID, "geo/geomancy_hammer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeomancyHammerItem object) {
        return new ResourceLocation(NichesMobs.MOD_ID, "textures/item/hammer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeomancyHammerItem animatable) {
        return new ResourceLocation(NichesMobs.MOD_ID, "animations/waving.json");
    }
}
