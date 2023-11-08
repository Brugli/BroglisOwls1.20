package com.brugli.broglisowls.event;

import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.capability.EntityOnHeadPlayerProvider;
import com.brugli.broglisowls.entity.BroglisOwlsEntityTypes;
import com.brugli.broglisowls.message.MessageOwlMountPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BroglisOwls.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeModEvents {

    @SubscribeEvent
    public static void effectEvent(final TickEvent.PlayerTickEvent event) {



        Player player = event.player;

        player.getCapability(EntityOnHeadPlayerProvider.DATA_HEAD).ifPresent(entityOnHead -> {

            CompoundTag compoundTag = entityOnHead.getTag();
//            System.out.println(compoundTag);

            EntityType.byString(compoundTag.getString("id")).filter((p_117294_) -> {
                return p_117294_ == BroglisOwlsEntityTypes.OWL_ENTITY_TYPE.get();
            }).ifPresent((p_262538_) -> {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 3));
                if (player.onGround() && player.isSteppingCarefully() && !player.level().isClientSide() && !compoundTag.isEmpty()) {
                    EntityType.create(compoundTag, player.level()).ifPresent((p_289491_) -> {
                        if (p_289491_ instanceof TamableAnimal) {
                            ((TamableAnimal)p_289491_).setOwnerUUID(compoundTag.getUUID("Owner"));
                        }
                        p_289491_.setPos(player.getX(), player.getY() + (double)0.7F, player.getZ());
                        ((ServerLevel)player.level()).addWithUUID(p_289491_);
                        entityOnHead.setTag(new CompoundTag());
                        player.sendSystemMessage(Component.literal("EntityTookOff"));
                        BroglisOwls.sendMSGToAll(new MessageOwlMountPlayer(new CompoundTag()));
                    });
                }
            });
            EntityType.byString(compoundTag.getString("id")).filter((p_117294_) -> {
                return p_117294_ == BroglisOwlsEntityTypes.GREAT_OWL_ENTITY_TYPE.get();
            }).ifPresent((p_262538_) -> {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 3));
                if (player.onGround() && player.isSteppingCarefully() && !player.level().isClientSide() && !compoundTag.isEmpty()) {
                    EntityType.create(compoundTag, player.level()).ifPresent((p_289491_) -> {
                        if (p_289491_ instanceof TamableAnimal) {
                            ((TamableAnimal)p_289491_).setOwnerUUID(compoundTag.getUUID("Owner"));
                        }
                        p_289491_.setPos(player.getX(), player.getY() + (double)0.7F, player.getZ());
                        ((ServerLevel)player.level()).addWithUUID(p_289491_);
                        entityOnHead.setTag(new CompoundTag());
                        player.sendSystemMessage(Component.literal("EntityTookOff"));
                        BroglisOwls.sendMSGToAll(new MessageOwlMountPlayer(new CompoundTag()));
                    });
                }
            });
        });
    }
}
