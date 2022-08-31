package com.frogniche.nichesmobs.entity.n_cauldron;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NCauldronRenderer extends GeoEntityRenderer<NCauldron> {
    public NCauldronRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NCauldronModel());
    }
}
