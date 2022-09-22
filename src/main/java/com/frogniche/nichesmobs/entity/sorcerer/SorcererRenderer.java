package com.frogniche.nichesmobs.entity.sorcerer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SorcererRenderer extends GeoEntityRenderer<Sorcerer> {
    public SorcererRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SorcererModel());
        this.shadowRadius = 0.2f;
    }

    @Override
    public ResourceLocation getTextureLocation(Sorcerer instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}