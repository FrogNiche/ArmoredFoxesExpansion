package com.frogniche.nichesmobs.entity.furry;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FurryRenderer extends GeoEntityRenderer<FurryEntity> {
    public FurryRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FurryModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(FurryEntity instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}