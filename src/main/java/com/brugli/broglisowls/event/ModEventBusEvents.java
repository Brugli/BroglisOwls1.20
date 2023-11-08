package com.brugli.broglisowls.event;


import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.capability.EntityOnHeadPlayerCapability;
import com.brugli.broglisowls.entity.BroglisOwlsEntityTypes;
import com.brugli.broglisowls.entity.custom.GreatOwl;
import com.brugli.broglisowls.entity.custom.Owl;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BroglisOwls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(BroglisOwlsEntityTypes.OWL_ENTITY_TYPE.get(), Owl.setAttributes());
        event.put(BroglisOwlsEntityTypes.GREAT_OWL_ENTITY_TYPE.get(), GreatOwl.setAttributes());
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(EntityOnHeadPlayerCapability.class);
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(
                BroglisOwlsEntityTypes.OWL_ENTITY_TYPE.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                Owl::checkAnimalSpawnRules,
                SpawnPlacementRegisterEvent.Operation.OR
        );

        event.register(
                BroglisOwlsEntityTypes.GREAT_OWL_ENTITY_TYPE.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                GreatOwl::checkAnimalSpawnRules,
                SpawnPlacementRegisterEvent.Operation.OR
        );
    }
}
