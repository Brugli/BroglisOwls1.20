package com.brugli.broglisowls.capability;

import net.minecraft.nbt.CompoundTag;

//@AutoRegisterCapability
public class EntityOnHeadPlayerCapability {

    private CompoundTag entityOnHead = new CompoundTag();

    public CompoundTag getTag() {
        return entityOnHead;
    }

    public void setTag(CompoundTag compoundTag) {
        this.entityOnHead = compoundTag;
    }

    public void saveNBTData(CompoundTag compoundTag) {
        compoundTag.put("id", entityOnHead);
    }

    public void loadNBTData(CompoundTag compoundTag) {
        entityOnHead = (CompoundTag) compoundTag.get("id");
    }

    public void copyFrom(EntityOnHeadPlayerCapability source) {
        this.entityOnHead = source.entityOnHead;
    }
}
