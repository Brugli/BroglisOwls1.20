package com.brugli.broglisowls;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;

import static com.brugli.broglisowls.BroglisOwls.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {


    public void init() {
    }

    public void clientInit() {
    }
    public Player getClientSidePlayer() {
        return null;
    }
}
