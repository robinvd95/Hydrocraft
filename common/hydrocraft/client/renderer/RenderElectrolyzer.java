package hydrocraft.client.renderer;

import hydrocraft.client.ClientProxy;
import hydrocraft.client.IInventoryRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderElectrolyzer extends TileEntitySpecialRenderer implements IInventoryRenderer{

	private ModelBase model = new ModelBase(){};
	ModelRenderer bottom;
	ModelRenderer tank;
	ModelRenderer trunk;
	ModelRenderer arm0;
	ModelRenderer arm1;
	ModelRenderer arm2;
	ModelRenderer arm3;
	ModelRenderer dripper0;
	ModelRenderer dripper1;
	ModelRenderer dripper2;
	ModelRenderer dripper3;

	public RenderElectrolyzer(){
		
		model.textureWidth = 64;
		model.textureHeight = 32;
		
		bottom = new ModelRenderer(model, 0, 14);
		bottom.addBox(0F, 0F, 0F, 16, 2, 16);
		bottom.setRotationPoint(-8F, 22F, -8F);
		bottom.setTextureSize(64, 32);

		tank = new ModelRenderer(model, 40, 0);
		tank.addBox(0F, 0F, 0F, 6, 4, 6);
		tank.setRotationPoint(-3F, 18F, -3F);
		tank.setTextureSize(64, 32);

		trunk = new ModelRenderer(model, 0, 0);
		trunk.addBox(-2F, 0F, -2F, 4, 10, 4);
		trunk.setRotationPoint(0F, 8F, 0F);
		trunk.setTextureSize(64, 32);

		arm0 = new ModelRenderer(model, 16, 0);
		arm0.addBox(-1F, 0F, -5F, 2, 2, 5);
		arm0.setRotationPoint(-2F, 10F, 0F);
		arm0.setTextureSize(64, 32);
		arm0.rotateAngleY = 1.570796F;
		
		arm1 = new ModelRenderer(model, 16, 0);
		arm1.addBox(0F, 0F, 0F, 2, 2, 5);
		arm1.setRotationPoint(-1F, 10F, 2F);
		arm1.setTextureSize(64, 32);

		arm2 = new ModelRenderer(model, 16, 0);
		arm2.addBox(0F, 0F, 0F, 2, 2, 5);
		arm2.setRotationPoint(2F, 10F, 1F);
		arm2.setTextureSize(64, 32);
		arm2.rotateAngleY = 1.570796F;
		
		arm3 = new ModelRenderer(model, 16, 0);
		arm3.addBox(0F, 0F, 0F, 2, 2, 5);
		arm3.setRotationPoint(-1F, 10F, -7F);
		arm3.setTextureSize(64, 32);

		dripper0 = new ModelRenderer(model, 0, 0);
		dripper0.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		dripper0.setRotationPoint(-6F, 12F, 0F);
		dripper0.setTextureSize(64, 32);

		dripper1 = new ModelRenderer(model, 0, 0);
		dripper1.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		dripper1.setRotationPoint(6F, 12F, 0F);
		dripper1.setTextureSize(64, 32);

		dripper2 = new ModelRenderer(model, 0, 0);
		dripper2.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		dripper2.setRotationPoint(0F, 12F, -6F);
		dripper2.setTextureSize(64, 32);

		dripper3 = new ModelRenderer(model, 0, 0);
		dripper3.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		dripper3.setRotationPoint(0F, 12F, 6F);
		dripper3.setTextureSize(64, 32);

	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		this.render(d0, d1, d2);
	}

	public void render(double x, double y, double z){
		GL11.glPushMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		this.bindTexture("/mods/hydrocraft/textures/models/electrolyzer.png");
		float f = 0.0625f;
		bottom.render(f);
		tank.render(f);
		trunk.render(f);
		arm0.render(f);
		arm1.render(f);
		arm2.render(f);
		arm3.render(f);
		dripper0.render(f);
		dripper1.render(f);
		dripper2.render(f);
		dripper3.render(f);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	public void bindTexture(String par1){
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(par1);
	}

	@Override
	public void renderInventory() {
		this.render(0, 0, 0);
	}

}
