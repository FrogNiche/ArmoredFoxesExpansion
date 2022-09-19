package com.frogniche.nichesmobs.entity.françois;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FrancoisModel extends AnimatedGeoModel<Francois> {
@Override
public ResourceLocation getModelResource(Francois object) {
        return NichesMobs.modLoc("geo/francois.geo.json");
        }

@Override
public ResourceLocation getTextureResource(Francois object) {
        return NichesMobs.modLoc("textures/entity/francois/franky.png");
        }

@Override
public ResourceLocation getAnimationResource(Francois animatable) {
        return NichesMobs.modLoc("animations/francois.animation.json");
        }
        }