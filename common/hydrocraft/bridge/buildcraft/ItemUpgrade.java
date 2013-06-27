package hydrocraft.bridge.buildcraft;

import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hydrocraft.items.ItemHydrocraft;

public class ItemUpgrade extends ItemHydrocraft{

	private final int tier;
	private final int slot;
	
	public ItemUpgrade(int par1, String par2, int par3, int par4) {
		super(par1, par2);
		this.slot = par3;
		this.tier = par4;
	}
	
	public int getTier(){
		return this.tier;
	}
	
	public int getSlot(){
		return this.slot;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister){
		this.itemIcon = par1IconRegister.registerIcon("hydrocraft/buildcraft:"+this.iconName);
	}

}
