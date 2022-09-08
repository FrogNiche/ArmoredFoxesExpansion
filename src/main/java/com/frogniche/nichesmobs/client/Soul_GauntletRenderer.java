package com.frogniche.nichesmobs.client;

import com.frogniche.nichesmobs.item.custom.Soul_GauntletItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class Soul_GauntletRenderer extends GeoItemRenderer<Soul_GauntletItem> {
    public Soul_GauntletRenderer() {
        super(new Soul_GauntletModel());
    }
}