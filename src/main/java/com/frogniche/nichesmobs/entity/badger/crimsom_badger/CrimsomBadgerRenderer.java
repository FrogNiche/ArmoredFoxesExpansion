package com.frogniche.nichesmobs.entity.badger.crimsom_badger;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CrimsomBadgerRenderer extends GeoEntityRenderer<CrimsomBadger>{
        public CrimsomBadgerRenderer(EntityRendererProvider.Context renderManager) {
            super(renderManager, new CrimsomBadgerModel());
            this.shadowRadius = 0.3f;
        }

        @Override
        public ResourceLocation getTextureLocation(CrimsomBadger instance) {
            return this.modelProvider.getTextureResource(instance);
        }

    }

