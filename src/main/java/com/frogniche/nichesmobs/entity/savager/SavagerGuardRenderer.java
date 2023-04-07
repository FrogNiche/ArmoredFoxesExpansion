package com.frogniche.nichesmobs.entity.savager;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SavagerGuardRenderer extends GeoEntityRenderer<SavagerGuardEntity> {
    public SavagerGuardRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SavagerGuardModel());
    }
}
