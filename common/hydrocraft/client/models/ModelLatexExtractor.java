// Date: 8-5-2013 22:46:30
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package hydrocraft.client.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLatexExtractor extends ModelHydrocraft
{
	//fields
	ModelRenderer bottom;
	ModelRenderer fence1;
	ModelRenderer fence2;
	ModelRenderer fence3;
	ModelRenderer fence4;
	ModelRenderer fence5;
	ModelRenderer fence6;
	ModelRenderer fence7;
	ModelRenderer fence8;
	ModelRenderer main1;
	ModelRenderer main2;
	ModelRenderer top1;
	ModelRenderer top2;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer bucketPlatform;

	public ModelLatexExtractor()
	{
		textureWidth = 128;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(0F, 0F, 0F, 16, 2, 16);
		bottom.setRotationPoint(-8F, 22F, -8F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		fence1 = new ModelRenderer(this, 0, 0);
		fence1.addBox(0F, 0F, 0F, 1, 6, 1);
		fence1.setRotationPoint(-8F, 16F, -8F);
		fence1.setTextureSize(128, 64);
		fence1.mirror = true;
		setRotation(fence1, 0F, 0F, 0F);
		fence2 = new ModelRenderer(this, 0, 0);
		fence2.addBox(0F, 0F, 0F, 1, 6, 1);
		fence2.setRotationPoint(-8F, 16F, 7F);
		fence2.setTextureSize(128, 64);
		fence2.mirror = true;
		setRotation(fence2, 0F, 0F, 0F);
		fence3 = new ModelRenderer(this, 0, 0);
		fence3.addBox(0F, 0F, 0F, 1, 6, 1);
		fence3.setRotationPoint(7F, 16F, 7F);
		fence3.setTextureSize(128, 64);
		fence3.mirror = true;
		setRotation(fence3, 0F, 0F, 0F);
		fence4 = new ModelRenderer(this, 0, 0);
		fence4.addBox(0F, 0F, 0F, 1, 6, 1);
		fence4.setRotationPoint(7F, 16F, -8F);
		fence4.setTextureSize(128, 64);
		fence4.mirror = true;
		setRotation(fence4, 0F, 0F, 0F);
		fence5 = new ModelRenderer(this, 0, 0);
		fence5.addBox(0F, 0F, 0F, 1, 14, 1);
		fence5.setRotationPoint(-8F, 17F, -7F);
		fence5.setTextureSize(128, 64);
		fence5.mirror = true;
		setRotation(fence5, 1.570796F, 0F, 0F);
		fence6 = new ModelRenderer(this, 0, 0);
		fence6.addBox(0F, 0F, 0F, 1, 14, 1);
		fence6.setRotationPoint(-7F, 17F, -7F);
		fence6.setTextureSize(128, 64);
		fence6.mirror = true;
		setRotation(fence6, 1.570796F, 1.570796F, 0F);
		fence7 = new ModelRenderer(this, 0, 0);
		fence7.addBox(0F, 0F, 0F, 1, 14, 1);
		fence7.setRotationPoint(7F, 17F, -7F);
		fence7.setTextureSize(128, 64);
		fence7.mirror = true;
		setRotation(fence7, 1.570796F, 0F, 0F);
		fence8 = new ModelRenderer(this, 0, 0);
		fence8.addBox(0F, 0F, 0F, 1, 14, 1);
		fence8.setRotationPoint(-7F, 17F, 8F);
		fence8.setTextureSize(128, 64);
		fence8.mirror = true;
		setRotation(fence8, 1.570796F, 1.570796F, 0F);
		main1 = new ModelRenderer(this, 0, 18);
		main1.addBox(-4F, 0F, -3F, 8, 6, 6);
		main1.setRotationPoint(0F, 12F, 0F);
		main1.setTextureSize(128, 64);
		main1.mirror = true;
		setRotation(main1, 0F, 0F, 0F);
		main2 = new ModelRenderer(this, 0, 18);
		main2.addBox(-4F, 0F, -3F, 8, 6, 6);
		main2.setRotationPoint(0F, 12F, 0F);
		main2.setTextureSize(128, 64);
		main2.mirror = true;
		setRotation(main2, 0F, 1.570796F, 0F);
		top1 = new ModelRenderer(this, 0, 34);
		top1.addBox(-2.5F, 0F, -2.5F, 5, 2, 5);
		top1.setRotationPoint(0F, 10F, 0F);
		top1.setTextureSize(128, 64);
		top1.mirror = true;
		setRotation(top1, 0F, 0F, 0F);
		top2 = new ModelRenderer(this, 0, 41);
		top2.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
		top2.setRotationPoint(0F, 8F, 0F);
		top2.setTextureSize(128, 64);
		top2.mirror = true;
		setRotation(top2, 0F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 0);
		leg1.addBox(0F, 0F, 0F, 1, 4, 1);
		leg1.setRotationPoint(-3F, 18F, -3F);
		leg1.setTextureSize(128, 64);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 0);
		leg2.addBox(0F, 0F, 0F, 1, 4, 1);
		leg2.setRotationPoint(-3F, 18F, 2F);
		leg2.setTextureSize(128, 64);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 0);
		leg3.addBox(0F, 0F, 0F, 1, 4, 1);
		leg3.setRotationPoint(2F, 18F, -3F);
		leg3.setTextureSize(128, 64);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 0);
		leg4.addBox(0F, 0F, 0F, 1, 4, 1);
		leg4.setRotationPoint(2F, 18F, 2F);
		leg4.setTextureSize(128, 64);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		bucketPlatform = new ModelRenderer(this, 16, 11);
		bucketPlatform.addBox(0F, 0F, 0F, 4, 1, 4);
		bucketPlatform.setRotationPoint(-2F, 21F, -2F);
		bucketPlatform.setTextureSize(128, 64);
		bucketPlatform.mirror = true;
		setRotation(bucketPlatform, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottom.render(f5);
		fence1.render(f5);
		fence2.render(f5);
		fence3.render(f5);
		fence4.render(f5);
		fence5.render(f5);
		fence6.render(f5);
		fence7.render(f5);
		fence8.render(f5);
		main1.render(f5);
		main2.render(f5);
		top1.render(f5);
		top2.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		bucketPlatform.render(f5);
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
		fence1.render(f5);
		fence2.render(f5);
		fence3.render(f5);
		fence4.render(f5);
		fence5.render(f5);
		fence6.render(f5);
		fence7.render(f5);
		fence8.render(f5);
		main1.render(f5);
		main2.render(f5);
		top1.render(f5);
		top2.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		bucketPlatform.render(f5);
	}

}
