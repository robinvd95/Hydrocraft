package hydrocraft.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPulley extends ModelHydrocraft
{
	//fields
	ModelRenderer anchor;
	ModelRenderer part;
	ModelRenderer twister;
	ModelRenderer input;

	public ModelPulley()
	{
		textureWidth = 64;
		textureHeight = 32;

		anchor = new ModelRenderer(this, 0, 0);
		anchor.addBox(0F, 0F, 0F, 16, 16, 4);
		anchor.setRotationPoint(-8F, 8F, 4F);
		anchor.setTextureSize(64, 32);
		anchor.mirror = true;
		setRotation(anchor, 0F, 0F, 0F);
		part = new ModelRenderer(this, 40, 0);
		part.addBox(-3F, -3F, 0F, 6, 6, 2);
		part.setRotationPoint(0F, 16F, 2F);
		part.setTextureSize(64, 32);
		part.mirror = true;
		setRotation(part, 0F, 0F, 0F);
		twister = new ModelRenderer(this, 0, 20);
		twister.addBox(-2F, -2F, -2F, 4, 4, 6);
		twister.setRotationPoint(0F, 16F, -2F);
		twister.setTextureSize(64, 32);
		twister.mirror = true;
		setRotation(twister, 0F, 0F, 0F);
		input = new ModelRenderer(this, 40, 8);
		input.addBox(-3F, -3F, -1F, 6, 6, 4);
		input.setRotationPoint(0F, 16F, -7F);
		input.setTextureSize(64, 32);
		input.mirror = true;
		setRotation(input, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		anchor.render(f5);
		part.render(f5);
		twister.render(f5);
		input.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}

	@Override
	public void renderInventory() {
		this.renderModel(0.0625f);
	}
	
	public void renderModel(float f5){
		anchor.render(f5);
		part.render(f5);
		twister.render(f5);
		input.render(f5);
	}

}
