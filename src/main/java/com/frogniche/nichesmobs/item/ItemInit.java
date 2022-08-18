package com.frogniche.nichesmobs.item;

import com.frogniche.nichesmobs.NichesMobs;
import com.frogniche.nichesmobs.entity.EntityInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NichesMobs.MOD_ID);

    public static final RegistryObject<Item> SNOW_MOLE_SPAWN_EGG = ITEMS.register("snow_mole_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.SNOW_MOLE,0xcccccc, 0xcbbeb5,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> LOGO = ITEMS.register("logo",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> WOLFIE_SPAWN_EGG = ITEMS.register("wolfie_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.WOLFIE,0xF3E8DD, 0xD3D3D3,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> SP_SPAWN_EGG = ITEMS.register("sp_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.SP,0xCB8B16, 0xDDCE17,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> FURRY_SPAWN_EGG = ITEMS.register("furry_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.FURRY,0x3e2723, 0xef6c00,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> UD_SPAWN_EGG = ITEMS.register("ud_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.UD,0x1f1f1f, 0x000000,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
