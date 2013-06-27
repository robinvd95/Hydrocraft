package hydrocraft.client.models;

import java.util.List;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeDirection;

public class ModelCable extends ModelHydrocraft{
	ModelRenderer center;

	ModelRenderer[] segment = new ModelRenderer[6];

	public ModelCable(){
		textureWidth = 64;
		textureHeight = 32;

		center = new ModelRenderer(this, 0, 10);
		center.addBox(0F, 0F, 0F, 4, 4, 4);
		center.setRotationPoint(-2F, 14F, -2F);
		center.setTextureSize(64, 32);
		center.mirror = true;
		setRotation(center, 0F, 0F, 0F);
		segment[1] = new ModelRenderer(this, 0, 0);
		segment[1].addBox(0F, 0F, 0F, 4, 6, 4);
		segment[1].setRotationPoint(-2F, 8F, -2F);
		segment[1].setTextureSize(64, 32);
		segment[1].mirror = true;
		setRotation(segment[1], 0F, 0F, 0F);
		segment[0] = new ModelRenderer(this, 0, 0);
		segment[0].addBox(0F, 0F, 0F, 4, 6, 4);
		segment[0].setRotationPoint(-2F, 18F, -2F);
		segment[0].setTextureSize(64, 32);
		segment[0].mirror = true;
		setRotation(segment[0], 0F, 0F, 0F);
		segment[2] = new ModelRenderer(this, 0, 0);
		segment[2].addBox(0F, 0F, 0F, 4, 6, 4);
		segment[2].setRotationPoint(-2F, 18F, 2F);
		segment[2].setTextureSize(64, 32);
		segment[2].mirror = true;
		setRotation(segment[2], 1.570796F, 0F, 0F);
		segment[3] = new ModelRenderer(this, 0, 0);
		segment[3].addBox(0F, 0F, 0F, 4, 6, 4);
		segment[3].setRotationPoint(-2F, 18F, -8F);
		segment[3].setTextureSize(64, 32);
		segment[3].mirror = true;
		setRotation(segment[3], 1.570796F, 0F, 0F);
		segment[4] = new ModelRenderer(this, 0, 0);
		segment[4].addBox(0F, 0F, 0F, 4, 6, 4);
		segment[4].setRotationPoint(-2F, 14F, -2F);
		segment[4].setTextureSize(64, 32);
		segment[4].mirror = true;
		setRotation(segment[4], 0F, 0F, 1.570796F);
		segment[5] = new ModelRenderer(this, 0, 0);
		segment[5].addBox(0F, 0F, 0F, 4, 6, 4);
		segment[5].setRotationPoint(8F, 14F, -2F);
		segment[5].setTextureSize(64, 32);
		segment[5].mirror = true;
		setRotation(segment[5], 0F, 0F, 1.570796F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		center.render(f5);
		segment[1].render(f5);
		segment[0].render(f5);
		segment[2].render(f5);
		segment[3].render(f5);
		segment[4].render(f5);
		segment[5].render(f5);
	}

	public void renderModel(float f, List<ForgeDirection> directionID) {
		center.render(f);
		for(ForgeDirection x : directionID){
			if(this.segment[x.ordinal()] != null){
				this.segment[x.ordinal()].render(0.0625f);
			}
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void renderInventory() {
		center.render(0.0625f);
		segment[2].render(0.0625f);
		segment[3].render(0.0625f);
		segment[4].render(0.0625f);
		segment[5].render(0.0625f);
	}
}
