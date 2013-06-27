package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.api.utils.Utils;
import hydrocraft.client.ClientProxy;
import hydrocraft.tileentity.TileEntityExtractor;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockExtractorPipe extends BlockContainer {

	public BlockExtractorPipe(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}
	
	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		return ClientProxy.BLOCK_RENDER_ID;
	}
	
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9){
		return par5;
    }
	
	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int par5) {
		TileEntityExtractor provider= (TileEntityExtractor) world.getBlockTileEntity(x, y, z);
		provider.setOrientation(ForgeDirection.getOrientation(par5));
		world.markBlockForUpdate(x, y, z);
		Utils.printLine(par5);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityExtractor();
	}

}
