package hydrocraft.blocks;

import java.util.List;

import buildcraft.core.utils.Utils;
import buildcraft.transport.TileGenericPipe;
import buildcraft.transport.render.PipeWorldRenderer;
import hydrocraft.BaseHydrocraft;
import hydrocraft.client.ClientProxy;
import hydrocraft.client.models.ModelHydrocraft;
import hydrocraft.client.models.ModelPipe;
import hydrocraft.client.renderer.RenderInventory;
import hydrocraft.tileentity.TileEntityPipe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockPipe extends BlockContainer{

	public BlockPipe(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}

	public boolean isOpaqueCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}

	/*@SuppressWarnings("rawtypes")
	@Override
	public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
		float minPos = 0.25f;
		float maxPos = 0.75f;

		setBlockBounds(minPos, minPos, minPos, maxPos, maxPos, maxPos);
		super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);

		TileEntityPipe tile = (TileEntityPipe) world.getBlockTileEntity(i, j, k);

		if (tile.directions.contains(ForgeDirection.WEST)) {
			setBlockBounds(0.0F, minPos, minPos, maxPos, maxPos, maxPos);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
		}

		if (tile.directions.contains(ForgeDirection.EAST)) {
			setBlockBounds(minPos, minPos, minPos, 1.0F, maxPos, maxPos);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
		}

		if (tile.directions.contains(ForgeDirection.DOWN)) {
			setBlockBounds(minPos, 0.0F, minPos, maxPos, maxPos, maxPos);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
		}

		if (tile.directions.contains(ForgeDirection.UP)) {
			setBlockBounds(minPos, minPos, minPos, maxPos, 1.0F, maxPos);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
		}

		if (tile.directions.contains(ForgeDirection.NORTH)) {
			setBlockBounds(minPos, minPos, 0.0F, maxPos, maxPos, maxPos);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
		}

		if (tile.directions.contains(ForgeDirection.SOUTH)) {
			setBlockBounds(minPos, minPos, minPos, maxPos, maxPos, 1.0F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
		}

		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}*/

	/*@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {

		float minPos = 0.25f;
		float maxPos = 0.75f;

		float xMin = minPos, xMax = maxPos, yMin = minPos, yMax = maxPos, zMin = minPos, zMax = maxPos;
		TileEntityPipe tile = (TileEntityPipe) world.getBlockTileEntity(i, j, k);

		if (tile.directions.contains(ForgeDirection.WEST)) {
			xMin = 0.0F;
		}

		if (tile.directions.contains(ForgeDirection.EAST)) {
			xMax = 1.0F;
		}

		if (tile.directions.contains(ForgeDirection.DOWN)) {
			yMin = 0.0F;
		}

		if (tile.directions.contains(ForgeDirection.UP)) {
			yMax = 1.0F;
		}

		if (tile.directions.contains(ForgeDirection.NORTH)) {
			zMin = 0.0F;
		}

		if (tile.directions.contains(ForgeDirection.SOUTH)) {
			zMax = 1.0F;
		}

		return AxisAlignedBB.getBoundingBox((double) i + xMin, (double) j + yMin, (double) k + zMin, (double) i + xMax, (double) j + yMax, (double) k + zMax);
	}*/
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		TileEntityPipe tile = (TileEntityPipe) par1World.getBlockTileEntity(par2, par3, par4);
		tile.setValidDirections();
	}

	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		//TODO renderID
		return ClientProxy.PIPE_RENDER_ID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register){
		this.blockIcon = register.registerIcon("hydrocraft:waterPipe");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityPipe();
	}
}