package com.example.wholelottadiscs.item.discscollection;

import com.example.wholelottadiscs.ModContainers;
import com.example.wholelottadiscs.item.boombox.ItemStackHandlerBoombox;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class DiscsCollectionContainer extends AbstractContainerMenu {

    public DiscsCollectionContainer(int windowId, Inventory playerInv,
                                    ItemStackHandlerBoombox contents,
                                    ItemStack held) {
        super(ModContainers.DISCS_COLLECTION.get(), windowId);
        addInterfaceL(contents);
        addInterfaceR(contents);
        addPlayerHotbar(playerInv);
        addPlayerInventory(playerInv);

    }

    public static DiscsCollectionContainer createContainerServerSide(int windowID, Inventory playerInventory,
                                                                     ItemStackHandlerBoombox bagContents,
                                                                     ItemStack jukebox) {
        return new DiscsCollectionContainer(windowID, playerInventory, bagContents, jukebox);
    }

    public static DiscsCollectionContainer createContainerClientSide(int windowID, Inventory playerInventory, FriendlyByteBuf extraData) {
        ItemStackHandlerBoombox handler = new ItemStackHandlerBoombox(18);
        return new DiscsCollectionContainer(windowID, playerInventory, handler, ItemStack.EMPTY);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        var retStack = ItemStack.EMPTY;
        final Slot slot = getSlot(index);
        if (slot.hasItem()) {
            final ItemStack item = slot.getItem();
            retStack = item.copy();
            if (index < 27) {
                if (!moveItemStackTo(item, 27, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(item, 0, 27, false))
                return ItemStack.EMPTY;

            if (item.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return retStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }

    private void addInterfaceL(IItemHandler inventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 3; ++l) {
                this.addSlot(new SlotItemHandler(inventory, l + i * 3, 18 + l * 18, 15 + i * 18));
            }
        }

    }
    private void addInterfaceR(IItemHandler inventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 3; ++l) {
                this.addSlot(new SlotItemHandler(inventory, l + i * 3 + 9, 107 + l * 18, 15 + i * 18));
            }
        }

    }
}
