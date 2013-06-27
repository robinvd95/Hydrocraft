package hydrocraft.client.renderer;

import hydrocraft.GearBox;
import hydrocraft.blocks.BlockGearBox.GearBoxType;
import hydrocraft.client.ClientProxy;
import hydrocraft.client.IInventoryRenderer;
import hydrocraft.client.models.ModelGearBox;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderGearBox extends TileEntitySpecialRenderer implements IInventoryRenderer{

	private ModelGearBox model;
	private GearBoxType gearBoxType;

	public RenderGearBox(GearBoxType par1){
		model = new ModelGearBox();
		this.gearBoxType = par1;
	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		renderModelAt(((GearBox) var1).getDirections(), var2, var4, var6);
	}

	private void renderModelAt(List<ForgeDirection> var1, double var2, double var4, double var6) {
		GL11.glPushMatrix();
		this.bindTexture(ClientProxy.PATH_MODEL_TEXTURE+this.gearBoxType.textureName);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + 1.5F, (float)var6 + 0.5F);
		GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		int var9 = -1;
		int var10 = -1;
		model.renderModel(0.0625F, var1);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void bindTexture(String par1){
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(par1);
	}

	@Override
	public void renderInventory() {
		List<ForgeDirection> list = new ArrayList();
		list.add(ForgeDirection.UP);
		list.add(ForgeDirection.SOUTH);
		this.renderModelAt(list, -0.5f, -0.5f, -0.5f);
		list.clear();
	}
}
