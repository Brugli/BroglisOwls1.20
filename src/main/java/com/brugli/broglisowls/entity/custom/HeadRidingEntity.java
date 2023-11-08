package com.brugli.broglisowls.entity.custom;

import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.capability.EntityOnHeadPlayerProvider;
import com.brugli.broglisowls.message.MessageOwlMountPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public abstract class HeadRidingEntity extends TamableAnimal {
    private int rideCooldownCounter;

    protected HeadRidingEntity(EntityType<? extends HeadRidingEntity> p_29893_, Level p_29894_) {
        super(p_29893_, p_29894_);
    }

    public boolean setEntityOnHead(ServerPlayer p_29896_) {

        p_29896_.getCapability(EntityOnHeadPlayerProvider.DATA_HEAD).ifPresent(entityOnHead -> {
            if (entityOnHead.getTag().isEmpty()) {
                CompoundTag compoundtag = new CompoundTag();
                compoundtag.putString("id", this.getEncodeId());
                this.saveWithoutId(compoundtag);
                entityOnHead.setTag(compoundtag);
                p_29896_.sendSystemMessage(Component.literal("EntityLandedOnHead"));
                this.discard();
                BroglisOwls.sendMSGToAll(new MessageOwlMountPlayer(compoundtag));
            }
    });
        return true;
    }

    public void tick() {
        ++this.rideCooldownCounter;
        super.tick();
    }

    public boolean canSitOnHead() {
        return this.rideCooldownCounter > 100 ;
    }
}