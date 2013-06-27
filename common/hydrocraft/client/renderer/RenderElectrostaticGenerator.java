package hydrocraft.client.renderer;

import hydrocraft.client.models.ModelElectrostaticGenerator;
import hydrocraft.tileentity.TileEntityElectrostaticGenerator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class RenderElectrostaticGenerator extends TileEntitySpecialRenderer{

	private ModelElectrostaticGenerator model;

	public RenderElectrostaticGenerator(){
		model = new ModelElectrostaticGenerator();
	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		renderModelAt((TileEntityElectrostaticGenerator) var1, var2, var4, var6, var8);
	}

	private void renderModelAt(TileEntityElectrostaticGenerator var1, double var2, double var4, double var6, float var8) {
		GL11.glPushMatrix();
		bindTextureByName("/mods/hydrocraft/textures/models/electrostaticGenerator.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + 1.5F, (float)var6 + 0.5F);
		GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		int var9 = -1;
		int var10 = -1;
		model.renderModel(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
