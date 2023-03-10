package com.example.wholelottadiscs.event;

import com.example.wholelottadiscs.ModContainers;
import com.example.wholelottadiscs.WholeLottaDiscs;
import com.example.wholelottadiscs.item.ModItems;
import com.example.wholelottadiscs.item.boombox.BoomboxScreen;
import com.example.wholelottadiscs.item.discscollection.DiscsCollectionScreen;
import com.example.wholelottadiscs.item.recordsleeve.RecordSleeve;
import com.example.wholelottadiscs.item.recordsleeve.RecordSleeveScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WholeLottaDiscs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEvents {

    private ModEvents() {
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ModContainers.BOOMBOX.get(), BoomboxScreen::new);
        MenuScreens.register(ModContainers.RECORD_SLEEVE.get(), RecordSleeveScreen::new);
        MenuScreens.register(ModContainers.DISCS_COLLECTION.get(), DiscsCollectionScreen::new);

        event.enqueueWork(() ->
        {
            ItemProperties.register(ModItems.RECORD_SLEEVE.get(),
                    new ResourceLocation(WholeLottaDiscs.MOD_ID, "empty"),
                    (pStack, pLevel, pEntity, pSeed) -> RecordSleeve.getEmptinessProperty(pStack,pLevel,pEntity,pSeed));
        });
    }
}