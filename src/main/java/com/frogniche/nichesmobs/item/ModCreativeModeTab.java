package com.frogniche.nichesmobs.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab NICHES_TAB = new CreativeModeTab("nichestab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.LOGO.get());
        }
    };
}