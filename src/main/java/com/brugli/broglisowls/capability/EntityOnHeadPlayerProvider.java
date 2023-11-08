package com.brugli.broglisowls.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class EntityOnHeadPlayerProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<EntityOnHeadPlayerCapability> DATA_HEAD = CapabilityManager.get(new CapabilityToken<EntityOnHeadPlayerCapability>() {});

    private EntityOnHeadPlayerCapability entityOnHead = null;
    private final LazyOptional<EntityOnHeadPlayerCapability> optional = LazyOptional.of(this::createEntityOnHeadPlayerCapabiliy);

    private EntityOnHeadPlayerCapability createEntityOnHeadPlayerCapabiliy() {
        if(this.entityOnHead == null) {
            this.entityOnHead = new EntityOnHeadPlayerCapability();
        }

        return this.entityOnHead;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == DATA_HEAD) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createEntityOnHeadPlayerCapabiliy().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createEntityOnHeadPlayerCapabiliy().loadNBTData(nbt);
    }
}
