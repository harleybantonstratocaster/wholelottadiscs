package com.example.wholelottadiscs.item.boombox;

import com.example.wholelottadiscs.ModContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;


public class BoomboxContainer extends AbstractContainerMenu {

        public BoomboxContainer(int windowId, Inventory playerInv,
                                ItemStackHandlerBoombox contents,
                                ItemStack held) {
                super(ModContainers.BOOMBOX.get(), windowId);
                this.addSlot(new SlotItemHandler(contents, 0, 81, 35));
                addPlayerHotbar(playerInv);
                addPlayerInventory(playerInv);

        }


        public static BoomboxContainer createContainerServerSide(int windowID, Inventory playerInventory,
                                                                 ItemStackHandlerBoombox bagContents,
                                                                 ItemStack jukebox) {
                return new BoomboxContainer(windowID, playerInventory, bagContents, jukebox);
        }

        public static BoomboxContainer createContainerClientSide(int windowID, Inventory playerInventory, FriendlyByteBuf extraData) {
                ItemStackHandlerBoombox handler = new ItemStackHandlerBoombox(1);
                return new BoomboxContainer(windowID, playerInventory, handler, ItemStack.EMPTY);
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
}