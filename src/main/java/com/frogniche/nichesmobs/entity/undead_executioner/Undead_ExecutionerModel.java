package com.frogniche.nichesmobs.entity.undead_executioner;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

    public class Undead_ExecutionerModel extends AnimatedGeoModel<Undead_ExecutionerEntity> {
        @Override
        public ResourceLocation getModelResource(Undead_ExecutionerEntity object) {
            return NichesMobs.modLoc("geo/undead_executioner.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(Undead_ExecutionerEntity object) {
            return NichesMobs.modLoc("textures/entity/undead_executioner/undeadexecutioner.png");
        }

        @Override
        public ResourceLocation getAnimationResource(Undead_ExecutionerEntity animatable) {
            return NichesMobs.modLoc("animations/undead_executioner.animation.json");
        }
    }

