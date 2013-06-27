package hydrocraft.bridge.buildcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IInventoryRenderer {
	
	@SideOnly(Side.CLIENT)
	public void renderInventory();

}
