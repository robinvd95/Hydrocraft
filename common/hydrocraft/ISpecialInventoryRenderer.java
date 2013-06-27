package hydrocraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface ISpecialInventoryRenderer {

	@SideOnly(Side.CLIENT)
	IRenderInventory getRenderer();
}
