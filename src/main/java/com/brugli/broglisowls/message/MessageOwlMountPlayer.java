//package com.brugli.broglisowls.message;
//
//import com.brugli.broglisowls.BroglisOwls;
//import com.brugli.broglisowls.entity.custom.Owl;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraftforge.fml.LogicalSide;
//import net.minecraftforge.network.NetworkEvent;
//
//import java.util.function.Supplier;
//
//public class MessageOwlMountPlayer {
//
//    public int rider;
//    public int mount;
//
//    public MessageOwlMountPlayer(int rider, int mount) {
//        this.rider = rider;
//        this.mount = mount;
//    }
//
//    public MessageOwlMountPlayer() {
//    }
//
//    public static MessageOwlMountPlayer read(FriendlyByteBuf buf) {
//        return new MessageOwlMountPlayer(buf.readInt(), buf.readInt());
//    }
//
//    public static void write(MessageOwlMountPlayer message, FriendlyByteBuf buf) {
//        buf.writeInt(message.rider);
//        buf.writeInt(message.mount);
//    }
//
//    public static class Handler {
//        public Handler() {
//        }
//
//        public static void handle(MessageOwlMountPlayer message, Supplier<NetworkEvent.Context> context) {
//            context.get().setPacketHandled(true);
//            Player player = context.get().getSender();
//            if(context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT){
//                player = BroglisOwls.PROXY.getClientSidePlayer();
//            }
//
//            if (player != null) {
//                if (player.level() != null) {
//                    Entity entity = player.level().getEntity(message.rider);
//                    Entity mountEntity = player.level().getEntity(message.mount);
//                    if (entity instanceof Owl && mountEntity instanceof Player && entity.distanceTo(mountEntity) < 16D) {
//                        entity.startRiding(mountEntity, true);
//                    }
//                }
//            }
//        }
//    }
//}

package com.brugli.broglisowls.message;

import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.capability.EntityOnHeadPlayerProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageOwlMountPlayer {

    public CompoundTag entityOnHead;

    public MessageOwlMountPlayer(CompoundTag compoundTag) {
        this.entityOnHead = compoundTag;
    }

    public MessageOwlMountPlayer() {
    }

    public static MessageOwlMountPlayer read(FriendlyByteBuf buf) {
        return new MessageOwlMountPlayer(buf.readNbt());
    }

    public static void write(MessageOwlMountPlayer message, FriendlyByteBuf buf) {
        buf.writeNbt(message.entityOnHead);
    }

    public static class Handler {
        public Handler() {
        }

        public static void handle(MessageOwlMountPlayer message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            Player player = context.get().getSender();
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                player = BroglisOwls.PROXY.getClientSidePlayer();
            }

            if (player != null) {
                if (player.level() != null) {
                    player.getCapability(EntityOnHeadPlayerProvider.DATA_HEAD).ifPresent(capability -> {
                        capability.setTag(message.entityOnHead);
                    });
                }
            }
        }
    }
}