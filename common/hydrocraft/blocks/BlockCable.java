package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.client.ClientProxy;
import hydrocraft.client.models.ModelCable;
import hydrocraft.client.models.ModelHydrocraft;
import hydrocraft.client.renderer.RenderInventory;
import hydrocraft.tileentity.TileEntityCable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCable extends BlockContainer{

	public BlockCable(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
		this.setBlockBounds(0.3750f, 0.3750f, 0.3750f, 0.6250f, 0.6250f, 0.6250f);
	}

	public boolean isOpaqueCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		//TODO renderID
		return -1;
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4){
		return this.getCollision(par1World, par2, par3, par4);
	}

	private AxisAlignedBB getCollision(World par1World, int par2, int par3, int par4){
		double xMin = 0.3750;
		double yMin = 0.3750;
		double zMin = 0.3750;
		double xMax = 0.6250;
		double yMax = 0.6250;
		double zMax = 0.6250;
		TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
		if(tile instanceof TileEntityCable){
			TileEntityCable var1 = (TileEntityCable) tile;
			if(var1 != null){
				if(var1.validDirections.contains(ForgeDirection.DOWN)){
					yMin = 0;
				}
				if(var1.validDirections.contains(ForgeDirection.UP)){
					yMax = 1;
				}
				if(var1.validDirections.contains(ForgeDirection.NORTH)){
					zMin = 0;
				}
				if(var1.validDirections.contains(ForgeDirection.SOUTH)){
					zMax = 1;
				}
				if(var1.validDirections.contains(ForgeDirection.EAST)){
					xMax = 1;
				}
				if(var1.validDirections.contains(ForgeDirection.WEST)){
					xMin = 0;
				}
			}
		}
		return AxisAlignedBB.getAABBPool().getAABB((double)par2 + xMin, (double)par3 + yMin, (double)par4 + zMin, (double)par2 + xMax, (double)par3 + yMax, (double)par4 + zMax);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4){
		return this.getCollision(par1World, par2, par3, par4);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = Block.cobblestone.getBlockTextureFromSide(0);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCable();
	}

}
