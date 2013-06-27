package hydrocraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockDisplay extends Block{

	private String name;
	
	public BlockDisplay(int par1, String par2) {
		super(par1, Material.wood);
		this.name = par2;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = par1IconRegister.registerIcon("hydrocraft:"+name);
	}

}
