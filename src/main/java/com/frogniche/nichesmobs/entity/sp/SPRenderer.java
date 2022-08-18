package com.frogniche.nichesmobs.entity.sp;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SPRenderer extends GeoEntityRenderer<SPEntity> {
    public SPRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SPModel());
        this.shadowRadius = 0.2f;
    }

    @Override
    public ResourceLocation getTextureLocation(SPEntity instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}