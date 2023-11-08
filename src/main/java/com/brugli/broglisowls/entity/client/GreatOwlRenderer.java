package com.brugli.broglisowls.entity.client;

import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.entity.client.layer.GlowingGreatOwlEyesLayer;
import com.brugli.broglisowls.entity.custom.GreatOwl;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public class GreatOwlRenderer extends MobRenderer<GreatOwl, EntityModel<GreatOwl>> {

    public static final ResourceLocation GREAT_OWL_TEX = new ResourceLocation(BroglisOwls.MODID, "textures/entity/great_owl.png");
    public static final ResourceLocation GREAT_OWL_BLINKING_TEX = new ResourceLocation(BroglisOwls.MODID, "textures/entity/great_owl_blinking.png");
    public static final ResourceLocation BABY_GREAT_OWL_TEX = new ResourceLocation(BroglisOwls.MODID, "textures/entity/baby_great_owl.png");
    public static final ResourceLocation BABY_GREAT_OWL_BLINKING_TEX = new ResourceLocation(BroglisOwls.MODID, "textures/entity/baby_great_owl_blinking.png");

    private final EntityModel<GreatOwl> baby;
    private final EntityModel<GreatOwl> big = this.getModel();

    public GreatOwlRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new GreatOwlModel<>(ctx.bakeLayer(GreatOwlModel.LAYER_LOCATION)), 0.3F);
        this.baby = new BabyGreatOwlModel<>(ctx.bakeLayer(BabyOwlModel.LAYER_LOCATION));
        this.addLayer(new GlowingGreatOwlEyesLayer(this, ctx.getModelSet()));
    }

    @Override
    public void render(GreatOwl owl, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if(owl.isBaby()) {
            this.model = this.baby;
        } else this.model = this.big;

        super.render(owl, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull GreatOwl entity) {
        if (entity.isBaby()) {
            if (entity.isShaking) {
                return BABY_GREAT_OWL_BLINKING_TEX;
            } else return BABY_GREAT_OWL_TEX;
        }
        if (entity.isShaking) {
            return GREAT_OWL_BLINKING_TEX;
        } else return GREAT_OWL_TEX;
    }
}