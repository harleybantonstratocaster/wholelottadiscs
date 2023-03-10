package com.example.wholelottadiscs.item.boombox;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BoomboxCapabilityProvider implements ICapabilitySerializable<CompoundTag> {

    private final LazyOptional<IItemHandler> lazyInitialisionSupplier = LazyOptional.of(this::getCachedInventory);

    private ItemStackHandlerBoombox itemStackHandlerBoombox;
    private ItemStackHandlerBoombox getCachedInventory() {
        if (itemStackHandlerBoombox == null) {
            itemStackHandlerBoombox = new ItemStackHandlerBoombox(1);
        }
        return itemStackHandlerBoombox;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) return lazyInitialisionSupplier.cast();
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return getCachedInventory().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        getCachedInventory().deserializeNBT(nbt);
    }
}
