package com.brugli.broglisowls.event;


import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.entity.BroglisOwlsEntityTypes;
import com.brugli.broglisowls.entity.client.*;
import com.brugli.broglisowls.entity.client.layer.OwlOnHeadLayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BroglisOwls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(BroglisOwlsEntityTypes.OWL_ENTITY_TYPE.get(), OwlRenderer::new);
        EntityRenderers.register(BroglisOwlsEntityTypes.GREAT_OWL_ENTITY_TYPE.get(), GreatOwlRenderer::new);
        EntityRenderers.register(BroglisOwlsEntityTypes.THROWN_OWL_EGG.get(), ThrownOwlEggRenderer::new);
    }

    @SubscribeEvent
    public static void addEntityLayers(EntityRenderersEvent.AddLayers event) {
        for (final String skin : event.getSkins()) {
            final LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer = event
                    .getSkin(skin);
            renderer.addLayer(new OwlOnHeadLayer<>(renderer, event.getEntityModels()));
        }
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OwlModel.LAYER_LOCATION, OwlModel::createBodyLayer);
        event.registerLayerDefinition(GreatOwlModel.LAYER_LOCATION, GreatOwlModel::createBodyLayer);
        event.registerLayerDefinition(BabyOwlModel.LAYER_LOCATION, BabyOwlModel::createBodyLayer);
        event.registerLayerDefinition(BabyGreatOwlModel.LAYER_LOCATION, BabyGreatOwlModel::createBodyLayer);
    }
}
