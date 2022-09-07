package com.frogniche.nichesmobs.entity.wavy;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WavyRenderer extends GeoEntityRenderer<WavyGeo> {
    public WavyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WavyModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(WavyGeo instance) {
        return this.modelProvider.getTextureResource(instance);
    }
}
