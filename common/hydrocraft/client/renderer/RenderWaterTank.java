package hydrocraft.client.renderer;

import hydrocraft.client.models.ModelWaterTank;
import hydrocraft.tileentity.TileEntityWaterTank;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.liquids.LiquidStack;

import org.lwjgl.opengl.GL11;

import buildcraft.core.render.LiquidRenderer;

public class RenderWaterTank extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

		TileEntityWaterTank tank = ((TileEntityWaterTank) tileentity);

		LiquidStack liquid = tank.tank.getLiquid();
		if (liquid == null || liquid.amount <= 0) {
			return;
		}

		int[] displayList = LiquidRenderer.getLiquidDisplayLists(liquid, tileentity.worldObj, false);
		if (displayList == null) {
			return;
		}

		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		bindTextureByName(LiquidRenderer.getLiquidSheet(liquid));

		GL11.glTranslatef((float) x + 0.125F, (float) y, (float) z + 0.125F);
		GL11.glScalef(0.75F, 0.999F, 0.75F);

		GL11.glCallList(displayList[(int) ((float) liquid.amount / (float) (tank.tank.getCapacity()) * (LiquidRenderer.DISPLAY_STAGES - 1))]);

		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
}