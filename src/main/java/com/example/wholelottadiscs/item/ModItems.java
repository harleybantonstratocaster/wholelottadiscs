package com.example.wholelottadiscs.item;

import com.example.wholelottadiscs.Creativetab;
import com.example.wholelottadiscs.WholeLottaDiscs;
import com.example.wholelottadiscs.item.boombox.Boombox;
import com.example.wholelottadiscs.item.discscollection.DiscsCollection;
import com.example.wholelottadiscs.item.recordsleeve.RecordSleeve;
import com.example.wholelottadiscs.item.recordsleeve.sounds.Sounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WholeLottaDiscs.MOD_ID);

    public static final RegistryObject<Item> BOOMBOX = ITEMS.register("boombox",
            () -> new Boombox(new Item.Properties().tab(Creativetab.TAB).stacksTo(1)));
    public static final RegistryObject<Item> RECORD_SLEEVE = ITEMS.register("record_sleeve",
            () -> new RecordSleeve(new Item.Properties().tab(Creativetab.TAB ).stacksTo(1)));

    public static final RegistryObject<Item> DISCS_COLLECTION = ITEMS.register("discs_collection",
            () -> new DiscsCollection(new Item.Properties().tab(Creativetab.TAB )));
    public static final RegistryObject<Item> DOORS = ITEMS.register("doors",
            () -> new RecordItem(4, Sounds.DOORS,new Item.Properties().tab(Creativetab.TAB ).stacksTo(1),149));
    public static final RegistryObject<Item> SOAD = ITEMS.register("soad",
            () -> new RecordItem(4, Sounds.SOAD,new Item.Properties().tab(Creativetab.TAB ).stacksTo(1),219));
    public static final RegistryObject<Item> AGATHA = ITEMS.register("agatha",
            () -> new RecordItem(4, Sounds.AGATHA,new Item.Properties().tab(Creativetab.TAB ).stacksTo(1),219));
    public static final RegistryObject<Item> MCR = ITEMS.register("mcr",
            () -> new RecordItem(4, Sounds.MCR,new Item.Properties().tab(Creativetab.TAB ).stacksTo(1),219));
    public static final RegistryObject<Item> METALLICA = ITEMS.register("metallica",
            () -> new RecordItem(4, Sounds.METALLICA,new Item.Properties().tab(Creativetab.TAB ).stacksTo(1),219));
    public static final RegistryObject<Item> PANTERA = ITEMS.register("pantera",
            () -> new RecordItem(4, Sounds.PANTERA,new Item.Properties().tab(Creativetab.TAB ).stacksTo(1),219));
    public static final RegistryObject<Item> SLAYER = ITEMS.register("slayer",
            () -> new RecordItem(4, Sounds.SLAYER,new Item.Properties().tab(Creativetab.TAB ).stacksTo(1),219));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
