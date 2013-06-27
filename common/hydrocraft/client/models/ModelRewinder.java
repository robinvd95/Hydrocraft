package hydrocraft.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRewinder extends ModelHydrocraft
{
	//fields
	ModelRenderer stock;
	ModelRenderer motor;
	ModelRenderer top;
	ModelRenderer bottom;
	ModelRenderer glass;

	public ModelRewinder()
	{
		textureWidth = 64;
		textureHeight = 32;

		stock = new ModelRenderer(this, 0, 15);
		stock.addBox(-1F, 0F, -1F, 2, 14, 2);
		stock.setRotationPoint(0F, 9F, 0F);
		stock.setTextureSize(64, 32);
		stock.mirror = true;
		setRotation(stock, 0F, 0F, 0F);
		motor = new ModelRenderer(this, 48, 17);
		motor.addBox(-2F, 0F, -2F, 4, 10, 4);
		motor.setRotationPoint(0F, 11F, 0F);
		motor.setTextureSize(64, 32);
		motor.mirror = true;
		setRotation(motor, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 15);
		top.addBox(0F, 0F, 0F, 16, 1, 16);
		top.setRotationPoint(-8F, 8F, -8F);
		top.setTextureSize(64, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		bottom = new ModelRenderer(this, 0, 15);
		bottom.addBox(0F, 0F, 0F, 16, 1, 16);
		bottom.setRotationPoint(-8F, 23F, -8F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		glass = new ModelRenderer(this, 0, -16);
		glass.addBox(-8F, 0F, -8F, 16, 14, 16);
		glass.setRotationPoint(0F, 9F, 0F);
		glass.setTextureSize(64, 32);
		glass.mirror = true;
		setRotation(glass, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		stock.render(f5);
		motor.render(f5);
		top.render(f5);
		bottom.render(f5);
		glass.render(f5);
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

	public void renderModel(float f5, boolean par2){
		this.bottom.render(f5);
		this.glass.render(f5);
		this.top.render(f5);
		if(par2){
			this.motor.render(f5);
			this.stock.render(f5);
		}
	}
	
	@Override
	public void renderInventory() {
		this.renderModel(0.0625f, true);
	}

}
