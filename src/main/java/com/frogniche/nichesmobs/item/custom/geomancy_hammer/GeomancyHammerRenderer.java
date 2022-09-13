package com.frogniche.nichesmobs.item.custom.geomancy_hammer;

import software.bernie.example.client.model.item.JackInTheBoxModel;
import software.bernie.example.item.JackInTheBoxItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class GeomancyHammerRenderer extends GeoItemRenderer<GeomancyHammerItem> {
    public GeomancyHammerRenderer() {
        super(new GeomancyHammerModel());
    }
}
