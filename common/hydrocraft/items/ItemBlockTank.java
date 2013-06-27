package hydrocraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.Icon;

public class ItemBlockTank extends ItemBlock{

	private Icon[] icons = new Icon[2];
	
	public ItemBlockTank(int par1) {
		super(par1);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int meta){
		return meta;
	}
}