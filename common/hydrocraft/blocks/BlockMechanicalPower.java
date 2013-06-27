package hydrocraft.blocks;

import hydrocraft.MechanicalPowerProvider;
import hydrocraft.api.utils.Utils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class BlockMechanicalPower extends BlockContainer{

	public enum BlockType{
		Provider, Receiver, Transfer, Distributor;
	}
	
	private final BlockType blockType;
	
	public BlockMechanicalPower(int par1, Material par2Material, BlockType par3) {
		super(par1, par2Material);
		this.blockType = par3;
	}
	
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9){
		return par5;
    }
	
	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int par5) {
		MechanicalPowerProvider provider= (MechanicalPowerProvider) world.getBlockTileEntity(x, y, z);
		provider.direction = ForgeDirection.getOrientation(par5);
		Utils.printLine(par5);
	}
	
	public BlockType getBlockType(){
		return this.blockType;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return createNewProvider(world);
	}
	
	public abstract boolean doesBlockHaveRotation();
	
	public abstract MechanicalPowerProvider createNewProvider(World world);
}