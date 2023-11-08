package com.brugli.broglisowls.entity.client;

import com.brugli.broglisowls.BroglisOwls;
import com.brugli.broglisowls.capability.EntityOnHeadPlayerProvider;
import com.brugli.broglisowls.entity.client.animation.OwlAnimation;
import com.brugli.broglisowls.entity.custom.Owl;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class OwlModel<T extends Owl> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BroglisOwls.MODID, "entity_owl"), "main");

    private final ModelPart Root;
    private final ModelPart Head;
    private final ModelPart LeftLeg;
    private final ModelPart RightLeg;

    public OwlModel(ModelPart root) {
        this.Root = root.getChild("Root");
        this.Head = Root.getChild("Body").getChild("Head");
        this.LeftLeg = Root.getChild("LeftFoot");
        this.RightLeg = Root.getChild("RightFoot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Root = partdefinition.addOrReplaceChild("Root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Body = Root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -6.0F, -6.0F, 10.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 20).addBox(-6.0F, -6.0F, -5.0F, 12.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -4.0F, -6.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create().texOffs(5, 5).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -6.0F, -4.0F));

        PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create().texOffs(0, 5).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -6.0F, -4.0F));

        PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(33, 7).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 6.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition LeftWing = Body.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(0, 37).addBox(0.0F, 0.0F, -4.91F, 2.0F, 7.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -6.0F, -0.1F));

        PartDefinition RightWing = Body.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(34, 26).addBox(-2.0F, 0.0F, -5.01F, 2.0F, 7.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -6.0F, 0.0F));

        PartDefinition RightFoot = Root.addOrReplaceChild("RightFoot", CubeListBuilder.create().texOffs(45, 7).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(40, 15).addBox(-2.0F, 1.0F, -2.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -2.0F, 0.0F));

        PartDefinition LeftFoot = Root.addOrReplaceChild("LeftFoot", CubeListBuilder.create().texOffs(45, 7).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(33, 0).addBox(-2.0F, 1.0F, -2.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -2.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public ModelPart root() {
        return Root;
    }

    @Override
    public void setupAnim(@NotNull Owl entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);
        animate(entity.idleAnimationState, OwlAnimation.IDLE, ageInTicks);
        if (!entity.isInWaterOrBubble() && !entity.isFlying()) {
            animateWalk(OwlAnimation.WALK, limbSwing, limbSwingAmount, 1.5F, 1.5F);
        }
        if (entity.isFlying()) {
            animate(entity.flyingAnimationState, OwlAnimation.FLIGHT, ageInTicks);
        }
        if (entity.isShaking) {
            animate(entity.shakingAnimationState, OwlAnimation.SHAKE, ageInTicks);
        } else if (entity.isInSittingPose()) {
            animate(entity.sittingAnimationState, OwlAnimation.SIT, ageInTicks);
        }
    }

    public void setupShoulderAnim(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        root().getAllParts().forEach(ModelPart::resetPose);
        Player player = Minecraft.getInstance().player;
        player.getCapability(EntityOnHeadPlayerProvider.DATA_HEAD).ifPresent(entityOnHead -> {
            if (!entityOnHead.getTag().isEmpty() && !player.onGround()) {
                animateWalk(OwlAnimation.FLIGHT, limbSwing, limbSwingAmount, 1.25F, 1.25F);
            }
        });
    }

    private void applyHeadRotation(Owl entity, float netHeadYaw, float netHeadPitch, float ageInTicks) {
        netHeadYaw = Mth.clamp(netHeadYaw, -170.0F, 170.0F);
        netHeadPitch = Mth.clamp(netHeadPitch, -30.0F, 30.0F);

        this.Head.xRot = netHeadPitch * ((float) Math.PI / 270F);
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 270F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void renderOnShoulder(PoseStack p_103224_, VertexConsumer p_103225_, int p_103226_, int p_103227_, float p_103228_, float p_103229_, float p_103230_, float p_103231_, float p_103232_) {
        this.setupShoulderAnim(p_103228_, p_103229_, p_103232_, p_103230_, p_103231_);
        this.LeftLeg.visible = false;
        this.RightLeg.visible = false;
        this.Root.render(p_103224_, p_103225_, p_103226_, p_103227_);
    }
}