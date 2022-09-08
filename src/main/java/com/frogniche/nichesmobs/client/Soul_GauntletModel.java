package com.frogniche.nichesmobs.client;

import com.frogniche.nichesmobs.NichesMobs;
import com.frogniche.nichesmobs.item.custom.Soul_GauntletItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Soul_GauntletModel extends AnimatedGeoModel<Soul_GauntletItem> {


    @Override
    public ResourceLocation getModelResource(Soul_GauntletItem object) {
        return new ResourceLocation(NichesMobs.MOD_ID, "geo/soul_gauntlet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Soul_GauntletItem object) {
        return new ResourceLocation(NichesMobs.MOD_ID, "textures/item/gauntlet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Soul_GauntletItem animatable) {
        return new ResourceLocation(NichesMobs.MOD_ID, "animations/soul_gauntlet.animation.json");
    }
}