package hydrocraft.client.models;

import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeDirection;

public class ModelPipe extends ModelHydrocraft {
	ModelRenderer center;
	/*
	 * ModelRenderer segment[0]; ModelRenderer segment[1]; ModelRenderer
	 * segment[2]; ModelRenderer segment[3]; ModelRenderer segment[5]; ModelRenderer
	 * segment[4];
	 */
	ModelRenderer[] segment = new ModelRenderer[6];

	public ModelPipe() {
		textureWidth = 64;
		textureHeight = 32;

		center = new ModelRenderer(this, 0, 0);
		center.addBox(0F, 0F, 0F, 8, 8, 8);
		center.setRotationPoint(-4F, 12F, -4F);
		center.setTextureSize(64, 32);
		center.mirror = true;
		setRotation(center, 0F, 0F, 0F);
		segment[1] = new ModelRenderer(this, 0, 0);
		segment[1].addBox(0F, 0F, 0F, 8, 4, 8);
		segment[1].setRotationPoint(-4F, 8F, -4F);
		segment[1].setTextureSize(64, 32);
		segment[1].mirror = true;
		setRotation(segment[1], 0F, 0F, 0F);
		segment[0] = new ModelRenderer(this, 0, 0);
		segment[0].addBox(0F, 0F, 0F, 8, 4, 8);
		segment[0].setRotationPoint(-4F, 20F, -4F);
		segment[0].setTextureSize(64, 32);
		segment[0].mirror = true;
		setRotation(segment[0], 0F, 0F, 0F);
		segment[3] = new ModelRenderer(this, 0, 0);
		segment[3].addBox(0F, 0F, 0F, 8, 4, 8);
		segment[3].setRotationPoint(-4F, 20F, -8F);
		segment[3].setTextureSize(64, 32);
		segment[3].mirror = true;
		setRotation(segment[3], 1.570796F, 0F, 0F);
		segment[2] = new ModelRenderer(this, 0, 0);
		segment[2].addBox(0F, 0F, 0F, 8, 4, 8);
		segment[2].setRotationPoint(-4F, 20F, 4F);
		segment[2].setTextureSize(64, 32);
		segment[2].mirror = true;
		setRotation(segment[2], 1.58825F, 0F, 0F);
		segment[4] = new ModelRenderer(this, 0, 0);
		segment[4].addBox(0F, 0F, 0F, 8, 4, 8);
		segment[4].setRotationPoint(-4F, 12F, -4F);
		segment[4].setTextureSize(64, 32);
		segment[4].mirror = true;
		setRotation(segment[4], 0F, 0F, 1.570796F);
		segment[5] = new ModelRenderer(this, 0, 0);
		segment[5].addBox(0F, 0F, 0F, 8, 4, 8);
		segment[5].setRotationPoint(4F, 20F, -4F);
		segment[5].setTextureSize(64, 32);
		segment[5].mirror = true;
		setRotation(segment[5], 0F, 0F, -1.570796F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		center.render(f5);
		segment[0].render(f5);
		segment[1].render(f5);
		segment[2].render(f5);
		segment[3].render(f5);
		segment[5].render(f5);
		segment[4].render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	public void renderModel(float f, List<Integer> directionID) {
		center.render(f);
		for(Integer x : directionID){
			this.segment[x].render(0.0625f);
		}
	}

	@Override
	public void renderInventory() {
		this.center.render(0.0625f);
		this.segment[0].render(0.0625f);
		this.segment[1].render(0.0625f);
	}

}
