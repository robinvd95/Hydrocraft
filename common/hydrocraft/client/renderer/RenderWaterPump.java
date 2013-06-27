package hydrocraft.client.renderer;

import hydrocraft.client.IInventoryRenderer;
import hydrocraft.client.models.ModelWaterPump;
import hydrocraft.tileentity.TileEntityTank;
import hydrocraft.tileentity.TileEntityWaterPump;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

import org.lwjgl.opengl.GL11;

import buildcraft.core.render.LiquidRenderer.LiquidCanonException;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderWaterPump extends TileEntitySpecialRenderer implements IInventoryRenderer{

	private static RenderBlocks renderBlocks = new RenderBlocks();
	
	float factor = 0.0625f;
	
	private ModelBase model = new ModelBase(){};
	
	ModelRenderer bottom;
	ModelRenderer glass;
	ModelRenderer top;
	ModelRenderer tank;
	ModelRenderer pump;
	
	public RenderWaterPump(){
		bottom = new ModelRenderer(model, 0, 14);
		bottom.addBox(-8F, -8F, -8F, 16, 2, 16);
		bottom.setRotationPoint(8F, 8F, 8F);
		bottom.setTextureSize(64, 32);

		glass = new ModelRenderer(model, 0, -16);
		glass.addBox(-8F, -6F, -8F, 16, 12, 16);
		glass.setRotationPoint(8F, 8F, 8F);
		glass.setTextureSize(64, 32);

		top = new ModelRenderer(model, 0, 14);
		top.addBox(-8F, 6F, -8F, 16, 2, 16);
		top.setRotationPoint(8F, 8F, 8F);
		top.setTextureSize(64, 32);

		tank = new ModelRenderer(model, 0, 14);
		tank.addBox(-2F, -6F, -2F, 4, 12, 4);
		tank.setRotationPoint(8F, 8F, 8F);
		tank.setTextureSize(64, 32);

		pump = new ModelRenderer(model, 48, 18);
		pump.addBox(-4F, -6F, -4F, 8, 2, 8);
		pump.setRotationPoint(8F, 8F, 8F);
		pump.setTextureSize(64, 32);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		TileEntityWaterPump tile = (TileEntityWaterPump) var1;
		renderModel(tile.getProgress(), var2, var4, var6);
		LiquidTank tank = tile.getLiquidTank();
		if(tank != null && tank.containsValidLiquid()){
			this.renderWater(tank.getCapacity(), tank.getLiquid().amount, var2, var4, var6);
		}
	}
	
	private void renderModel(float animation, double x, double y, double z){
		this.bindTexture("/mods/hydrocraft/textures/models/waterPump.png");
		
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glColor3f(1, 1, 1);
		
		GL11.glTranslatef((float) x, (float) y, (float) z);
		
		this.bottom.render(factor);
		this.glass.render(factor);
		this.tank.render(factor);
		this.top.render(factor);
		
		float translateFactor;
		if(animation < 0.5f){
			translateFactor = animation * (0.6325f * 2f);
		}else{
			translateFactor = (1f-animation) * 0.6325f * 2f;
		}
		
		this.bindTexture("/mods/hydrocraft/textures/models/waterWheel.png");
		GL11.glTranslatef(0, translateFactor, 0);
		this.pump.render(factor);
		GL11.glTranslatef(0, -translateFactor, 0);
		
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
	
	public void renderWater(int capacity, int amount, double x, double y, double z) {
		
		float heigth = ((float)amount * 0.875f / (float)capacity);
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);

		bindTextureByName(this.getLiquidSheet(new LiquidStack(Block.waterStill, 1)));

		Block block = Block.waterStill;

		Tessellator tessellator = Tessellator.instance;

		renderBlocks.renderMaxX = 0.9375f;
		renderBlocks.renderMinX = 0.0625f;
		renderBlocks.renderMaxY = heigth -0.01f;
		renderBlocks.renderMinY = 0.125f;
		renderBlocks.renderMaxZ = 0.9375f;
		renderBlocks.renderMinZ = 0.0625f;
		renderBlocks.enableAO = false;

		tessellator.startDrawingQuads();

		renderBlocks.renderFaceYNeg(null, 0, 0, 0, block.getBlockTextureFromSide(0));
		renderBlocks.renderFaceYPos(null, 0, 0, 0, block.getBlockTextureFromSide(1));
		renderBlocks.renderFaceZNeg(null, 0, 0, 0, block.getBlockTextureFromSide(2));
		renderBlocks.renderFaceZPos(null, 0, 0, 0, block.getBlockTextureFromSide(3));
		renderBlocks.renderFaceXNeg(null, 0, 0, 0, block.getBlockTextureFromSide(4));
		renderBlocks.renderFaceXPos(null, 0, 0, 0, block.getBlockTextureFromSide(5));
		tessellator.draw();
		GL11.glPopMatrix();
	}
	
	public String getLiquidSheet(LiquidStack liquid) {
		if (liquid == null || liquid.itemID <= 0) {
			return "/terrain.png";
		}
		LiquidStack canon = liquid.canonical();
		if (canon == null) {
			throw new LiquidCanonException(liquid);
		}
		return canon.getTextureSheet();
	}

	@Override
	public void renderInventory() {
		this.renderModel(0, -.5f, -.5f, -.5f);
	}
	
	public void bindTexture(String par1){
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(par1);
	}
}

