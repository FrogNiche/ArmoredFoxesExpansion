package com.frogniche.nichesmobs.armor;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class NetheriteCauldronRenderer extends GeoArmorRenderer<NetheriteCauldronArmorItem> {
    public NetheriteCauldronRenderer() {
        super(new NetheriteCauldronArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
    }
}
