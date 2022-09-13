package com.frogniche.nichesmobs.entity.badger.warped_badger;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WarpedBadgerRenderer extends GeoEntityRenderer<WarpedBadger> {
    public WarpedBadgerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WarpedBadgerModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(WarpedBadger instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}