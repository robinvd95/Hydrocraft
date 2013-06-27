package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockHydrocraft extends Block{

	final String name;
	
	public BlockHydrocraft(int par1, Material par2Material, String par3) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
		this.name = par3;
		this.setUnlocalizedName(par3);
	}

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = par1IconRegister.registerIcon("hydrocraft:"+name);
    }
	
}
