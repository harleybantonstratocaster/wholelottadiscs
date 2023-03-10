package com.example.wholelottadiscs;

import com.example.wholelottadiscs.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class Creativetab {
    public static final CreativeModeTab TAB = new CreativeModeTab("discstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BOOMBOX.get());
        }
    };
}
