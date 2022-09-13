package com.frogniche.nichesmobs.entity.end_frog;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.security.cert.CertPathBuilder;

public class EndFrogRenderer extends GeoEntityRenderer<EndFrog> {
    public EndFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EndFrogModel());
        this.shadowRadius = 0.3f;
    }

    public static CertPathBuilder createAttributes() {
        return null;
    }

    @Override
    public ResourceLocation getTextureLocation(EndFrog instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}