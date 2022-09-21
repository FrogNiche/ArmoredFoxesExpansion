package com.frogniche.nichesmobs.entity.cauldron;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CauldronRenderer extends GeoEntityRenderer<Cauldron> {
    public CauldronRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CauldronModel());
    }
}
