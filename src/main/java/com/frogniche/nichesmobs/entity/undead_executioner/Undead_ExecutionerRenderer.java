package com.frogniche.nichesmobs.entity.undead_executioner;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class Undead_ExecutionerRenderer extends GeoEntityRenderer<Undead_ExecutionerEntity> {
    public Undead_ExecutionerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Undead_ExecutionerModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(Undead_ExecutionerEntity instance) {
        return this.modelProvider.getTextureResource(instance);
    }

}