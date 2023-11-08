package com.brugli.broglisowls.entity.client.layer;

import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.entity.client.BabyOwlModel;
import com.brugli.broglisowls.entity.client.GreatOwlModel;
import com.brugli.broglisowls.entity.custom.GreatOwl;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;


@OnlyIn(Dist.CLIENT)
public class GlowingGreatOwlEyesLayer extends RenderLayer<GreatOwl, EntityModel<GreatOwl>> {

    private static final ResourceLocation GLOWING_GREAT_OWL_EYES = new ResourceLocation(BroglisOwls.MODID,"textures/layer/great_owl_eyes_layer.png");
    private static final ResourceLocation GLOWING_BABY_GREAT_OWL_EYES = new ResourceLocation(BroglisOwls.MODID,"textures/layer/baby_owl_eyes_layer.png");

    private final EntityModel<GreatOwl> babyModel;
    private final EntityModel<GreatOwl> bigModel;

    public GlowingGreatOwlEyesLayer(RenderLayerParent<GreatOwl, EntityModel<GreatOwl>> p_174471_, EntityModelSet p_174472_) {
        super(p_174471_);
        this.babyModel = new BabyOwlModel<>(p_174472_.bakeLayer(BabyOwlModel.LAYER_LOCATION));
        this.bigModel = new GreatOwlModel<>(p_174472_.bakeLayer(GreatOwlModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull PoseStack stack, @NotNull MultiBufferSource source, int i, GreatOwl owl, float a, float b, float c, float d, float e, float f) {
        if (!owl.isShaking && !owl.isInvisible()) {
            float g = (float)owl.tickCount + c;
            EntityModel<GreatOwl> entitymodel = this.model(owl);
            entitymodel.prepareMobModel(owl, a, b, c);
            this.getParentModel().copyPropertiesTo(entitymodel);
            VertexConsumer vertexconsumer = source.getBuffer(RenderType.entityTranslucentEmissive(this.getTextureLocation(owl)));
            entitymodel.setupAnim(owl, a, b, d, e, f);
            entitymodel.renderToBuffer(stack, vertexconsumer, i, OverlayTexture.pack(0, OverlayTexture.v(owl.level().isNight())), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    protected @NotNull ResourceLocation getTextureLocation(GreatOwl owl) {
        if (owl.isBaby()) {
            return GLOWING_BABY_GREAT_OWL_EYES;
        }
        return GLOWING_GREAT_OWL_EYES;
    }

    protected @NotNull EntityModel<GreatOwl> model(GreatOwl owl) {
        if (owl.isBaby()) {
            return this.babyModel;
        }
        return this.bigModel;
    }
}