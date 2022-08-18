package com.frogniche.nichesmobs.entity.wolfie;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WolfieRenderer extends GeoEntityRenderer<WolfieEntity> {
    public WolfieRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WolfieModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(WolfieEntity instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}