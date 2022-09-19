package com.frogniche.nichesmobs.item.custom.whip;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WhipModel extends AnimatedGeoModel<Corrupted_Whip> {
    @Override
    public ResourceLocation getModelResource(Corrupted_Whip object) {
        return new ResourceLocation(NichesMobs.MOD_ID, "geo/corrupted_whip.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Corrupted_Whip object) {
        return new ResourceLocation(NichesMobs.MOD_ID, "textures/item/whip.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Corrupted_Whip animatable) {
        return new ResourceLocation(NichesMobs.MOD_ID, "animations/corrupted_whip.animation.json");
    }
}
