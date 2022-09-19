package com.frogniche.nichesmobs.entity.françois;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FrancoisRenderer extends GeoEntityRenderer<Francois> {
    public FrancoisRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FrancoisModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(Francois instance) {
        return this.modelProvider.getTextureResource(instance);
    }
}
