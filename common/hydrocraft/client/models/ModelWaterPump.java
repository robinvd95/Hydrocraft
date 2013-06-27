package hydrocraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWaterPump extends ModelBase
{
	ModelRenderer bottom;
	ModelRenderer glass;
	ModelRenderer top;
	ModelRenderer tank;
	ModelRenderer pump;

	public ModelWaterPump()
	{
		textureWidth = 64;
		textureHeight = 32;

		
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottom.render(f5);
		glass.render(f5);
		top.render(f5);
		tank.render(f5);
		pump.render(f5);
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

	public void renderModel(float f, float f2) {
		this.pump.rotationPointY = this.pump.rotationPointY+f2;
		bottom.render(f);
		glass.render(f);
		top.render(f);
		tank.render(f);
		pump.render(f);
	}

}
