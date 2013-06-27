package hydrocraft.client.renderer;

import hydrocraft.blocks.TileEntityPulley;
import hydrocraft.client.models.ModelPulley;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class RenderPulley extends TileEntitySpecialRenderer{

	private ModelPulley model;

	public RenderPulley(){
		model = new ModelPulley();
	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		renderModelAt((TileEntityPulley) var1, var2, var4, var6, var8);
	}

	private void renderModelAt(TileEntityPulley var1, double var2, double var4, double var6, float var8) {
		int i = var1.blockMetadata;
		int j = 0;
		if(i == 0){
			j = 0;
		}else if(i == 1){
			j = 270;
		}else if(i == 2){
			j = 180;
		}else if(i == 3){
			j = 90;
		}
		
		GL11.glPushMatrix();
		bindTextureByName("/mods/hydrocraft/textures/models/pulley.png");
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
