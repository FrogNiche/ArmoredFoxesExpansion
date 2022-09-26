package com.frogniche.nichesmobs.armor;
import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NetheriteCauldronArmorModel extends AnimatedGeoModel<NetheriteCauldronArmorItem> {
    @Override
    public ResourceLocation getModelResource(NetheriteCauldronArmorItem object) {
        return new ResourceLocation(NichesMobs.MOD_ID, "geo/netherite_cauldron_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NetheriteCauldronArmorItem object) {
        return new ResourceLocation(NichesMobs.MOD_ID, "textures/armor/cauldronarmor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NetheriteCauldronArmorItem animatable) {
        return new ResourceLocation(NichesMobs.MOD_ID, "animations/netherite_cauldron_animation.json");
    }
}