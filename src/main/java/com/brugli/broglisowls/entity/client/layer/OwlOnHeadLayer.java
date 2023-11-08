package com.brugli.broglisowls.entity.client.layer;

import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.capability.EntityOnHeadPlayerProvider;
import com.brugli.broglisowls.entity.BroglisOwlsEntityTypes;
import com.brugli.broglisowls.entity.client.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;



public class OwlOnHeadLayer<T extends Player> extends RenderLayer<T, PlayerModel<T>> {

    private static final ResourceLocation GLOWING_OWL_EYES = new ResourceLocation(BroglisOwls.MODID,"textures/layer/owl_eyes_layer.png");
    private static final ResourceLocation GLOWING_BABY_OWL_EYES = new ResourceLocation(BroglisOwls.MODID,"textures/layer/baby_owl_eyes_layer.png");
    private static final ResourceLocation GLOWING_GREAT_OWL_EYES = new ResourceLocation(BroglisOwls.MODID,"textures/layer/great_owl_eyes_layer.png");
    private static final ResourceLocation GLOWING_BABY_GREAT_OWL_EYES = new ResourceLocation(BroglisOwls.MODID,"textures/layer/baby_owl_eyes_layer.png");

    private final OwlModel bigModel;
    private final GreatOwlModel greatBigModel;
    private final BabyOwlModel babyModel;
    private final BabyGreatOwlModel babyGreatModel;

    public OwlOnHeadLayer(RenderLayerParent<T, PlayerModel<T>> p_174511_, EntityModelSet p_174512_) {
        super(p_174511_);
        this.bigModel = new OwlModel(p_174512_.bakeLayer(OwlModel.LAYER_LOCATION));
        this.greatBigModel = new GreatOwlModel(p_174512_.bakeLayer(GreatOwlModel.LAYER_LOCATION));
        this.babyModel = new BabyOwlModel(p_174512_.bakeLayer(BabyOwlModel.LAYER_LOCATION));
        this.babyGreatModel = new BabyGreatOwlModel(p_174512_.bakeLayer(BabyGreatOwlModel.LAYER_LOCATION));
    }

    public void render(PoseStack p_117307_, MultiBufferSource p_117308_, int p_117309_, T p_117310_, float p_117311_, float p_117312_, float p_117313_, float p_117314_, float p_117315_, float p_117316_) {
        this.render(p_117307_, p_117308_, p_117309_, p_117310_, p_117311_, p_117312_, p_117315_, p_117316_);
    }

    private void render(PoseStack p_117318_, MultiBufferSource p_117319_, int p_117320_, T p_117321_, float p_117322_, float p_117323_, float p_117324_, float p_117325_) {
        if (!p_117321_.isInvisible()) {
            p_117321_.getCapability(EntityOnHeadPlayerProvider.DATA_HEAD).ifPresent(entityOnHead -> {
                EntityType.byString(entityOnHead.getTag().getString("id")).filter((p_117294_) -> {
                    return p_117294_ == BroglisOwlsEntityTypes.OWL_ENTITY_TYPE.get();
                }).ifPresent((p_262538_) -> {
                    p_117318_.pushPose();
                    if (entityOnHead.getTag().getInt("Age") < 0) {
                        ((HeadedModel) this.getParentModel()).getHead().translateAndRotate(p_117318_);
                        p_117318_.translate(0.0F, -1.75F, 0.0F);
                        VertexConsumer owl = p_117319_.getBuffer(this.babyModel.renderType(OwlRenderer.BABY_OWL_TEX));
                        this.babyModel.renderOnShoulder(p_117318_, owl, p_117320_, OverlayTexture.NO_OVERLAY, p_117322_, p_117323_, p_117324_, p_117325_, p_117321_.tickCount);
                        VertexConsumer eyes = p_117319_.getBuffer(RenderType.entityTranslucentEmissive(GLOWING_BABY_OWL_EYES));
                        this.babyModel.renderToBuffer(p_117318_, eyes, p_117320_, OverlayTexture.pack(0, OverlayTexture.v(p_117321_.level().isNight())), 1.0F, 1.0F, 1.0F, 1.0F);
                    } else {
                        ((HeadedModel) this.getParentModel()).getHead().translateAndRotate(p_117318_);
                        p_117318_.translate(0.0F, -1.75F, 0.0F);
                        VertexConsumer vertexconsumer = p_117319_.getBuffer(this.bigModel.renderType(OwlRenderer.OWL_TEX));
                        this.bigModel.renderOnShoulder(p_117318_, vertexconsumer, p_117320_, OverlayTexture.NO_OVERLAY, p_117322_, p_117323_, p_117324_, p_117325_, p_117321_.tickCount);
                        VertexConsumer eyes = p_117319_.getBuffer(RenderType.entityTranslucentEmissive(GLOWING_OWL_EYES));
                        this.bigModel.renderToBuffer(p_117318_, eyes, p_117320_, OverlayTexture.pack(0, OverlayTexture.v(p_117321_.level().isNight())), 1.0F, 1.0F, 1.0F, 1.0F);
                    }
                    p_117318_.popPose();
                });
                EntityType.byString(entityOnHead.getTag().getString("id")).filter((p_117294_) -> {
                    return p_117294_ == BroglisOwlsEntityTypes.GREAT_OWL_ENTITY_TYPE.get();
                }).ifPresent((p_262538_) -> {
                    p_117318_.pushPose();
                    if (entityOnHead.getTag().getInt("Age") < 0) {
                        ((HeadedModel) this.getParentModel()).getHead().translateAndRotate(p_117318_);
                        p_117318_.translate(0.0F, -1.75F, 0.0F);
                        VertexConsumer vertexconsumer = p_117319_.getBuffer(this.babyModel.renderType(GreatOwlRenderer.BABY_GREAT_OWL_TEX));
                        this.babyGreatModel.renderOnShoulder(p_117318_, vertexconsumer, p_117320_, OverlayTexture.NO_OVERLAY, p_117322_, p_117323_, p_117324_, p_117325_, p_117321_.tickCount);
                        VertexConsumer eyes = p_117319_.getBuffer(RenderType.entityTranslucentEmissive(GLOWING_BABY_GREAT_OWL_EYES));
                        this.babyGreatModel.renderToBuffer(p_117318_, eyes, p_117320_, OverlayTexture.pack(0, OverlayTexture.v(p_117321_.level().isNight())), 1.0F, 1.0F, 1.0F, 1.0F);
                    } else {
                        ((HeadedModel) this.getParentModel()).getHead().translateAndRotate(p_117318_);
                        p_117318_.translate(0.0F, -1.75F, 0.0F);
                        VertexConsumer vertexconsumer = p_117319_.getBuffer(this.bigModel.renderType(GreatOwlRenderer.GREAT_OWL_TEX));
                        this.greatBigModel.renderOnShoulder(p_117318_, vertexconsumer, p_117320_, OverlayTexture.NO_OVERLAY, p_117322_, p_117323_, p_117324_, p_117325_, p_117321_.tickCount);
                        VertexConsumer eyes = p_117319_.getBuffer(RenderType.entityTranslucentEmissive(GLOWING_GREAT_OWL_EYES));
                        this.greatBigModel.renderToBuffer(p_117318_, eyes, p_117320_, OverlayTexture.pack(0, OverlayTexture.v(p_117321_.level().isNight())), 1.0F, 1.0F, 1.0F, 1.0F);
                    }
                    p_117318_.popPose();
                });
            });
        }
    }
}