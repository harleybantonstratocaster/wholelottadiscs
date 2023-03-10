package com.example.wholelottadiscs.item.boombox;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class Boombox extends Item {

    public boolean isPlaying = false;

    public Boombox(Properties pProperties) {
        super(pProperties);
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide) {
            MenuProvider containerProviderFlowerBag = new ContainerProviderBoombox(stack);
            final int NUMBER_OF_SLOTS = 1;
            NetworkHooks.openScreen((ServerPlayer) pPlayer,
                    containerProviderFlowerBag,
                    (packetBuffer)->{packetBuffer.writeInt(NUMBER_OF_SLOTS);});
        }
        return super.use(pLevel,pPlayer,pUsedHand);
    }


    private static class ContainerProviderBoombox implements MenuProvider {

        public ContainerProviderBoombox(ItemStack itemStackPortableJukebox) {
            this.itemStackPortableJukebox = itemStackPortableJukebox;
        }

        @Override
        public Component getDisplayName() {
            return itemStackPortableJukebox.getDisplayName();
        }


        private ItemStack itemStackPortableJukebox;

        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
            BoomboxContainer newContainerServerSide =
                    BoomboxContainer.createContainerServerSide(pContainerId, pPlayerInventory,
                            getItemStackHandlerBoombox(itemStackPortableJukebox),
                            itemStackPortableJukebox);
            return newContainerServerSide;
        }
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new BoomboxCapabilityProvider();


    }


    private static ItemStackHandlerBoombox getItemStackHandlerBoombox(ItemStack itemStack) {
        IItemHandler portableJukebox = itemStack.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
        if (!(portableJukebox instanceof ItemStackHandlerBoombox)) {
            return new ItemStackHandlerBoombox(1);
        }
        return (ItemStackHandlerBoombox) portableJukebox;
    }
    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        Item item = getItemStackHandlerBoombox(pStack).getStackInSlot(0).getItem();
       if (item instanceof RecordItem && !isPlaying){
           setPlaying(true,item,pLevel,pEntity);

       } else if (!(item instanceof RecordItem) && isPlaying) {
           setPlaying(false,item,pLevel,pEntity);
       }


    }
    public void setPlaying(boolean condition, Item item, Level level, Entity entity){

        Minecraft minecraft = Minecraft.getInstance().screen.getMinecraft();
        if (condition){
            minecraft.getSoundManager().play(new BoomboxSoundInstance((RecordItem) item,(Player) entity));
        }
        else {
            minecraft.getSoundManager().stop();
        }
        this.isPlaying = condition;
    }

}
