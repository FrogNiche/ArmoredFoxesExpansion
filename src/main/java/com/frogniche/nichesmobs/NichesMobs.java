package com.frogniche.nichesmobs;
import com.frogniche.nichesmobs.effect.ModEffects;
import com.frogniche.nichesmobs.entity.EntityInit;
import com.frogniche.nichesmobs.entity.badger.crimsom_badger.CrimsomBadger;
import com.frogniche.nichesmobs.entity.badger.crimsom_badger.CrimsomBadgerRenderer;
import com.frogniche.nichesmobs.entity.badger.warped_badger.WarpedBadger;
import com.frogniche.nichesmobs.entity.badger.warped_badger.WarpedBadgerRenderer;
import com.frogniche.nichesmobs.entity.cauldron.Cauldron;
import com.frogniche.nichesmobs.entity.cauldron.CauldronRenderer;
import com.frogniche.nichesmobs.entity.elite_paws.ElitePaws;
import com.frogniche.nichesmobs.entity.elite_paws.ElitePawsRenderer;
import com.frogniche.nichesmobs.entity.end_frog.EndFrog;
import com.frogniche.nichesmobs.entity.end_frog.EndFrogRenderer;
import com.frogniche.nichesmobs.entity.end_tadpole.EndTadpole;
import com.frogniche.nichesmobs.entity.end_tadpole.EndTadpoleRenderer;
import com.frogniche.nichesmobs.entity.françois.Francois;
import com.frogniche.nichesmobs.entity.françois.FrancoisRenderer;
import com.frogniche.nichesmobs.entity.furry.FurryRenderer;
import com.frogniche.nichesmobs.entity.guard.GuardEntity;
import com.frogniche.nichesmobs.entity.guard.GuardRenderer;
import com.frogniche.nichesmobs.entity.n_cauldron.NCauldron;
import com.frogniche.nichesmobs.entity.n_cauldron.NCauldronRenderer;
import com.frogniche.nichesmobs.entity.savager.SavagerGuardEntity;
import com.frogniche.nichesmobs.entity.savager.SavagerGuardRenderer;
import com.frogniche.nichesmobs.entity.snow_mole.EntitySnowMole;
import com.frogniche.nichesmobs.entity.snow_mole.SnowMoleRenderer;
import com.frogniche.nichesmobs.entity.sorcerer.Sorcerer;
import com.frogniche.nichesmobs.entity.sorcerer.SorcererRenderer;
import com.frogniche.nichesmobs.entity.sp.SPEntity;
import com.frogniche.nichesmobs.entity.sp.SPRenderer;
import com.frogniche.nichesmobs.entity.spores_chief.SporesChiefEntity;
import com.frogniche.nichesmobs.entity.spores_chief.SporesChiefModel;
import com.frogniche.nichesmobs.entity.ud.UDEntity;
import com.frogniche.nichesmobs.entity.ud.UDRenderer;
import com.frogniche.nichesmobs.entity.undead_executioner.Undead_ExecutionerEntity;
import com.frogniche.nichesmobs.entity.undead_executioner.Undead_ExecutionerRenderer;
import com.frogniche.nichesmobs.entity.wavy.WavyGeo;
import com.frogniche.nichesmobs.entity.wavy.WavyRenderer;
import com.frogniche.nichesmobs.entity.wolfie.WolfieEntity;
import com.frogniche.nichesmobs.entity.wolfie.WolfieRenderer;
import com.frogniche.nichesmobs.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@Mod(NichesMobs.MOD_ID)
public class NichesMobs
{
    public static final String MOD_ID = "nichesmobs";

    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation modLoc(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public NichesMobs() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Add to the constructor
        ModItems.register(modEventBus);
        ModEffects.register(modEventBus);
        EntityInit.ENTITIES.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerEntityAttributes);
        modEventBus.addListener(this::clientSetup);
        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }
    private void clientSetup(FMLClientSetupEvent event){
        EntityRenderers.register(EntityInit.SNOW_MOLE.get(), SnowMoleRenderer::new);
        EntityRenderers.register(EntityInit.END_TADPOLE.get(), EndTadpoleRenderer::new);
        EntityRenderers.register(EntityInit.FRANCOIS.get(), FrancoisRenderer::new);
        EntityRenderers.register(EntityInit.END_FROG.get(), EndFrogRenderer::new);
        //EntityRenderers.register(EntityInit.WARPED_BADGER.get(), WarpedBadgerRenderer::new);
        //EntityRenderers.register(EntityInit.CRIMSOM_BADGER.get(), CrimsomBadgerRenderer::new);
        EntityRenderers.register(EntityInit.SP.get(), SPRenderer::new);
        EntityRenderers.register(EntityInit.GUARD.get(), GuardRenderer::new);
        EntityRenderers.register(EntityInit.SORCERER.get(), SorcererRenderer::new);
        EntityRenderers.register(EntityInit.ELITE_PAWS.get(), ElitePawsRenderer::new);
        //EntityRenderers.register(EntityInit.WAVY_GEO.get(), WavyRenderer::new);
        EntityRenderers.register(EntityInit.FURRY.get(), FurryRenderer::new);
        EntityRenderers.register(EntityInit.WOLFIE.get(), WolfieRenderer::new);
        EntityRenderers.register(EntityInit.SNOW_MOLE.get(), SnowMoleRenderer::new);
        EntityRenderers.register(EntityInit.UD.get(), UDRenderer::new);
        EntityRenderers.register(EntityInit.UNDEAD_EXECUTIONER.get(), Undead_ExecutionerRenderer::new);
        EntityRenderers.register(EntityInit.N_CAUDRON.get(), NCauldronRenderer::new);
        //EntityRenderers.register(EntityInit.CAULDRON.get(), NCauldronRenderer::new);
        EntityRenderers.register(EntityInit.SAVAGER_GUARD.get(), SavagerGuardRenderer::new);
        EntityRenderers.register(EntityInit.SPORES_CHIEF.get(), makeRenderer(new SporesChiefModel()));
    }
    private void registerEntityAttributes(EntityAttributeCreationEvent event){
        event.put(EntityInit.SNOW_MOLE.get(), EntitySnowMole.createAttributes().build());
        event.put(EntityInit.FRANCOIS.get(), Francois.createAttributes().build());
        event.put(EntityInit.END_TADPOLE.get(), EndTadpole.createAttributes().build());
        event.put(EntityInit.END_FROG.get(), EndFrog.createAttributes().build());
        //event.put(EntityInit.CRIMSOM_BADGER.get(), CrimsomBadger.createAttributes().build());
        //event.put(EntityInit.WARPED_BADGER.get(), WarpedBadger.createAttributes().build());
        event.put(EntityInit.SP.get(), SPEntity.createAttributes());
        event.put(EntityInit.ELITE_PAWS.get(), ElitePaws.createAttributes());
        event.put(EntityInit.GUARD.get(), GuardEntity.createAttributes());
        event.put(EntityInit.SORCERER.get(), Sorcerer.createAttributes());
        event.put(EntityInit.FURRY.get(), SPEntity.createAttributes());
        event.put(EntityInit.WOLFIE.get(), WolfieEntity.createAttributes());
        event.put(EntityInit.UD.get(), UDEntity.createAttributes());
        event.put(EntityInit.UNDEAD_EXECUTIONER.get(), Undead_ExecutionerEntity.createAttributes());
        //event.put(EntityInit.WAVY_GEO.get(), WavyGeo.createAttributes());
        event.put(EntityInit.N_CAUDRON.get(), NCauldron.createAttributes());
        //event.put(EntityInit.CAULDRON.get(), Cauldron.createAttributes());
        event.put(EntityInit.SAVAGER_GUARD.get(), SavagerGuardEntity.createAttributes());
        event.put(EntityInit.SPORES_CHIEF.get(), SporesChiefEntity.makeAttributes());
    }

    public static <T extends LivingEntity & IAnimatable> EntityRendererProvider<T> makeRenderer(AnimatedGeoModel<T> model){
        return m -> new HelperGeoRenderer<>(m, model);
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }

    public static class HelperGeoRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T>{

        public HelperGeoRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel<T> modelProvider) {
            super(renderManager, modelProvider);
        }
    }
}
