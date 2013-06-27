package hydrocraft.bridge.buildcraft;

import hydrocraft.BaseHydrocraft;
import hydrocraft.IRenderInventory;
import hydrocraft.ISpecialInventoryRenderer;
import hydrocraft.api.IWhackable;
import hydrocraft.bridge.buildcraft.client.RenderEngine;
import hydrocraft.client.renderer.RenderInventory;

import java.util.Random;

import buildcraft.api.tools.IToolWrench;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMechanicalEngine extends BlockContainer implements IWhackable{

	protected BlockMechanicalEngine(int par1, Material par2Material) {
		super(par1, par2Material);
		setHardness(0.5F);
		setCreativeTab(BaseHydrocraft.hydroCraftTab);
		setUnlocalizedName("engineBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileHydrocraftEngine();

	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return HydrocraftBridgeBC.customBlockRenderID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = Block.stoneDoubleSlab.getBlockTextureFromSide(0);
	}

	/*@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (tile instanceof TileEngine) {
			return ForgeDirection.getOrientation(((TileEngine) tile).orientation).getOpposite() == side;
		}
		return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		TileEngine engine = ((TileEngine) world.getBlockTileEntity(x, y, z));

		if (engine != null) {
			engine.delete();
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}*/

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {

		TileHydrocraftEngine tile = (TileHydrocraftEngine) world.getBlockTileEntity(i, j, k);

		if (entityplayer.isSneaking())
			return false;

		Item equipped = entityplayer.getCurrentEquippedItem() != null ? entityplayer.getCurrentEquippedItem().getItem() : null;
		if (equipped instanceof IToolWrench && ((IToolWrench) equipped).canWrench(entityplayer, i, j, k)) {

			tile.switchOrientation();
			((IToolWrench) equipped).wrenchUsed(entityplayer, i, j, k);
			return true;

		}else{
			entityplayer.openGui(HydrocraftBridgeBC.instance, 0, world, i, j, k);
			return true;
		}
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int par5) {
		TileHydrocraftEngine tile = (TileHydrocraftEngine) world.getBlockTileEntity(x, y, z);
		tile.orientation = ForgeDirection.UP.ordinal();
		tile.switchOrientation();
	}

	@SuppressWarnings({ "all" })
	@Override
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		TileHydrocraftEngine tile = (TileHydrocraftEngine) world.getBlockTileEntity(i, j, k);

		if(!tile.isRunning){
			return;
		}

		float f = (float) i + 0.5F;
		float f1 = (float) j + 0.0F + (random.nextFloat() * 6F) / 16F;
		float f2 = (float) k + 0.5F;
		float f3 = 0.52F;
		float f4 = random.nextFloat() * 0.6F - 0.3F;

		world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		TileHydrocraftEngine tile = (TileHydrocraftEngine) world.getBlockTileEntity(i, j, k);
	}

	@Override
	public boolean whack(World world, int x, int y, int z) {
		TileHydrocraftEngine tile = (TileHydrocraftEngine) world.getBlockTileEntity(x, y, z);
		tile.switchOrientation();
		return true;

	}
}
