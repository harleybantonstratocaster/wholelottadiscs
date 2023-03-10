package com.example.wholelottadiscs.item.discscollection;

import com.example.wholelottadiscs.item.boombox.ItemStackHandlerBoombox;
import com.example.wholelottadiscs.item.recordsleeve.RecordSleeve;
import com.example.wholelottadiscs.item.recordsleeve.RecordSleeveCapabilityProvider;
import com.example.wholelottadiscs.item.recordsleeve.RecordSleeveContainer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class DiscsCollection extends Item {
    public DiscsCollection(Properties pProperties) {
        super(pProperties);
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide) {
            MenuProvider containerProviderDiscsCollection = new DiscsCollection.ContainerProviderDiscsCollection(stack);
            final int NUMBER_OF_SLOTS = 9;
            NetworkHooks.openScreen((ServerPlayer) pPlayer,
                    containerProviderDiscsCollection,
                    (packetBuffer)->{packetBuffer.writeInt(NUMBER_OF_SLOTS);});
        }
        return super.use(pLevel,pPlayer,pUsedHand);
    }

    private static class ContainerProviderDiscsCollection implements MenuProvider {

        private final ItemStack itemStackDiscsContainer;

        public ContainerProviderDiscsCollection(ItemStack itemStackDiscsCollection) {
            this.itemStackDiscsContainer = itemStackDiscsCollection;
        }

        @Override
        public Component getDisplayName() {
            return itemStackDiscsContainer.getDisplayName();
        }


        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
            return DiscsCollectionContainer.createContainerServerSide(pContainerId, pPlayerInventory,
                    getItemStackHandlerDiscsCollection(itemStackDiscsContainer),
                    itemStackDiscsContainer);
        }
    }
    private static ItemStackHandlerBoombox getItemStackHandlerDiscsCollection(ItemStack itemStack) {
        IItemHandler discsCollection = itemStack.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
        if (!(discsCollection instanceof ItemStackHandlerBoombox)) {
            return new ItemStackHandlerBoombox(9);
        }
        return (ItemStackHandlerBoombox) discsCollection;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new DiscsCollectionCapabilityProvider();

    }
}
