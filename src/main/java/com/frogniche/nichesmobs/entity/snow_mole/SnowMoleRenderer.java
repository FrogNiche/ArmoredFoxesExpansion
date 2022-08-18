package com.frogniche.nichesmobs.entity.snow_mole;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SnowMoleRenderer extends GeoEntityRenderer<EntitySnowMole> {
    public SnowMoleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SnowMoleModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySnowMole instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}