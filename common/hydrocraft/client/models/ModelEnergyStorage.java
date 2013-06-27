package hydrocraft.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnergyStorage extends ModelHydrocraft
{
	//fields
	ModelRenderer bottom;
	ModelRenderer connectorFront;
	ModelRenderer connectorRigth;
	ModelRenderer connectorBack;
	ModelRenderer connectorLeft;
	ModelRenderer storage;
	ModelRenderer inputPillar1;
	ModelRenderer inputPillar2;
	ModelRenderer inputPillar3;
	ModelRenderer inputPillar4;
	ModelRenderer inputPlatform;
	ModelRenderer glass;

	public ModelEnergyStorage()
	{
		textureWidth = 128;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(0F, 0F, 0F, 16, 2, 16);
		bottom.setRotationPoint(-8F, 22F, -8F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		connectorFront = new ModelRenderer(this, 0, 18);
		connectorFront.addBox(0F, 0F, 0F, 4, 8, 1);
		connectorFront.setRotationPoint(2F, 14F, -7F);
		connectorFront.setTextureSize(128, 64);
		connectorFront.mirror = true;
		setRotation(connectorFront, 0F, -3.141593F, 0F);
		connectorRigth = new ModelRenderer(this, 0, 18);
		connectorRigth.addBox(0F, 0F, 0F, 4, 8, 1);
		connectorRigth.setRotationPoint(-7F, 14F, -2F);
		connectorRigth.setTextureSize(128, 64);
		connectorRigth.mirror = true;
		setRotation(connectorRigth, 0F, -1.570796F, 0F);
		connectorBack = new ModelRenderer(this, 0, 18);
		connectorBack.addBox(0F, 0F, 0F, 4, 8, 1);
		connectorBack.setRotationPoint(-2F, 14F, 7F);
		connectorBack.setTextureSize(128, 64);
		connectorBack.mirror = true;
		setRotation(connectorBack, 0F, 0F, 0F);
		connectorLeft = new ModelRenderer(this, 0, 18);
		connectorLeft.addBox(0F, 0F, 0F, 4, 8, 1);
		connectorLeft.setRotationPoint(7F, 14F, 2F);
		connectorLeft.setTextureSize(128, 64);
		connectorLeft.mirror = true;
		setRotation(connectorLeft, 0F, 1.570796F, 0F);
		storage = new ModelRenderer(this, 0, 0);
		storage.addBox(0F, 0F, 0F, 4, 11, 4);
		storage.setRotationPoint(-2F, 11F, -2F);
		storage.setTextureSize(128, 64);
		storage.mirror = true;
		setRotation(storage, 0F, 0F, 0F);
		inputPillar1 = new ModelRenderer(this, 10, 18);
		inputPillar1.addBox(0F, 0F, 0F, 1, 2, 1);
		inputPillar1.setRotationPoint(-2F, 9F, -2F);
		inputPillar1.setTextureSize(128, 64);
		inputPillar1.mirror = true;
		setRotation(inputPillar1, 0F, 0F, 0F);
		inputPillar2 = new ModelRenderer(this, 10, 18);
		inputPillar2.addBox(0F, 0F, 0F, 1, 2, 1);
		inputPillar2.setRotationPoint(1F, 9F, -2F);
		inputPillar2.setTextureSize(128, 64);
		inputPillar2.mirror = true;
		setRotation(inputPillar2, 0F, 0F, 0F);
		inputPillar3 = new ModelRenderer(this, 10, 18);
		inputPillar3.addBox(0F, 0F, 0F, 1, 2, 1);
		inputPillar3.setRotationPoint(-2F, 9F, 1F);
		inputPillar3.setTextureSize(128, 64);
		inputPillar3.mirror = true;
		setRotation(inputPillar3, 0F, 0F, 0F);
		inputPillar4 = new ModelRenderer(this, 10, 18);
		inputPillar4.addBox(0F, 0F, 0F, 1, 2, 1);
		inputPillar4.setRotationPoint(1F, 9F, 1F);
		inputPillar4.setTextureSize(128, 64);
		inputPillar4.mirror = true;
		setRotation(inputPillar4, 0F, 0F, 0F);
		inputPlatform = new ModelRenderer(this, 10, 18);
		inputPlatform.addBox(0F, 0F, 0F, 4, 1, 4);
		inputPlatform.setRotationPoint(-2F, 8F, -2F);
		inputPlatform.setTextureSize(128, 64);
		inputPlatform.mirror = true;
		setRotation(inputPlatform, 0F, 0F, 0F);
		glass = new ModelRenderer(this, 0, 29);
		glass.addBox(0F, 0F, 0F, 16, 14, 16);
		glass.setRotationPoint(-8F, 8F, -8F);
		glass.setTextureSize(128, 64);
		glass.mirror = true;
		setRotation(glass, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottom.render(f5);
		connectorFront.render(f5);
		connectorRigth.render(f5);
		connectorBack.render(f5);
		connectorLeft.render(f5);
		storage.render(f5);
		inputPillar1.render(f5);
		inputPillar2.render(f5);
		inputPillar3.render(f5);
		inputPillar4.render(f5);
		inputPlatform.render(f5);
		glass.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	@Override
	public void renderInventory() {
		this.renderModel(0.0625f);
	}
	
	public void renderModel(float f5){
		bottom.render(f5);
		connectorFront.render(f5);
		connectorRigth.render(f5);
		connectorBack.render(f5);
		connectorLeft.render(f5);
		storage.render(f5);
		inputPillar1.render(f5);
		inputPillar2.render(f5);
		inputPillar3.render(f5);
		inputPillar4.render(f5);
		inputPlatform.render(f5);
		glass.render(f5);
	}

}
