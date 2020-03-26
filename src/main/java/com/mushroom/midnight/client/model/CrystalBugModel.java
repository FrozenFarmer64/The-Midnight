package com.mushroom.midnight.client.model;

import com.google.common.collect.ImmutableList;
import com.mushroom.midnight.common.entity.creature.CrystalBugEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrystalBugModel extends SegmentedModel<CrystalBugEntity> {
    private final ModelRenderer body;
    private final ModelRenderer wingL;
    private final ModelRenderer head;
    private final ModelRenderer bulb;
    private final ModelRenderer wingR;
    private final ModelRenderer wingL2;
    private final ModelRenderer antennaL;
    private final ModelRenderer antennaR;
    private final ModelRenderer wingR2;

    public CrystalBugModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.wingL = new ModelRenderer(this, 0, 11);
        this.wingL.setRotationPoint(2.0F, 1.1F, 3.1F);
        this.wingL.addBox(0.0F, 0.0F, 0.0F, 6, 7, 1, 0.0F);
        this.setRotateAngle(wingL, 0.0F, 0.0F, -0.08726646259971647F);
        this.antennaR = new ModelRenderer(this, 0, -1);
        this.antennaR.setRotationPoint(0.5F, -4.0F, 3.0F);
        this.antennaR.addBox(0.0F, 0.0F, 0.0F, 0, 4, 1, 0.0F);
        this.antennaL = new ModelRenderer(this, 0, -1);
        this.antennaL.setRotationPoint(4.5F, -4.0F, 3.0F);
        this.antennaL.addBox(0.0F, 0.0F, 0.0F, 0, 4, 1, 0.0F);
        this.head = new ModelRenderer(this, 17, 0);
        this.head.setRotationPoint(-0.5F, -5.0F, -0.5F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 5, 5, 5, 0.0F);
        this.wingR = new ModelRenderer(this, 15, 11);
        this.wingR.setRotationPoint(2.0F, 1.1F, 3.1F);
        this.wingR.addBox(-6.0F, 0.0F, 0.0F, 6, 7, 1, 0.0F);
        this.setRotateAngle(wingR, 0.0F, 0.0F, 0.08726646259971647F);
        this.wingL2 = new ModelRenderer(this, 0, 20);
        this.wingL2.setRotationPoint(0.0F, 7.0F, 0.1F);
        this.wingL2.addBox(0.0F, 0.0F, 0.0F, 7, 5, 1, 0.0F);
        this.setRotateAngle(wingL2, 0.0F, 0.0F, -0.08726646259971647F);
        this.bulb = new ModelRenderer(this, 38, 0);
        this.bulb.setRotationPoint(0.5F, 6.0F, 0.5F);
        this.bulb.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
        this.wingR2 = new ModelRenderer(this, 0, 26);
        this.wingR2.setRotationPoint(-1.0F, 7.0F, 0.1F);
        this.wingR2.addBox(-6.0F, 0.0F, 0.0F, 7, 5, 1, 0.0F);
        this.setRotateAngle(wingR2, 0.0F, 0.0F, 0.08726646259971647F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(-2.0F, -3.0F, -2.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 4, 6, 4, 0.0F);

        this.head.addChild(this.antennaR);
        this.head.addChild(this.antennaL);
        this.body.addChild(this.head);
        this.wingL.addChild(this.wingL2);
        this.body.addChild(this.wingL);
        this.wingR.addChild(this.wingR2);
        this.body.addChild(this.wingR);
        this.body.addChild(this.bulb);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.body);
    }

    // TODO: NEEDS ATTENTION!
    @Override
    public void render(CrystalBugEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitchr) {
        if (entity.isStanding()) {
            body.rotateAngleX = 0f;
            wingR.rotateAngleY = wingL.rotateAngleY = 0f;
        } else {
            body.rotateAngleX = (float) Math.PI / 4f + MathHelper.cos(ageInTicks * 0.1f) * 0.15f;
            wingR.rotateAngleY = Math.abs(MathHelper.cos(ageInTicks)) * (float) Math.PI * 0.35f;
            wingL.rotateAngleY = -wingR.rotateAngleY;
        }
    }

    private void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }
}
