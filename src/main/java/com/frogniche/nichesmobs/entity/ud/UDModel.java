package com.frogniche.nichesmobs.entity.ud;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

    public class UDModel extends AnimatedGeoModel<UDEntity> {
        @Override
        public ResourceLocation getModelResource(UDEntity object) {
            return NichesMobs.modLoc("geo/ud.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(UDEntity object) {
            return NichesMobs.modLoc("textures/entity/ud/ud.png");
        }

        @Override
        public ResourceLocation getAnimationResource(UDEntity animatable) {
            return NichesMobs.modLoc("animations/ud.animation.json");
        }
    }

