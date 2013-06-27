package hydrocraft.client.renderer;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;
import hydrocraft.api.utils.Utils;
import hydrocraft.tileentity.TileEntityTank;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import buildcraft.core.render.LiquidRenderer.LiquidCanonException;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidStack;

public class RenderTank extends TileEntitySpecialRenderer{

	private ModelBase model = new ModelBase(){};
	private ModelRenderer blocks;

	public RenderTank(){
		blocks = new ModelRenderer(model, 0, 0);
		blocks.addBox(-8F, -8F, -8F, 16, 16, 16);
		blocks.setRotationPoint(0F, 0F, 0F);
		blocks.setTextureSize(64, 32);
	}

	private static RenderBlocks renderBlocks = new RenderBlocks();

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float ff) {
		TileEntityTank tank = (TileEntityTank) tileentity;
		if(tank == null){
			return;
		}
		
		if(tank.getLiquidTank() == null){
			return;
		}
		
		if(!tank.getIsValid() || !tank.getLiquidTank().containsValidLiquid()){
			return;
		}
		
		float diameter = tank.getTankDiameter() / 2 + 0.5f;
		
		float tankHeigth = tank.getTankHeigth() - 1;
		float tankCapacity = tank.getLiquidTank().getCapacity();
		float tankAmount = tank.getLiquidTank().getLiquid().amount;
		
		float heigth = (tankAmount * tankHeigth / tankCapacity);
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d0+0.5f, (float)d1+1f, (float)d2+0.5f);

		bindTextureByName(this.getLiquidSheet(new LiquidStack(Block.waterStill, 1)));
		
		float f = 0.5F;
		float f1 = 1.0F;
		float f2 = 0.8F;
		float f3 = 0.6F;

		int i = (int) d0;
		int j = (int) d1;
		int k = (int) d2;

		IBlockAccess blockAccess = tileentity.worldObj;

		Block block = Block.waterStill;

		Tessellator tessellator = Tessellator.instance;

		renderBlocks.renderMaxX = diameter-0.01;
		renderBlocks.renderMinX = -diameter+0.01;
		renderBlocks.renderMaxY = heigth + 0.01;
		renderBlocks.renderMinY = 0;
		renderBlocks.renderMaxZ = diameter-0.01;
		renderBlocks.renderMinZ = -diameter+0.01;
		renderBlocks.enableAO = false;

		tessellator.startDrawingQuads();

		float f4 = 0, f5 = 0;
		renderBlocks.renderFaceYNeg(null, 0, 0, 0, block.getBlockTextureFromSide(0));
		renderBlocks.renderFaceYPos(null, 0, 0, 0, block.getBlockTextureFromSide(1));
		renderBlocks.renderFaceZNeg(null, 0, 0, 0, block.getBlockTextureFromSide(2));
		renderBlocks.renderFaceZPos(null, 0, 0, 0, block.getBlockTextureFromSide(3));
		renderBlocks.renderFaceXNeg(null, 0, 0, 0, block.getBlockTextureFromSide(4));
		renderBlocks.renderFaceXPos(null, 0, 0, 0, block.getBlockTextureFromSide(5));
		tessellator.draw();
		GL11.glPopMatrix();
	}

	public void render(double x, double y, double z, float scaleWidth, float scaleHeight){

		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glColor3f(1, 1, 1);

		this.bindTextureByName("");

		GL11.glTranslatef((float) x+0.5f, (float) y+1.5f + scaleHeight / 3, (float) z+0.5f);
		GL11.glScalef(scaleWidth, scaleHeight, scaleWidth);
		//block.render(0.0625f);

		GL11.glPopAttrib();
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

}
