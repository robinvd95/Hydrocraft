package hydrocraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJetCutter extends ModelBase{
	ModelRenderer base;
	ModelRenderer pillar1;
	ModelRenderer pillar2;
	ModelRenderer pillar3;
	ModelRenderer pillar4;
	ModelRenderer bar1;
	ModelRenderer bar2;
	ModelRenderer bar3;
	ModelRenderer bar4;
	ModelRenderer windowFront;
	ModelRenderer windowRigth;
	ModelRenderer windowBack;
	ModelRenderer windowLeft;
	ModelRenderer movingBarHorizontal;
	ModelRenderer movingBarVertical;
	ModelRenderer cutterBase;
	ModelRenderer cutter;

	public ModelJetCutter(){
		textureWidth = 256;
		textureHeight = 128;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(0F, 0F, 0F, 48, 16, 48);
		base.setRotationPoint(-24F, 8F, -24F);
		base.setTextureSize(256, 128);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		pillar1 = new ModelRenderer(this, 0, 0);
		pillar1.addBox(0F, 0F, 0F, 4, 32, 4);
		pillar1.setRotationPoint(-24F, -24F, -24F);
		pillar1.setTextureSize(256, 128);
		pillar1.mirror = true;
		setRotation(pillar1, 0F, 0F, 0F);
		pillar2 = new ModelRenderer(this, 0, 0);
		pillar2.addBox(0F, 0F, 0F, 4, 32, 4);
		pillar2.setRotationPoint(20F, -24F, -24F);
		pillar2.setTextureSize(256, 128);
		pillar2.mirror = true;
		setRotation(pillar2, 0F, 0F, 0F);
		pillar3 = new ModelRenderer(this, 0, 0);
		pillar3.addBox(0F, 0F, 0F, 4, 32, 4);
		pillar3.setRotationPoint(-24F, -24F, 20F);
		pillar3.setTextureSize(256, 128);
		pillar3.mirror = true;
		setRotation(pillar3, 0F, 0F, 0F);
		pillar4 = new ModelRenderer(this, 0, 0);
		pillar4.addBox(0F, 0F, 0F, 4, 32, 4);
		pillar4.setRotationPoint(20F, -24F, 20F);
		pillar4.setTextureSize(256, 128);
		pillar4.mirror = true;
		setRotation(pillar4, 0F, 0F, 0F);
		bar1 = new ModelRenderer(this, 0, 0);
		bar1.addBox(0F, 0F, 0F, 4, 40, 4);
		bar1.setRotationPoint(-24F, -20F, -20F);
		bar1.setTextureSize(256, 128);
		bar1.mirror = true;
		setRotation(bar1, 1.579523F, 0F, 0F);
		bar2 = new ModelRenderer(this, 0, 0);
		bar2.addBox(0F, 0F, 0F, 4, 40, 4);
		bar2.setRotationPoint(20F, -20F, -20F);
		bar2.setTextureSize(256, 128);
		bar2.mirror = true;
		setRotation(bar2, 1.579523F, 0F, 0F);
		bar3 = new ModelRenderer(this, 0, 0);
		bar3.addBox(0F, 0F, 0F, 4, 40, 4);
		bar3.setRotationPoint(-20F, -20F, -20F);
		bar3.setTextureSize(256, 128);
		bar3.mirror = true;
		setRotation(bar3, 1.579523F, 1.579523F, 0F);
		bar4 = new ModelRenderer(this, 0, 0);
		bar4.addBox(0F, 0F, 0F, 4, 40, 4);
		bar4.setRotationPoint(-20F, -20F, 24F);
		bar4.setTextureSize(256, 128);
		bar4.mirror = true;
		setRotation(bar4, 1.579523F, 1.579523F, 0F);
		windowFront = new ModelRenderer(this, 0, 64);
		windowFront.addBox(0F, 0F, 0F, 40, 28, 1);
		windowFront.setRotationPoint(-20F, -20F, -22F);
		windowFront.setTextureSize(256, 128);
		windowFront.mirror = true;
		setRotation(windowFront, 0F, 0F, 0F);
		windowRigth = new ModelRenderer(this, 0, 64);
		windowRigth.addBox(0F, 0F, 0F, 40, 28, 1);
		windowRigth.setRotationPoint(-22F, -20F, 20F);
		windowRigth.setTextureSize(256, 128);
		windowRigth.mirror = true;
		setRotation(windowRigth, 0F, 1.579523F, 0F);
		windowBack = new ModelRenderer(this, 0, 64);
		windowBack.addBox(0F, 0F, 0F, 40, 28, 1);
		windowBack.setRotationPoint(-20F, -20F, 21F);
		windowBack.setTextureSize(256, 128);
		windowBack.mirror = true;
		setRotation(windowBack, 0F, 0F, 0F);
		windowLeft = new ModelRenderer(this, 0, 64);
		windowLeft.addBox(0F, 0F, 0F, 40, 28, 1);
		windowLeft.setRotationPoint(21F, -20F, 20F);
		windowLeft.setTextureSize(256, 128);
		windowLeft.mirror = true;
		setRotation(windowLeft, 0F, 1.579523F, 0F);
		movingBarHorizontal = new ModelRenderer(this, 0, 93);
		movingBarHorizontal.addBox(0F, 0F, 0F, 40, 2, 2);
		movingBarHorizontal.setRotationPoint(-20F, -23F, -1F);
		movingBarHorizontal.setTextureSize(256, 128);
		movingBarHorizontal.mirror = true;
		setRotation(movingBarHorizontal, 0F, 0F, 0F);
		movingBarVertical = new ModelRenderer(this, 0, 93);
		movingBarVertical.addBox(0F, 0F, 0F, 40, 2, 2);
		movingBarVertical.setRotationPoint(-1F, -23F, 20F);
		movingBarVertical.setTextureSize(256, 128);
		movingBarVertical.mirror = true;
		setRotation(movingBarVertical, 0F, 1.579523F, 0F);
		cutterBase = new ModelRenderer(this, 16, 0);
		cutterBase.addBox(-2F, -2F, -2F, 4, 4, 4);
		cutterBase.setRotationPoint(0F, -22F, 0F);
		cutterBase.setTextureSize(256, 128);
		cutterBase.mirror = true;
		setRotation(cutterBase, 0F, 0F, 0F);
		cutter = new ModelRenderer(this, 16, 8);
		cutter.addBox(-1F, 2F, -1F, 2, 6, 2);
		cutter.setRotationPoint(0F, -22F, 0F);
		cutter.setTextureSize(256, 128);
		cutter.mirror = true;
		setRotation(cutter, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		base.render(f5);
		pillar1.render(f5);
		pillar2.render(f5);
		pillar3.render(f5);
		pillar4.render(f5);
		bar1.render(f5);
		bar2.render(f5);
		bar3.render(f5);
		bar4.render(f5);
		windowFront.render(f5);
		windowRigth.render(f5);
		windowBack.render(f5);
		windowLeft.render(f5);
		movingBarHorizontal.render(f5);
		movingBarVertical.render(f5);
		cutterBase.render(f5);
		cutter.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	public void renderModel(float f5) {
		base.render(f5);
		pillar1.render(f5);
		pillar2.render(f5);
		pillar3.render(f5);
		pillar4.render(f5);
		bar1.render(f5);
		bar2.render(f5);
		bar3.render(f5);
		bar4.render(f5);
		windowFront.render(f5);
		windowRigth.render(f5);
		windowBack.render(f5);
		windowLeft.render(f5);
		movingBarHorizontal.render(f5);
		movingBarVertical.render(f5);
		cutterBase.render(f5);
		cutter.render(f5);
	}

}
