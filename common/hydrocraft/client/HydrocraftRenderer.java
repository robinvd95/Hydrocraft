package hydrocraft.client;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public abstract class HydrocraftRenderer extends TileEntitySpecialRenderer{

	
	protected void bindTexture(String par1){
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(par1);
	}
	
	protected void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
