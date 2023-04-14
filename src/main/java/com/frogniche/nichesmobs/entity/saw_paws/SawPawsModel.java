package com.frogniche.nichesmobs.entity.saw_paws;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Animal;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SawPawsModel extends AnimatedGeoModel<SawPaws> {
    @Override
    public ResourceLocation getModelResource(SawPaws object) {
        return NichesMobs.modLoc("geo/saw_paws.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SawPaws object) {
        return NichesMobs.modLoc("textures/entity/sawpaws.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SawPaws animatable) {
        return NichesMobs.modLoc("animations/saw_paws.animation.json");
    }
}
