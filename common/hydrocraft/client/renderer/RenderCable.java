package hydrocraft.client.renderer;

import hydrocraft.client.models.ModelCable;
import hydrocraft.tileentity.TileEntityCable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class RenderCable extends TileEntitySpecialRenderer{

	private ModelCable model = new ModelCable();

	public RenderCable(){
	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		renderModelAt((TileEntityCable) var1, var2, var4, var6, var8);
	}

	private void renderModelAt(TileEntityCable var1, double var2, double var4, double var6, float var8) {
		bindTextureByName("/mods/hydrocraft/textures/models/electricityCable.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + 1.5F, (float)var6 + 0.5F);
		GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		model.renderModel(0.0625F, var1.validDirections);
		GL11.glPopMatrix();
	}
}
