package hydrocraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPowerConverter extends ModelBase{
	ModelRenderer bottom;
	ModelRenderer top;
	ModelRenderer magnet;
	ModelRenderer topHolder;
	ModelRenderer bottomHolder;
	ModelRenderer glass;
	ModelRenderer powerOutputLeft;
	ModelRenderer powerOutputFront;
	ModelRenderer powerOutputRigth;
	ModelRenderer powerOutputBack;

	public ModelPowerConverter(){
		textureWidth = 64;
		textureHeight = 32;

		bottom = new ModelRenderer(this, 0, 14);
		bottom.addBox(0F, 0F, 0F, 16, 2, 16);
		bottom.setRotationPoint(-8F, 22F, -8F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 14);
		top.addBox(0F, 0F, 0F, 16, 2, 16);
		top.setRotationPoint(-8F, 8F, -8F);
		top.setTextureSize(64, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		magnet = new ModelRenderer(this, 0, 22);
		magnet.addBox(0F, 0F, 0F, 4, 4, 4);
		magnet.setRotationPoint(-2F, 14F, -2F);
		magnet.setTextureSize(64, 32);
		magnet.mirror = true;
		setRotation(magnet, 0F, 0F, 0F);
		topHolder = new ModelRenderer(this, 48, 14);
		topHolder.addBox(-1F, 0F, -1F, 2, 4, 2);
		topHolder.setRotationPoint(0F, 10F, 0F);
		topHolder.setTextureSize(64, 32);
		topHolder.mirror = true;
		setRotation(topHolder, 0F, 0F, 0F);
		bottomHolder = new ModelRenderer(this, 48, 14);
		bottomHolder.addBox(-1F, 0F, -1F, 2, 4, 2);
		bottomHolder.setRotationPoint(0F, 18F, 0F);
		bottomHolder.setTextureSize(64, 32);
		bottomHolder.mirror = true;
		setRotation(bottomHolder, 0F, 0F, 0F);
		glass = new ModelRenderer(this, 0, -16);
		glass.addBox(0F, 0F, 0F, 16, 12, 16);
		glass.setRotationPoint(-8F, 10F, -8F);
		glass.setTextureSize(64, 32);
		glass.mirror = true;
		setRotation(glass, 0F, 0F, 0F);
		powerOutputLeft = new ModelRenderer(this, 0, 18);
		powerOutputLeft.addBox(0F, 0F, 0F, 6, 2, 2);
		powerOutputLeft.setRotationPoint(2F, 15F, -1F);
		powerOutputLeft.setTextureSize(64, 32);
		powerOutputLeft.mirror = true;
		setRotation(powerOutputLeft, 0F, 0F, 0F);
		powerOutputFront = new ModelRenderer(this, 0, 18);
		powerOutputFront.addBox(0F, 0F, 0F, 6, 2, 2);
		powerOutputFront.setRotationPoint(-1F, 15F, -2F);
		powerOutputFront.setTextureSize(64, 32);
		powerOutputFront.mirror = true;
		setRotation(powerOutputFront, 0F, 1.570796F, 0F);
		powerOutputRigth = new ModelRenderer(this, 0, 18);
		powerOutputRigth.addBox(0F, 0F, 0F, 6, 2, 2);
		powerOutputRigth.setRotationPoint(-2F, 15F, 1F);
		powerOutputRigth.setTextureSize(64, 32);
		powerOutputRigth.mirror = true;
		setRotation(powerOutputRigth, 0F, 3.141593F, 0F);
		powerOutputBack = new ModelRenderer(this, 0, 18);
		powerOutputBack.addBox(0F, 0F, 0F, 6, 2, 2);
		powerOutputBack.setRotationPoint(1F, 15F, 2F);
		powerOutputBack.setTextureSize(64, 32);
		powerOutputBack.mirror = true;
		setRotation(powerOutputBack, 0F, -1.570796F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottom.render(f5);
		top.render(f5);
		magnet.render(f5);
		topHolder.render(f5);
		bottomHolder.render(f5);
		glass.render(f5);
		powerOutputLeft.render(f5);
		powerOutputFront.render(f5);
		powerOutputRigth.render(f5);
		powerOutputBack.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	public void renderModel(float f5) {
		bottom.render(f5);
		top.render(f5);
		magnet.render(f5);
		topHolder.render(f5);
		bottomHolder.render(f5);
		glass.render(f5);
		powerOutputLeft.render(f5);
		powerOutputFront.render(f5);
		powerOutputRigth.render(f5);
		powerOutputBack.render(f5);
	}

}
