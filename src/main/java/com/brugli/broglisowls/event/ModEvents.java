package com.brugli.broglisowls.event;

import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.capability.EntityOnHeadPlayerProvider;
import com.brugli.broglisowls.entity.BroglisOwlsEntityTypes;
import com.brugli.broglisowls.message.MessageOwlMountPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BroglisOwls.MODID)
public class ModEvents {



    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(EntityOnHeadPlayerProvider.DATA_HEAD).isPresent()) {
                event.addCapability(new ResourceLocation(BroglisOwls.MODID, "properties"), new EntityOnHeadPlayerProvider());
                System.out.println("Player has onHead capability");
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        event.getOriginal().reviveCaps();
        event.getOriginal().getCapability(EntityOnHeadPlayerProvider.DATA_HEAD).ifPresent(oldStore -> {
            event.getEntity().getCapability(EntityOnHeadPlayerProvider.DATA_HEAD).ifPresent(newStore -> {
                newStore.copyFrom(oldStore);
            });
            event.getOriginal().invalidateCaps();
        });

    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            event.player.getCapability(EntityOnHeadPlayerProvider.DATA_HEAD).ifPresent(entityOnHead -> {
                BroglisOwls.sendMSGToAll(new MessageOwlMountPlayer(entityOnHead.getTag()));
            });
//        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(EntityOnHeadPlayerProvider.DATA_HEAD).ifPresent(entityOnHead -> {
                    BroglisOwls.sendMSGToAll(new MessageOwlMountPlayer(entityOnHead.getTag()));
                });
            }
        }
    }
}
