package com.frogniche.nichesmobs.entity;

import com.frogniche.nichesmobs.NichesMobs;
import com.frogniche.nichesmobs.entity.badger.crimsom_badger.CrimsomBadger;
import com.frogniche.nichesmobs.entity.badger.warped_badger.WarpedBadger;
import com.frogniche.nichesmobs.entity.bone_chief.BoneHordeChief;
import com.frogniche.nichesmobs.entity.bone_grunter.BoneGrunter;
import com.frogniche.nichesmobs.entity.elite_paws.ElitePaws;
import com.frogniche.nichesmobs.entity.end_frog.EndFrog;
import com.frogniche.nichesmobs.entity.end_tadpole.EndTadpole;
import com.frogniche.nichesmobs.entity.furry.FurryEntity;
import com.frogniche.nichesmobs.entity.guard.GuardEntity;
import com.frogniche.nichesmobs.entity.n_cauldron.NCauldron;
import com.frogniche.nichesmobs.entity.potted_paws.PottedPaws;
import com.frogniche.nichesmobs.entity.runt.Runt;
import com.frogniche.nichesmobs.entity.savager.SavagerGuardEntity;
import com.frogniche.nichesmobs.entity.saw_paws.SawPaws;
import com.frogniche.nichesmobs.entity.snow_mole.EntitySnowMole;
import com.frogniche.nichesmobs.entity.sorcerer.Sorcerer;
import com.frogniche.nichesmobs.entity.sp.SPEntity;
import com.frogniche.nichesmobs.entity.sp_medic.SPMedic;
import com.frogniche.nichesmobs.entity.sp_medic.SpMedicModel;
import com.frogniche.nichesmobs.entity.spores_chief.SporesChiefEntity;
import com.frogniche.nichesmobs.entity.ud.UDEntity;
import com.frogniche.nichesmobs.entity.undead_executioner.Undead_ExecutionerEntity;
import com.frogniche.nichesmobs.entity.wavy.WavyGeo;
import com.frogniche.nichesmobs.entity.wolfie.WolfieEntity;
import com.frogniche.nichesmobs.entity.françois.Francois;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.geckolib3.geo.raw.pojo.Bone;

public class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.
                    ENTITY_TYPES,
            NichesMobs.MOD_ID);

    public static final RegistryObject<EntityType<EntitySnowMole>> SNOW_MOLE = register("snow_mole",
            EntityType.Builder.<EntitySnowMole>of(EntitySnowMole::new,  MobCategory.AMBIENT));

    public static final RegistryObject<EntityType<Francois>> FRANCOIS = register("francois",
            EntityType.Builder.<Francois>of(Francois::new,  MobCategory.AMBIENT));

    public static final RegistryObject<EntityType<EndFrog>> END_FROG = register("end_frog",
            EntityType.Builder.<EndFrog>of(EndFrog::new, MobCategory.CREATURE).sized(0.5F, 0.5F).clientTrackingRange(10));

    public static final RegistryObject<EntityType<SPEntity>> SP = register("sp",
            EntityType.Builder.<SPEntity>of(SPEntity::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<GuardEntity>> GUARD = register("guard",
            EntityType.Builder.<GuardEntity>of(GuardEntity::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<WolfieEntity>> WOLFIE = register("wolfie",
            EntityType.Builder.<WolfieEntity>of(WolfieEntity::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<EndTadpole>> END_TADPOLE = register("end_tadpole",
            EntityType.Builder.<EndTadpole>of(EndTadpole::new,  MobCategory.CREATURE));

    public static final RegistryObject<EntityType<Undead_ExecutionerEntity>> UNDEAD_EXECUTIONER = register("undead_executioner",
            EntityType.Builder.<Undead_ExecutionerEntity>of(Undead_ExecutionerEntity::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<UDEntity>> UD = register("ud",
            EntityType.Builder.<UDEntity>of(UDEntity::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<FurryEntity>> FURRY = register("furry",
            EntityType.Builder.<FurryEntity>of(FurryEntity::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<Sorcerer>> SORCERER = register("sorcerer",
            EntityType.Builder.<Sorcerer>of(Sorcerer::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<ElitePaws>> ELITE_PAWS = register("elite_paws",
            EntityType.Builder.<ElitePaws>of(ElitePaws::new,  MobCategory.MONSTER));
    public static final RegistryObject<EntityType<SavagerGuardEntity>> SAVAGER_GUARD = register("savager_guard", EntityType.Builder.of(SavagerGuardEntity::new, MobCategory.MONSTER));
    public static final RegistryObject<EntityType<SporesChiefEntity>> SPORES_CHIEF = register("spores_chief", EntityType.Builder.of(SporesChiefEntity::new, MobCategory.MONSTER).sized(2f, 7f));
    public static final RegistryObject<EntityType<SPMedic>> SP_MEDIC = register("sp_medic", EntityType.Builder.of(SPMedic::new, MobCategory.AMBIENT).sized(1f, 1.5f));
    public static final RegistryObject<EntityType<PottedPaws>> POTTED_PAWS = register("potted_paws", EntityType.Builder.of(PottedPaws::new, MobCategory.AMBIENT).sized(1f, 1.5f));
    public static final RegistryObject<EntityType<BoneHordeChief>> BONE_HORDE_CHIEF = register("bone_horde_chief", EntityType.Builder.of(BoneHordeChief::new, MobCategory.AMBIENT).sized(1f, 1.5f));
    public static final RegistryObject<EntityType<Runt>> RUNT = register("runt", EntityType.Builder.of(Runt::new, MobCategory.AMBIENT).sized(1f, 1.5f));
    public static final RegistryObject<EntityType<SawPaws>> SAW_PAWS = register("saw_paws", EntityType.Builder.of(SawPaws::new, MobCategory.AMBIENT).sized(1f, 1.5f));
    public static final RegistryObject<EntityType<BoneGrunter>> BONE_GRUNTER = register("bone_grunter", EntityType.Builder.of(BoneGrunter::new, MobCategory.AMBIENT).sized(1f, 1.5f));

    //public static final RegistryObject<EntityType<WavyGeo>> WAVY_GEO = register("wavy_geo", EntityType.Builder.<WavyGeo>of(WavyGeo::new,  MobCategory.MONSTER));
    //public static final RegistryObject<EntityType<WarpedBadger>> WARPED_BADGER = register("warped_badger", EntityType.Builder.<WarpedBadger>of(WarpedBadger::new,  MobCategory.AMBIENT));
    //public static final RegistryObject<EntityType<CrimsomBadger>> CRIMSOM_BADGER = register("crimsom_badger", EntityType.Builder.<CrimsomBadger>of(CrimsomBadger::new,  MobCategory.AMBIENT));
    //public static final RegistryObject<EntityType<NCauldron>> CAULDRON = register("cauldron", EntityType.Builder.of(NCauldron::new, MobCategory.MONSTER).sized(2, 2f));


    public static final RegistryObject<EntityType<NCauldron>> N_CAUDRON = register("n_cauldron",EntityType.Builder.of(NCauldron::new, MobCategory.MONSTER).sized(2, 2f));


    public static final <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builder){
        return ENTITIES.register(name, () -> builder.build(NichesMobs.modLoc(name).toString()));
    }
}