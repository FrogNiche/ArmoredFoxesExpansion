package com.frogniche.nichesmobs.entity.elite_paws;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ElitePawsRenderer extends GeoEntityRenderer<ElitePaws> {
    public ElitePawsRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ElitePawsModel());
        this.shadowRadius = 0.2f;
    }

    @Override
    public ResourceLocation getTextureLocation(ElitePaws instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}