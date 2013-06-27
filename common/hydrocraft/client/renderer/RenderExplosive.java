package hydrocraft.client.renderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderExplosive extends TileEntitySpecialRenderer{

	private final String texture;
	private ModelBase model = new ModelBase(){};

	ModelRenderer corner0;
	ModelRenderer corner1;
	ModelRenderer corner2;
	ModelRenderer corner3;
	ModelRenderer center;
	ModelRenderer side0;
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer side3;
	ModelRenderer corner4;
	ModelRenderer corner5;
	ModelRenderer corner6;
	ModelRenderer corner7;
	ModelRenderer side4;
	ModelRenderer side5;
	ModelRenderer side6;
	ModelRenderer side7;
	ModelRenderer side8;
	ModelRenderer side9;
	ModelRenderer side10;
	ModelRenderer side11;

	private void initialize(){

		model.textureWidth = 64;
		model.textureHeight = 32;

		corner0 = new ModelRenderer(model, 28, 0);
		corner0.addBox(0F, 0F, 0F, 2, 2, 2);
		corner0.setRotationPoint(-8F, 8F, -8F);
		corner0.setTextureSize(64, 32);
		corner0.mirror = true;
		setRotation(corner0, 0F, 0F, 0F);
		corner1 = new ModelRenderer(model, 28, 0);
		corner1.addBox(0F, 0F, 0F, 2, 2, 2);
		corner1.setRotationPoint(-8F, 8F, 8F);
		corner1.setTextureSize(64, 32);
		corner1.mirror = true;
		setRotation(corner1, 0F, 1.570796F, 0F);
		corner2 = new ModelRenderer(model, 28, 0);
		corner2.addBox(0F, 0F, 0F, 2, 2, 2);
		corner2.setRotationPoint(8F, 8F, 8F);
		corner2.setTextureSize(64, 32);
		corner2.mirror = true;
		setRotation(corner2, 0F, 3.141593F, 0F);
		corner3 = new ModelRenderer(model, 28, 0);
		corner3.addBox(0F, 0F, 0F, 2, 2, 2);
		corner3.setRotationPoint(8F, 8F, -8F);
		corner3.setTextureSize(64, 32);
		corner3.mirror = true;
		setRotation(corner3, 0F, -1.570796F, 0F);
		center = new ModelRenderer(model, 0, 4);
		center.addBox(0F, 0F, 0F, 12, 12, 12);
		center.setRotationPoint(-6F, 10F, -6F);
		center.setTextureSize(64, 32);
		center.mirror = true;
		setRotation(center, 0F, 0F, 0F);
		side0 = new ModelRenderer(model, 0, 0);
		side0.addBox(0F, 0F, 0F, 12, 2, 2);
		side0.setRotationPoint(-6F, 8F, -8F);
		side0.setTextureSize(64, 32);
		side0.mirror = true;
		setRotation(side0, 0F, 0F, 0F);
		side1 = new ModelRenderer(model, 0, 0);
		side1.addBox(0F, 0F, 0F, 12, 2, 2);
		side1.setRotationPoint(-6F, 8F, 6F);
		side1.setTextureSize(64, 32);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(model, 0, 0);
		side2.addBox(0F, 0F, 0F, 12, 2, 2);
		side2.setRotationPoint(-8F, 8F, 6F);
		side2.setTextureSize(64, 32);
		side2.mirror = true;
		setRotation(side2, 0F, 1.570796F, 0F);
		side3 = new ModelRenderer(model, 0, 0);
		side3.addBox(0F, 0F, 0F, 12, 2, 2);
		side3.setRotationPoint(6F, 8F, 6F);
		side3.setTextureSize(64, 32);
		side3.mirror = true;
		setRotation(side3, 0F, 1.570796F, 0F);
		corner4 = new ModelRenderer(model, 28, 0);
		corner4.addBox(0F, 0F, 0F, 2, 2, 2);
		corner4.setRotationPoint(8F, 24F, -8F);
		corner4.setTextureSize(64, 32);
		corner4.mirror = true;
		setRotation(corner4, 0F, 0F, 3.141593F);
		corner5 = new ModelRenderer(model, 28, 0);
		corner5.addBox(0F, 0F, 0F, 2, 2, 2);
		corner5.setRotationPoint(-8F, 24F, -8F);
		corner5.setTextureSize(64, 32);
		corner5.mirror = true;
		setRotation(corner5, 0F, 1.570796F, 3.141593F);
		corner6 = new ModelRenderer(model, 28, 0);
		corner6.addBox(0F, 0F, 0F, 2, 2, 2);
		corner6.setRotationPoint(-8F, 24F, 8F);
		corner6.setTextureSize(64, 32);
		corner6.mirror = true;
		setRotation(corner6, 0F, 3.141593F, 3.141593F);
		corner7 = new ModelRenderer(model, 28, 0);
		corner7.addBox(0F, 0F, 0F, 2, 2, 2);
		corner7.setRotationPoint(8F, 24F, 8F);
		corner7.setTextureSize(64, 32);
		corner7.mirror = true;
		setRotation(corner7, 0F, -1.570796F, 3.141593F);
		side4 = new ModelRenderer(model, 0, 0);
		side4.addBox(0F, 0F, 0F, 12, 2, 2);
		side4.setRotationPoint(-8F, 22F, 6F);
		side4.setTextureSize(64, 32);
		side4.mirror = true;
		setRotation(side4, 0F, 1.570796F, 0F);
		side5 = new ModelRenderer(model, 0, 0);
		side5.addBox(0F, 0F, 0F, 12, 2, 2);
		side5.setRotationPoint(6F, 22F, 6F);
		side5.setTextureSize(64, 32);
		side5.mirror = true;
		setRotation(side5, 0F, 1.570796F, 0F);
		side6 = new ModelRenderer(model, 0, 0);
		side6.addBox(0F, 0F, 0F, 12, 2, 2);
		side6.setRotationPoint(-6F, 22F, -8F);
		side6.setTextureSize(64, 32);
		side6.mirror = true;
		setRotation(side6, 0F, 0F, 0F);
		side7 = new ModelRenderer(model, 0, 0);
		side7.addBox(0F, 0F, 0F, 12, 2, 2);
		side7.setRotationPoint(8F, 10F, 6F);
		side7.setTextureSize(64, 32);
		side7.mirror = true;
		setRotation(side7, 0F, 0F, 1.570796F);
		side8 = new ModelRenderer(model, 0, 0);
		side8.addBox(0F, 0F, 0F, 12, 2, 2);
		side8.setRotationPoint(8F, 10F, -8F);
		side8.setTextureSize(64, 32);
		side8.mirror = true;
		setRotation(side8, 0F, 0F, 1.570796F);
		side9 = new ModelRenderer(model, 0, 0);
		side9.addBox(0F, 0F, 0F, 12, 2, 2);
		side9.setRotationPoint(-6F, 10F, 6F);
		side9.setTextureSize(64, 32);
		side9.mirror = true;
		setRotation(side9, 0F, 0F, 1.570796F);
		side10 = new ModelRenderer(model, 0, 0);
		side10.addBox(0F, 0F, 0F, 12, 2, 2);
		side10.setRotationPoint(-6F, 10F, -8F);
		side10.setTextureSize(64, 32);
		side10.mirror = true;
		setRotation(side10, 0F, 0F, 1.570796F);
		side11 = new ModelRenderer(model, 0, 0);
		side11.addBox(0F, 0F, 0F, 12, 2, 2);
		side11.setRotationPoint(-6F, 22F, 6F);
		side11.setTextureSize(64, 32);
		side11.mirror = true;
		setRotation(side11, 0F, 0F, 0F);
	}

	public RenderExplosive(String par1){
		this.texture = par1;
		this.initialize();

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
		this.bindTexture("/mods/hydrocraft/textures/models/hydrogenBomb.png");
		float f = 0.0625f;
		corner0.render(f);
		corner1.render(f);
		corner2.render(f);
		corner3.render(f);
		center.render(f);
		side0.render(f);
		side1.render(f);
		side2.render(f);
		side3.render(f);
		corner4.render(f);
		corner5.render(f);
		corner6.render(f);
		corner7.render(f);
		side4.render(f);
		side5.render(f);
		side6.render(f);
		side7.render(f);
		side8.render(f);
		side9.render(f);
		side10.render(f);
		side11.render(f);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer par1, float x, float y, float z){
		par1.rotateAngleX = x;
		par1.rotateAngleY = y;
		par1.rotateAngleZ = z;
	}

	public void bindTexture(String par1){
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(par1);
	}

}
