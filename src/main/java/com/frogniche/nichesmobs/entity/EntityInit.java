package com.frogniche.nichesmobs.entity;

import com.frogniche.nichesmobs.NichesMobs;
import com.frogniche.nichesmobs.entity.badger.crimsom_badger.CrimsomBadger;
import com.frogniche.nichesmobs.entity.badger.warped_badger.WarpedBadger;
import com.frogniche.nichesmobs.entity.furry.FurryEntity;
import com.frogniche.nichesmobs.entity.n_cauldron.NCauldron;
import com.frogniche.nichesmobs.entity.snow_mole.EntitySnowMole;
import com.frogniche.nichesmobs.entity.sp.SPEntity;
import com.frogniche.nichesmobs.entity.ud.UDEntity;
import com.frogniche.nichesmobs.entity.undead_executioner.Undead_ExecutionerEntity;
import com.frogniche.nichesmobs.entity.wolfie.WolfieEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.
                    ENTITY_TYPES,
            NichesMobs.MOD_ID);

    public static final RegistryObject<EntityType<EntitySnowMole>> SNOW_MOLE = register("snow_mole",
            EntityType.Builder.<EntitySnowMole>of(EntitySnowMole::new,  MobCategory.AMBIENT));

    public static final RegistryObject<EntityType<SPEntity>> SP = register("sp",
            EntityType.Builder.<SPEntity>of(SPEntity::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<WolfieEntity>> WOLFIE = register("wolfie",
            EntityType.Builder.<WolfieEntity>of(WolfieEntity::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<Undead_ExecutionerEntity>> UNDEAD_EXECUTIONER = register("undead_executioner",
            EntityType.Builder.<Undead_ExecutionerEntity>of(Undead_ExecutionerEntity::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<UDEntity>> UD = register("ud",
            EntityType.Builder.<UDEntity>of(UDEntity::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<FurryEntity>> FURRY = register("furry",
            EntityType.Builder.<FurryEntity>of(FurryEntity::new,  MobCategory.MONSTER));
    public static final RegistryObject<EntityType<WarpedBadger>> WARPED_BADGER = register("warped_badger",
            EntityType.Builder.<WarpedBadger>of(WarpedBadger::new,  MobCategory.AMBIENT));
    public static final RegistryObject<EntityType<CrimsomBadger>> CRIMSOM_BADGER = register("crimsom_badger",
            EntityType.Builder.<CrimsomBadger>of(CrimsomBadger::new,  MobCategory.AMBIENT));
    public static final RegistryObject<EntityType<NCauldron>> N_CAUDRON = register("n_cauldron",
            EntityType.Builder.of(NCauldron::new, MobCategory.MONSTER).sized(2, 2f));

    public static final <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builder){
        return ENTITIES.register(name, () -> builder.build(NichesMobs.modLoc(name).toString()));
    }
}