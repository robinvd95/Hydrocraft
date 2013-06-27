package hydrocraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelStock extends ModelBase
{
	
	ModelRenderer y;
	ModelRenderer z;
	ModelRenderer x;

	public ModelStock()
	{
		textureWidth = 64;
		textureHeight = 32;

		y = new ModelRenderer(this, 0, 0);
		y.addBox(-1F, 0F, -1F, 2, 16, 2);
		y.setRotationPoint(0F, 8F, 0F);
		y.setTextureSize(64, 32);
		y.mirror = true;
		setRotation(y, 0F, 0F, 0F);
		z = new ModelRenderer(this, 0, 0);
		z.addBox(-1F, 0F, -1F, 2, 16, 2);
		z.setRotationPoint(0F, 16F, -8F);
		z.setTextureSize(64, 32);
		z.mirror = true;
		setRotation(z, 1.570796F, 0F, 0F);
		x = new ModelRenderer(this, 0, 0);
		x.addBox(-1F, 0F, -1F, 2, 16, 2);
		x.setRotationPoint(-8F, 16F, 0F);
		x.setTextureSize(64, 32);
		x.mirror = true;
		setRotation(x, 0F, 0F, -1.570796F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		y.render(f5);
		z.render(f5);
		x.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	public void renderModel(float f, int par2, float par3){
		if(par2 == 0 || par2 == 1){
			y.rotateAngleY += par3;
			y.render(f);
		}else if(par2 == 2 || par2 == 3){
			z.rotateAngleZ += par3;
			z.render(f);
		}else{
			x.rotateAngleY += par3;
			x.render(f);
		}
		
		
	}

}
