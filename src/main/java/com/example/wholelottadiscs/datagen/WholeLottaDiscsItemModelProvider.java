package com.example.wholelottadiscs.datagen;

import com.example.wholelottadiscs.WholeLottaDiscs;
import com.example.wholelottadiscs.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class WholeLottaDiscsItemModelProvider extends ItemModelProvider {
    public WholeLottaDiscsItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, WholeLottaDiscs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.AGATHA);
        simpleItem(ModItems.DOORS);
        simpleItem(ModItems.MCR);
        simpleItem(ModItems.METALLICA);
        simpleItem(ModItems.PANTERA);
        simpleItem(ModItems.AUKCION);
        simpleItem(ModItems.DISCS_COLLECTION);
        simpleItem(ModItems.NECO);
        simpleItem(ModItems.SLAYER);
        simpleItem(ModItems.SOAD);
        simpleItem(ModItems.VEDRO);
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(WholeLottaDiscs.MOD_ID,"item/" + item.getId().getPath()));
    }
}
