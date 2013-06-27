package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.tileentity.TileEntityExplosive;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockExplosive extends BlockContainer{

	public BlockExplosive(int par1) {
		super(par1, Material.cloth);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	public int getRenderType(){
		return -1;
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		par1World.setBlockToAir(par2, par3, par4);
		par1World.createExplosion(null, par2, par3, par4, 35f, true);
		return true;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityExplosive();
	}
}