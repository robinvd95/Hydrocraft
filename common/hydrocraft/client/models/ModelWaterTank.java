package hydrocraft.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWaterTank extends ModelHydrocraft
{
	//fields
	ModelRenderer bottom;
	ModelRenderer tank;
	ModelRenderer outputFront;
	ModelRenderer outputRigth;
	ModelRenderer outputBack;
	ModelRenderer outputLeft;
	ModelRenderer[] water = new ModelRenderer[4];

	public ModelWaterTank()
	{
		textureWidth = 128;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(0F, 0F, 0F, 16, 2, 16);
		bottom.setRotationPoint(-8F, 22F, -8F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		tank = new ModelRenderer(this, 0, 18);
		tank.addBox(0F, 0F, 0F, 14, 16, 14);
		tank.setRotationPoint(-7F, 8F, -7F);
		tank.setTextureSize(128, 64);
		tank.mirror = true;
		setRotation(tank, 0F, 0F, 0F);
		outputFront = new ModelRenderer(this, 0, 0);
		outputFront.addBox(0F, 0F, 0F, 6, 6, 2);
		outputFront.setRotationPoint(-3F, 13F, -8F);
		outputFront.setTextureSize(128, 64);
		outputFront.mirror = true;
		setRotation(outputFront, 0F, 0F, 0F);
		outputRigth = new ModelRenderer(this, 0, 0);
		outputRigth.addBox(0F, 0F, 0F, 6, 6, 2);
		outputRigth.setRotationPoint(-8F, 13F, 3F);
		outputRigth.setTextureSize(128, 64);
		outputRigth.mirror = true;
		setRotation(outputRigth, 0F, 1.570796F, 0F);
		outputBack = new ModelRenderer(this, 0, 0);
		outputBack.addBox(0F, 0F, 0F, 6, 6, 2);
		outputBack.setRotationPoint(3F, 13F, 8F);
		outputBack.setTextureSize(128, 64);
		outputBack.mirror = true;
		setRotation(outputBack, 0F, 3.141593F, 0F);
		outputLeft = new ModelRenderer(this, 0, 0);
		outputLeft.addBox(0F, 0F, 0F, 6, 6, 2);
		outputLeft.setRotationPoint(8F, 13F, -3F);
		outputLeft.setTextureSize(128, 64);
		outputLeft.mirror = true;
		setRotation(outputLeft, 0F, -1.570796F, 0F);
		water[0] = new ModelRenderer(this, 0, 48);
		water[0].addBox(0F, 0F, 0F, 12, 4, 12);
		water[0].setRotationPoint(-6F, 20F, -6F);
		water[0].setTextureSize(128, 64);
		water[0].mirror = true;
		setRotation(water[0], 0F, 0F, 0F);
		water[1] = new ModelRenderer(this, 0, 48);
		water[1].addBox(0F, 0F, 0F, 12, 4, 12);
		water[1].setRotationPoint(-6F, 16F, -6F);
		water[1].setTextureSize(128, 64);
		water[1].mirror = true;
		setRotation(water[1], 0F, 0F, 0F);
		water[2] = new ModelRenderer(this, 0, 48);
		water[2].addBox(0F, 0F, 0F, 12, 4, 12);
		water[2].setRotationPoint(-6F, 12F, -6F);
		water[2].setTextureSize(128, 64);
		water[2].mirror = true;
		setRotation(water[2], 0F, 0F, 0F);
		water[3] = new ModelRenderer(this, 0, 48);
		water[3].addBox(0F, 0F, 0F, 12, 4, 12);
		water[3].setRotationPoint(-6F, 8F, -6F);
		water[3].setTextureSize(128, 64);
		water[3].mirror = true;
		setRotation(water[3], 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottom.render(f5);
		tank.render(f5);
		outputFront.render(f5);
		outputRigth.render(f5);
		outputBack.render(f5);
		outputLeft.render(f5);
		water[0].render(f5);
		water[1].render(f5);
		water[2].render(f5);
		water[3].render(f5);
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
		bottom.render(0.0625f);
		tank.render(0.0625f);

	}

	public void renderModel(float f, boolean isBase, Object object, int par4) {
		this.tank.render(f);
		if(isBase){
			this.bottom.render(f);
		}
		for(int i = 0; i < par4; i++){
			water[i].render(f);
		}
		
	}

}
