package com.frogniche.nichesmobs;
import com.frogniche.nichesmobs.effect.ModEffects;
import com.frogniche.nichesmobs.entity.EntityInit;
import com.frogniche.nichesmobs.entity.furry.FurryRenderer;
import com.frogniche.nichesmobs.entity.snow_mole.EntitySnowMole;
import com.frogniche.nichesmobs.entity.snow_mole.SnowMoleRenderer;
import com.frogniche.nichesmobs.entity.sp.SPEntity;
import com.frogniche.nichesmobs.entity.sp.SPRenderer;
import com.frogniche.nichesmobs.entity.ud.UDEntity;
import com.frogniche.nichesmobs.entity.ud.UDRenderer;
import com.frogniche.nichesmobs.entity.undead_executioner.Undead_ExecutionerEntity;
import com.frogniche.nichesmobs.entity.undead_executioner.Undead_ExecutionerRenderer;
import com.frogniche.nichesmobs.entity.wolfie.WolfieEntity;
import com.frogniche.nichesmobs.entity.wolfie.WolfieRenderer;
import com.frogniche.nichesmobs.item.ItemInit;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

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
        ItemInit.register(modEventBus);
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
        EntityRenderers.register(EntityInit.SP.get(), SPRenderer::new);
        EntityRenderers.register(EntityInit.FURRY.get(), FurryRenderer::new);
        EntityRenderers.register(EntityInit.WOLFIE.get(), WolfieRenderer::new);
        EntityRenderers.register(EntityInit.SNOW_MOLE.get(), SnowMoleRenderer::new);
        EntityRenderers.register(EntityInit.UD.get(), UDRenderer::new);
        EntityRenderers.register(EntityInit.UNDEAD_EXECUTIONER.get(), Undead_ExecutionerRenderer::new);
    }
    private void registerEntityAttributes(EntityAttributeCreationEvent event){
        event.put(EntityInit.SNOW_MOLE.get(), EntitySnowMole.createAttributes().build());
        event.put(EntityInit.SP.get(), SPEntity.createAttributes());
        event.put(EntityInit.FURRY.get(), SPEntity.createAttributes());
        event.put(EntityInit.WOLFIE.get(), WolfieEntity.createAttributes());
        event.put(EntityInit.UD.get(), UDEntity.createAttributes());
        event.put(EntityInit.UNDEAD_EXECUTIONER.get(), Undead_ExecutionerEntity.createAttributes());
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
}
