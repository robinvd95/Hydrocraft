package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChain extends Block{

	public BlockChain(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setHardness(2.0f);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}
	
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	public int getRenderType(){
		return 1;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1){
		this.blockIcon = par1.registerIcon("hydrocraft:chain");
	}

}
