package com.brugli.broglisowls;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = BroglisOwls.MODID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    public Player getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }
}
