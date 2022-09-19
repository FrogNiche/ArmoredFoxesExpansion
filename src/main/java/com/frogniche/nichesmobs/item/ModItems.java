package com.frogniche.nichesmobs.item;

import com.frogniche.nichesmobs.NichesMobs;
import com.frogniche.nichesmobs.entity.EntityInit;
import com.frogniche.nichesmobs.item.custom.*;
import com.frogniche.nichesmobs.item.custom.whip.Corrupted_Whip;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NichesMobs.MOD_ID);
// The Snow-Mole
    public static final RegistryObject<Item> SNOW_MOLE_SPAWN_EGG = ITEMS.register("snow_mole_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.SNOW_MOLE,0xcccccc, 0xcbbeb5,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

 public static final RegistryObject<Item> NIGHTMARES_FANGS = ITEMS.register("nightmares_fangs",
         () -> new Nightmares_FangsItem(Tiers.NETHERITE, 10, 9f,
                 new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));
// WHAT'S GOING ON????

    public static final RegistryObject<Item> VA = ITEMS.register("va",
            () -> new VAItem(Tiers.NETHERITE, 10, 9f,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> ICE_HAMMER = ITEMS.register("ice_hammer",
            () -> new IceHammer(Tiers.NETHERITE, 10, 9f,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB).fireResistant()));

    public static final RegistryObject<Item> CORRUPTED_WHIP = ITEMS.register("corrupted_whip",
            () -> new Corrupted_Whip(Tiers.NETHERITE, 10, 9f,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB).fireResistant()));
 public static final RegistryObject<Item> TASTY_BONE = ITEMS.register("tasty_bone",
         () -> new UnbreakableSword(Tiers.NETHERITE, 15, 9f,
                 new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB).fireResistant()));

    public static final RegistryObject<Item> GEOMANCY_HAMMER = ITEMS.register("geomancy_hammer",
            () -> new PickaxeItem(Tiers.NETHERITE, 1, -2.8F,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB).fireResistant()));

    public static final RegistryObject<Item> LOGO = ITEMS.register("logo",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));

    public static final RegistryObject<Item> SOUL_GAUNTLET = ITEMS.register("soul_gauntlet",
            () -> new Soul_GauntletItem(new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> MF = ITEMS.register("mf",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> WOLFIE_SPAWN_EGG = ITEMS.register("wolfie_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.WOLFIE,0xF3E8DD, 0xD3D3D3,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> E_FROG_SPAWN_EGG = ITEMS.register("e_frog_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.END_FROG,0xe1d7e1, 0x3a143a,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));
// The Spawn Egg Edition!
    public static final RegistryObject<Item> E_T_SPAWN_EGG = ITEMS.register("e_t_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.END_TADPOLE,0x3a143a, 0xe3e29f,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> N_SPAWN_EGG = ITEMS.register("n_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.N_CAUDRON, 0x1d1819, 0x3e3a3e,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> WB_SPAWN_EGG = ITEMS.register("wb_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.WARPED_BADGER, 0x06564c, 0x187d84,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> CB_SPAWN_EGG = ITEMS.register("cb_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.CRIMSOM_BADGER, 0x4f1e1c, 0x7e1d1d,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> UNDEAD_EXECUTIONER_SPAWN_EGG = ITEMS.register("undead_executioner_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.UNDEAD_EXECUTIONER,0x8bc34a, 0x8bc34a,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> SP_SPAWN_EGG = ITEMS.register("sp_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.SP,0xCB8B16, 0xDDCE17,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> GUARD_SPAWN_EGG = ITEMS.register("guard_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.GUARD,0xCB8B16, 0xDDCE17,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> F_SPAWN_EGG = ITEMS.register("f_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.FRANCOIS,0xCB8B16, 0xDDCE17,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> FURRY_SPAWN_EGG = ITEMS.register("furry_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.FURRY,0x3e2723, 0xef6c00,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> UD_SPAWN_EGG = ITEMS.register("ud_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.UD,0x1f1f1f, 0x000000,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static final RegistryObject<Item> W_SPAWN_EGG = ITEMS.register("w_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.WAVY_GEO,0xaec77d, 0xd84315,
                    new Item.Properties().tab(ModCreativeModeTab.NICHES_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
