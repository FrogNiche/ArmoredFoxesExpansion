package com.frogniche.nichesmobs.entity.guard;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GuardRenderer extends GeoEntityRenderer<GuardEntity> {
    public GuardRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GuardModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(GuardEntity instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}