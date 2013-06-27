package hydrocraft.client.renderer;

import hydrocraft.client.models.ModelEnergyStorage;
import hydrocraft.tileentity.TileEntityEnergyStorage;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderEnergyStorage extends TileEntitySpecialRenderer{

	private ModelBase model = new ModelBase(){};
	ModelRenderer bottom;
	ModelRenderer connectorFront;
	ModelRenderer connectorRigth;
	ModelRenderer connectorBack;
	ModelRenderer connectorLeft;
	ModelRenderer storage;
	ModelRenderer inputPillar1;
	ModelRenderer inputPillar2;
	ModelRenderer inputPillar3;
	ModelRenderer inputPillar4;
	ModelRenderer inputPlatform;
	ModelRenderer glass;
	
	public RenderEnergyStorage(){
		
		model.textureWidth = 128;
		model.textureHeight = 64;
		
		bottom = new ModelRenderer(model, 0, 0);
		bottom.addBox(0F, 0F, 0F, 16, 2, 16);
		bottom.setRotationPoint(-8F, 22F, -8F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		connectorFront = new ModelRenderer(model, 0, 18);
		connectorFront.addBox(0F, 0F, 0F, 4, 8, 1);
		connectorFront.setRotationPoint(2F, 14F, -7F);
		connectorFront.setTextureSize(128, 64);
		connectorFront.mirror = true;
		setRotation(connectorFront, 0F, -3.141593F, 0F);
		connectorRigth = new ModelRenderer(model, 0, 18);
		connectorRigth.addBox(0F, 0F, 0F, 4, 8, 1);
		connectorRigth.setRotationPoint(-7F, 14F, -2F);
		connectorRigth.setTextureSize(128, 64);
		connectorRigth.mirror = true;
		setRotation(connectorRigth, 0F, -1.570796F, 0F);
		connectorBack = new ModelRenderer(model, 0, 18);
		connectorBack.addBox(0F, 0F, 0F, 4, 8, 1);
		connectorBack.setRotationPoint(-2F, 14F, 7F);
		connectorBack.setTextureSize(128, 64);
		connectorBack.mirror = true;
		setRotation(connectorBack, 0F, 0F, 0F);
		connectorLeft = new ModelRenderer(model, 0, 18);
		connectorLeft.addBox(0F, 0F, 0F, 4, 8, 1);
		connectorLeft.setRotationPoint(7F, 14F, 2F);
		connectorLeft.setTextureSize(128, 64);
		connectorLeft.mirror = true;
		setRotation(connectorLeft, 0F, 1.570796F, 0F);
		storage = new ModelRenderer(model, 0, 0);
		storage.addBox(0F, 0F, 0F, 4, 11, 4);
		storage.setRotationPoint(-2F, 11F, -2F);
		storage.setTextureSize(128, 64);
		storage.mirror = true;
		setRotation(storage, 0F, 0F, 0F);
		inputPillar1 = new ModelRenderer(model, 10, 18);
		inputPillar1.addBox(0F, 0F, 0F, 1, 2, 1);
		inputPillar1.setRotationPoint(-2F, 9F, -2F);
		inputPillar1.setTextureSize(128, 64);
		inputPillar1.mirror = true;
		setRotation(inputPillar1, 0F, 0F, 0F);
		inputPillar2 = new ModelRenderer(model, 10, 18);
		inputPillar2.addBox(0F, 0F, 0F, 1, 2, 1);
		inputPillar2.setRotationPoint(1F, 9F, -2F);
		inputPillar2.setTextureSize(128, 64);
		inputPillar2.mirror = true;
		setRotation(inputPillar2, 0F, 0F, 0F);
		inputPillar3 = new ModelRenderer(model, 10, 18);
		inputPillar3.addBox(0F, 0F, 0F, 1, 2, 1);
		inputPillar3.setRotationPoint(-2F, 9F, 1F);
		inputPillar3.setTextureSize(128, 64);
		inputPillar3.mirror = true;
		setRotation(inputPillar3, 0F, 0F, 0F);
		inputPillar4 = new ModelRenderer(model, 10, 18);
		inputPillar4.addBox(0F, 0F, 0F, 1, 2, 1);
		inputPillar4.setRotationPoint(1F, 9F, 1F);
		inputPillar4.setTextureSize(128, 64);
		inputPillar4.mirror = true;
		setRotation(inputPillar4, 0F, 0F, 0F);
		inputPlatform = new ModelRenderer(model, 10, 18);
		inputPlatform.addBox(0F, 0F, 0F, 4, 1, 4);
		inputPlatform.setRotationPoint(-2F, 8F, -2F);
		inputPlatform.setTextureSize(128, 64);
		inputPlatform.mirror = true;
		setRotation(inputPlatform, 0F, 0F, 0F);
		glass = new ModelRenderer(model, 0, 29);
		glass.addBox(0F, 0F, 0F, 16, 14, 16);
		glass.setRotationPoint(-8F, 8F, -8F);
		glass.setTextureSize(128, 64);
		glass.mirror = true;
		setRotation(glass, 0F, 0F, 0F);
	}
	
	private void setRotation(ModelRenderer r, float x, float y, float z)
	{
		r.rotateAngleX = x;
		r.rotateAngleY = y;
		r.rotateAngleZ = z;
	}
	
	public void render(double x, double y, double z){
		GL11.glPushMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		this.bindTexture("/mods/hydrocraft/textures/models/energyStorage.png");
		bottom.render(0.0625f);
		connectorFront.render(0.0625f);
		connectorRigth.render(0.0625f);
		connectorBack.render(0.0625f);
		connectorLeft.render(0.0625f);
		storage.render(0.0625f);
		inputPillar1.render(0.0625f);
		inputPillar2.render(0.0625f);
		inputPillar3.render(0.0625f);
		inputPillar4.render(0.0625f);
		inputPlatform.render(0.0625f);
		glass.render(0.0625f);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void bindTexture(String par1){
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(par1);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		this.render(d0, d1, d2);
	}
}
