package hydrocraft.bridge.buildcraft;

import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hydrocraft.items.ItemHydrocraft;

public class ItemBuildcraftBridge extends ItemHydrocraft{

	public ItemBuildcraftBridge(int par1, String par2) {
		super(par1, par2);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister){
		this.itemIcon = par1IconRegister.registerIcon("hydrocraft/buildcraft:"+this.iconName);
	}
}
