package com.frogniche.nichesmobs.entity.ud;
import com.frogniche.nichesmobs.entity.wolfie.WolfieModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class UDRenderer extends GeoEntityRenderer<UDEntity> {
    public UDRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new UDModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(UDEntity instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}