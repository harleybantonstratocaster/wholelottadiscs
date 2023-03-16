package com.example.wholelottadiscs.item.recordsleeve;

import com.example.wholelottadiscs.item.ModItems;
import com.example.wholelottadiscs.item.boombox.ItemStackHandlerBoombox;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
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

public class RecordSleeve extends Item {
    public RecordSleeve(Properties pProperties) {
        super(pProperties);
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide) {
            MenuProvider containerProviderRecordSleeve = new ContainerProviderRecordSleeve(stack);
            final int NUMBER_OF_SLOTS = 1;
            NetworkHooks.openScreen((ServerPlayer) pPlayer,
                    containerProviderRecordSleeve,
                    (packetBuffer)->{packetBuffer.writeInt(NUMBER_OF_SLOTS);});
        }
        return super.use(pLevel,pPlayer,pUsedHand);
    }

    private static class ContainerProviderRecordSleeve implements MenuProvider {

        private final ItemStack itemStackRecordSleeve;

        public ContainerProviderRecordSleeve(ItemStack itemStackRecordSleeve) {
            this.itemStackRecordSleeve = itemStackRecordSleeve;
        }

        @Override
        public Component getDisplayName() {
            return itemStackRecordSleeve.getDisplayName();
        }


        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
            return RecordSleeveContainer.createContainerServerSide(pContainerId, pPlayerInventory,
                    getItemStackHandlerRecordSleeve(itemStackRecordSleeve),
                    itemStackRecordSleeve);
        }
    }

    private static ItemStackHandlerBoombox getItemStackHandlerRecordSleeve(ItemStack itemStack) {
        IItemHandler portableJukebox = itemStack.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
        if (!(portableJukebox instanceof ItemStackHandlerBoombox)) {
            return new ItemStackHandlerBoombox(1);
        }
        return (ItemStackHandlerBoombox) portableJukebox;
    }

    public static float getEmptinessProperty(ItemStack itemStack, @Nullable ClientLevel level,
                                             @Nullable LivingEntity livingEntity, int id){
        ItemStackHandlerBoombox recordsleeve = getItemStackHandlerRecordSleeve(itemStack);
        if (recordsleeve.getStackInSlot(0).getItem() == ModItems.SOAD.get()) {
            return 0.1F;
        }
        if (recordsleeve.getStackInSlot(0).getItem() == ModItems.MCR.get()){
            return 0.2F;
        }
        if (recordsleeve.getStackInSlot(0).getItem() == ModItems.PANTERA.get()){
            return 0.3F;
        }
        if (recordsleeve.getStackInSlot(0).getItem() == ModItems.SLAYER.get()){
            return 0.4F;
        }
        if (recordsleeve.getStackInSlot(0).getItem() == ModItems.METALLICA.get()){
            return 0.5F;
        }
        if (recordsleeve.getStackInSlot(0).getItem() == ModItems.AGATHA.get()){
            return 0.6F;
        }
        if (recordsleeve.getStackInSlot(0).getItem() == ModItems.DOORS.get()){
            return 0.7F;
        }
        if (recordsleeve.getStackInSlot(0).getItem() == ModItems.AUKCION.get()){
            return 0.8F;
        }
        if (recordsleeve.getStackInSlot(0).getItem() == ModItems.VEDRO.get()){
            return 0.9F;
        }
        if (recordsleeve.getStackInSlot(0).getItem() == ModItems.NECO.get()){
            return 1F;
        }
        return 0.0F;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new RecordSleeveCapabilityProvider();

    }
}
