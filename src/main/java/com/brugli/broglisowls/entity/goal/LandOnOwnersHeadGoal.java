package com.brugli.broglisowls.entity.goal;

import com.brugli.broglisowls.entity.custom.HeadRidingEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.goal.Goal;

public class LandOnOwnersHeadGoal extends Goal {
    private final HeadRidingEntity entity;
    private ServerPlayer owner;
    private boolean isSittingOnHead;

    public LandOnOwnersHeadGoal(HeadRidingEntity p_25483_) {
        this.entity = p_25483_;
    }

    public boolean canUse() {
        ServerPlayer serverplayer = (ServerPlayer)this.entity.getOwner();
        boolean flag = serverplayer != null && !serverplayer.isSpectator() && !serverplayer.getAbilities().flying && !serverplayer.isInWater() && !serverplayer.isInPowderSnow;
        return !this.entity.isOrderedToSit() && flag && this.entity.canSitOnHead();
    }

    public boolean isInterruptable() {
        return !this.isSittingOnHead;
    }

    public void start() {
        this.owner = (ServerPlayer)this.entity.getOwner();
        this.isSittingOnHead = false;
    }

    public void tick() {
        if (!this.isSittingOnHead && !this.entity.isInSittingPose() && !this.entity.isLeashed()) {
            if (this.entity.getBoundingBox().intersects(this.owner.getBoundingBox())) {
                this.isSittingOnHead = this.entity.setEntityOnHead(this.owner);
            }

        }
    }
}