package com.example.wholelottadiscs;

import com.example.wholelottadiscs.item.boombox.BoomboxContainer;
import com.example.wholelottadiscs.item.discscollection.DiscsCollectionContainer;
import com.example.wholelottadiscs.item.recordsleeve.RecordSleeveContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, WholeLottaDiscs.MOD_ID);

    public static final RegistryObject<MenuType<BoomboxContainer>> BOOMBOX =
            registerMenuType(BoomboxContainer::createContainerClientSide, "boombox_menu");

    public static final RegistryObject<MenuType<RecordSleeveContainer>> RECORD_SLEEVE =
            registerMenuType(RecordSleeveContainer::createContainerClientSide, "record_sleeve_menu");

    public static final RegistryObject<MenuType<DiscsCollectionContainer>> DISCS_COLLECTION =
            registerMenuType(DiscsCollectionContainer::createContainerClientSide, "discs_collection_menu");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
