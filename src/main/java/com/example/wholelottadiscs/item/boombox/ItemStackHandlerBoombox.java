package com.example.wholelottadiscs.item.boombox;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class ItemStackHandlerBoombox extends ItemStackHandler {


    public ItemStackHandlerBoombox(int numSlots){
        super(numSlots);
    }


    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return stack.getItem() instanceof RecordItem;
    }
}
