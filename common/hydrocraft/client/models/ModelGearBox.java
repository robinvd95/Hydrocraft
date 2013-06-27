package hydrocraft.client.models;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeDirection;

public class ModelGearBox extends ModelHydrocraft
{
	//fields
	ModelRenderer mainBox;
	ModelRenderer top;
	ModelRenderer bottom;
	ModelRenderer front;
	ModelRenderer back;
	ModelRenderer left;
	ModelRenderer rigth;
	ModelRenderer gearTop;
	ModelRenderer gearBottom;
	ModelRenderer gearFront;
	ModelRenderer gearBack;
	ModelRenderer gearLeft;
	ModelRenderer gearRigth;

	public ModelGearBox()
	{
		textureWidth = 64;
		textureHeight = 32;

		mainBox = new ModelRenderer(this, 0, 0);
		mainBox.addBox(0F, 0F, 0F, 8, 8, 8);
		mainBox.setRotationPoint(-4F, 12F, -4F);
		mainBox.setTextureSize(64, 32);
		mainBox.mirror = true;
		setRotation(mainBox, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-1F, 0F, -1F, 2, 5, 2);
		top.setRotationPoint(0F, 8F, 0F);
		top.setTextureSize(64, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-1F, 0F, -1F, 2, 5, 2);
		bottom.setRotationPoint(0F, 19F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		front = new ModelRenderer(this, 0, 0);
		front.addBox(-1F, 0F, -1F, 2, 5, 2);
		front.setRotationPoint(0F, 16F, -8F);
		front.setTextureSize(64, 32);
		front.mirror = true;
		setRotation(front, 1.570796F, 0F, 0F);
		back = new ModelRenderer(this, 0, 0);
		back.addBox(-1F, 0F, -1F, 2, 5, 2);
		back.setRotationPoint(0F, 16F, 3F);
		back.setTextureSize(64, 32);
		back.mirror = true;
		setRotation(back, 1.570796F, 0F, 0F);
		left = new ModelRenderer(this, 0, 0);
		left.addBox(-1F, 0F, -1F, 2, 5, 2);
		left.setRotationPoint(3F, 16F, 0F);
		left.setTextureSize(64, 32);
		left.mirror = true;
		setRotation(left, 1.570796F, 1.570796F, 0F);
		rigth = new ModelRenderer(this, 0, 0);
		rigth.addBox(-1F, 0F, -1F, 2, 5, 2);
		rigth.setRotationPoint(-8F, 16F, 0F);
		rigth.setTextureSize(64, 32);
		rigth.mirror = true;
		setRotation(rigth, 1.570796F, 1.570796F, 0F);
		gearTop = new ModelRenderer(this, 0, 16);
		gearTop.addBox(-2F, 0F, -2F, 4, 1, 4);
		gearTop.setRotationPoint(0F, 13F, 0F);
		gearTop.setTextureSize(64, 32);
		gearTop.mirror = true;
		setRotation(gearTop, 0F, 0F, 0F);
		gearBottom = new ModelRenderer(this, 0, 16);
		gearBottom.addBox(-2F, 0F, -2F, 4, 1, 4);
		gearBottom.setRotationPoint(0F, 18F, 0F);
		gearBottom.setTextureSize(64, 32);
		gearBottom.mirror = true;
		setRotation(gearBottom, 0F, 0F, 0F);
		gearFront = new ModelRenderer(this, 0, 16);
		gearFront.addBox(-2F, 0F, -2F, 4, 1, 4);
		gearFront.setRotationPoint(0F, 16F, -3F);
		gearFront.setTextureSize(64, 32);
		gearFront.mirror = true;
		setRotation(gearFront, 1.570796F, 0F, 0F);
		gearBack = new ModelRenderer(this, 0, 16);
		gearBack.addBox(-2F, 0F, -2F, 4, 1, 4);
		gearBack.setRotationPoint(0F, 16F, 2F);
		gearBack.setTextureSize(64, 32);
		gearBack.mirror = true;
		setRotation(gearBack, 1.570796F, 0F, 0F);
		gearLeft = new ModelRenderer(this, 0, 16);
		gearLeft.addBox(-2F, 0F, -2F, 4, 1, 4);
		gearLeft.setRotationPoint(2F, 16F, 0F);
		gearLeft.setTextureSize(64, 32);
		gearLeft.mirror = true;
		setRotation(gearLeft, 1.570796F, 1.570796F, 0F);
		gearRigth = new ModelRenderer(this, 0, 16);
		gearRigth.addBox(-2F, 0F, -2F, 4, 1, 4);
		gearRigth.setRotationPoint(-3F, 16F, 0F);
		gearRigth.setTextureSize(64, 32);
		gearRigth.mirror = true;
		setRotation(gearRigth, 1.570796F, 1.570796F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		mainBox.render(f5);
		top.render(f5);
		bottom.render(f5);
		front.render(f5);
		back.render(f5);
		left.render(f5);
		rigth.render(f5);
		gearTop.render(f5);
		gearBottom.render(f5);
		gearFront.render(f5);
		gearBack.render(f5);
		gearLeft.render(f5);
		gearRigth.render(f5);
	}

	public void renderModel(float f5, List<ForgeDirection> par2){
		mainBox.render(f5);
		for(ForgeDirection x : par2){
			if(x == ForgeDirection.UP){
				top.render(f5);
				gearTop.render(f5);
			}
			else if(x == ForgeDirection.DOWN){
				bottom.render(f5);
				gearBottom.render(f5);
			}
			else if(x == ForgeDirection.SOUTH){
				front.render(f5);
				gearFront.render(f5);
			}
			else if(x == ForgeDirection.NORTH){
				back.render(f5);
				gearBack.render(f5);
			}
			else if(x == ForgeDirection.EAST){
				left.render(f5);
				gearLeft.render(f5);
			}
			else if(x == ForgeDirection.WEST){
				rigth.render(f5);
				gearRigth.render(f5);
			}
		}
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
		List<ForgeDirection> list = new ArrayList();
		list.add(ForgeDirection.UP);
		list.add(ForgeDirection.NORTH);
		this.renderModel(0.0625F, list);
	}

}
