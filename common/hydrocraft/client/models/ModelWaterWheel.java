package hydrocraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWaterWheel extends ModelBase
{
	//fields
	ModelRenderer base;
	ModelRenderer wing1;
	ModelRenderer wing2;
	ModelRenderer wing3;
	ModelRenderer wing4;
	ModelRenderer wing5;
	ModelRenderer wing6;
	ModelRenderer wing7;
	ModelRenderer wing8;
	
	private float startAngle0;
	private float startAngle1;
	private float startAngle2;
	private float startAngle3;
	private float startAngle4;
	private float startAngle5;
	private float startAngle6;
	private float startAngle7;
	private float startAngle8;


	public ModelWaterWheel()
	{
		textureWidth = 64;
		textureHeight = 32;

		base = new ModelRenderer(this, 0, 13);
		base.addBox(-1F, -1F, -7F, 2, 2, 15);
		base.setRotationPoint(0F, 0F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		wing1 = new ModelRenderer(this, 0, 0);
		wing1.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing1.setRotationPoint(0F, 0F, 0F);
		wing1.setTextureSize(64, 32);
		wing1.mirror = true;
		setRotation(wing1, 0F, 0F, 0F);
		wing2 = new ModelRenderer(this, 0, 0);
		wing2.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing2.setRotationPoint(0F, 0F, 0F);
		wing2.setTextureSize(64, 32);
		wing2.mirror = true;
		setRotation(wing2, 0F, 0F, 0.7853982F);
		wing3 = new ModelRenderer(this, 0, 0);
		wing3.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing3.setRotationPoint(0F, 0F, 0F);
		wing3.setTextureSize(64, 32);
		wing3.mirror = true;
		setRotation(wing3, 0F, 0F, 1.570796F);
		wing4 = new ModelRenderer(this, 0, 0);
		wing4.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing4.setRotationPoint(0F, 0F, 0F);
		wing4.setTextureSize(64, 32);
		wing4.mirror = true;
		setRotation(wing4, 0F, 0F, 2.356194F);
		wing5 = new ModelRenderer(this, 0, 0);
		wing5.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing5.setRotationPoint(0F, 0F, 0F);
		wing5.setTextureSize(64, 32);
		wing5.mirror = true;
		setRotation(wing5, 0F, 0F, 3.153228F);
		wing6 = new ModelRenderer(this, 0, 0);
		wing6.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing6.setRotationPoint(0F, 0F, 0F);
		wing6.setTextureSize(64, 32);
		wing6.mirror = true;
		setRotation(wing6, 0F, 0F, -0.7853982F);
		wing7 = new ModelRenderer(this, 0, 0);
		wing7.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing7.setRotationPoint(0F, 0F, 0F);
		wing7.setTextureSize(64, 32);
		wing7.mirror = true;
		setRotation(wing7, 0F, 0F, -1.570796F);
		wing8 = new ModelRenderer(this, 0, 0);
		wing8.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing8.setRotationPoint(0F, 0F, 0F);
		wing8.setTextureSize(64, 32);
		wing8.mirror = true;
		setRotation(wing8, 0F, 0F, -2.356194F);
		
		this.startAngle0 = base.rotateAngleZ;
		this.startAngle1 = wing1.rotateAngleZ;
		this.startAngle2 = wing2.rotateAngleZ;
		this.startAngle3 = wing3.rotateAngleZ;
		this.startAngle4 = wing4.rotateAngleZ;
		this.startAngle5 = wing5.rotateAngleZ;
		this.startAngle6 = wing6.rotateAngleZ;
		this.startAngle7 = wing7.rotateAngleZ;
		this.startAngle8 = wing8.rotateAngleZ;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		base.render(f5);
		wing1.render(f5);
		wing2.render(f5);
		wing3.render(f5);
		wing4.render(f5);
		wing5.render(f5);
		wing6.render(f5);
		wing7.render(f5);
		wing8.render(f5);
	}
	
	public void renderModel(float f5){
		base.render(f5);
		wing1.render(f5);
		wing2.render(f5);
		wing3.render(f5);
		wing4.render(f5);
		wing5.render(f5);
		wing6.render(f5);
		wing7.render(f5);
		wing8.render(f5);
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

	public void setRotation(float rotation) {
		this.base.rotateAngleZ += rotation;
		this.wing1.rotateAngleZ += rotation;
		this.wing2.rotateAngleZ += rotation;
		this.wing3.rotateAngleZ += rotation;
		this.wing4.rotateAngleZ += rotation;
		this.wing5.rotateAngleZ += rotation;
		this.wing6.rotateAngleZ += rotation;
		this.wing7.rotateAngleZ += rotation;
		this.wing8.rotateAngleZ += rotation;
	}

}
