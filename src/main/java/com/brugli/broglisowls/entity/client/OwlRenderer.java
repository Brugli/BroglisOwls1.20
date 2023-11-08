package com.brugli.broglisowls.entity.client;

import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.entity.client.layer.GlowingOwlEyesLayer;
import com.brugli.broglisowls.entity.custom.Owl;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public class OwlRenderer extends MobRenderer<Owl, EntityModel<Owl>> {

    public static final ResourceLocation OWL_TEX = new ResourceLocation(BroglisOwls.MODID, "textures/entity/brown_owl.png");
    public static final ResourceLocation OWL_BLINKING_TEX = new ResourceLocation(BroglisOwls.MODID, "textures/entity/brown_owl_blinking.png");
    public static final ResourceLocation BABY_OWL_TEX = new ResourceLocation(BroglisOwls.MODID, "textures/entity/baby_brown_owl.png");
    public static final ResourceLocation BABY_OWL_BLINKING_TEX = new ResourceLocation(BroglisOwls.MODID, "textures/entity/baby_brown_owl_blinking.png");

    public static final ResourceLocation GLOWING_OWL_EYES = new ResourceLocation(BroglisOwls.MODID,"textures/layer/owl_eyes_layer.png");

    private final EntityModel<Owl> baby;
    private final EntityModel<Owl> big = this.getModel();

    public OwlRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new OwlModel<>(ctx.bakeLayer(OwlModel.LAYER_LOCATION)), 0.3F);
        this.baby = new BabyOwlModel<>(ctx.bakeLayer(BabyOwlModel.LAYER_LOCATION));
        this.addLayer(new GlowingOwlEyesLayer(this, ctx.getModelSet()));
    }

    @Override
    public void render(Owl owl, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if(owl.isBaby()) {
            this.model = this.baby;
        } else this.model = this.big;

        super.render(owl, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull Owl entity) {
        if (entity.isBaby()) {
            if (entity.isShaking) {
                return BABY_OWL_BLINKING_TEX;
            } else return BABY_OWL_TEX;
        }
        if (entity.isShaking) {
            return OWL_BLINKING_TEX;
        } else return OWL_TEX;
    }
}