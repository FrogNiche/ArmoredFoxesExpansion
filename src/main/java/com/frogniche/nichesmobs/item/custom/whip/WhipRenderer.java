package com.frogniche.nichesmobs.item.custom.whip;

import software.bernie.example.client.model.item.JackInTheBoxModel;
import software.bernie.example.item.JackInTheBoxItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class WhipRenderer extends GeoItemRenderer<Corrupted_Whip> {
    public WhipRenderer() {
        super(new WhipModel());
    }
}

