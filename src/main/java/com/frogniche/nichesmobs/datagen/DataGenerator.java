package com.frogniche.nichesmobs.datagen;

import com.frogniche.nichesmobs.NichesMobs;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = NichesMobs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerator {


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        net.minecraft.data.DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeClient())
            gatherClientData(gen, helper);
        if (event.includeServer())
            gatherServerData(gen, helper);
        try {
            gen.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gatherClientData(net.minecraft.data.DataGenerator gen, ExistingFileHelper helper) {
        gen.addProvider(true, new ModItemModelsProvider(gen, helper));
    }

    private static void gatherServerData(net.minecraft.data.DataGenerator gen, ExistingFileHelper helper) {

    }
}
