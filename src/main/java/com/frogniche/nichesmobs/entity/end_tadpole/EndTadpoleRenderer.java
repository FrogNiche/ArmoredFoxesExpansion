package com.frogniche.nichesmobs.entity.end_tadpole;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.security.cert.CertPathBuilder;

public class EndTadpoleRenderer extends GeoEntityRenderer<EndTadpole> {
    public EndTadpoleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EndTadpoleModel());
        this.shadowRadius = 0.3f;
    }

    public static CertPathBuilder createAttributes() {
        return null;
    }

    @Override
    public ResourceLocation getTextureLocation(EndTadpole instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}