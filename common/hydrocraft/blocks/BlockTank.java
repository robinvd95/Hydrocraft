package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.api.IWhackable;
import hydrocraft.api.utils.Utils;
import hydrocraft.tileentity.TileEntityTank;
import hydrocraft.tileentity.TileEntityTankValve;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockPoweredOre;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTank extends BlockContainer implements IWhackable{

	private Icon[] icons = new Icon[6];

	private Icon connectedGlass;

	public BlockTank(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}

	public float getBlockHardness(World world, int x, int y, int z){
		return 2f;
	}

	public int damageDropped(int meta){
		return meta;
	}

	public boolean isOpaqueCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
	}

	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side){
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 2 || meta == 0){
			return false;
		}
		return true;
	}

	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side){
		int meta = blockAccess.getBlockMetadata(x, y, z);
		if(meta == 2){
			TileEntityTank tileEntity = (TileEntityTank) blockAccess.getBlockTileEntity(x, y, z);
			if(tileEntity.isPartOfMultiblock()){
				if(side < 2){
					return this.icons[0];
				}
				return this.connectedGlass;
			}else{
				return this.icons[2];
			}
		}
		return icons[meta];
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
		if(player.isSneaking())
			return false;

		TileEntityTank tank = (TileEntityTank)world.getBlockTileEntity(x, y, z);

		if(tank != null){
			if(tank.getCore() != null){
				TileEntityTank core = tank.getCore();
				Utils.printLine("sending open gui to core");
				return core.getBlockType().onBlockActivated(world, core.xCoord, core.yCoord, core.zCoord, player, par6, par7, par8, par9);
			}else if(tank.getIsValid()){
				Utils.printLine("open GUI");
				player.openGui(BaseHydrocraft.instance, 6, world, x, y, z);
				return true;
			}

		}

		return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6){
		Utils.printLine(world.isRemote);
		TileEntityTank tileEntity = (TileEntityTank)world.getBlockTileEntity(x, y, z);

		if(tileEntity != null && tileEntity.getIsValid()){
			tileEntity.invalidateMultiblock();
		}

		if(tileEntity != null && tileEntity.getCore() != null)
			tileEntity.getCore().invalidateMultiblock();

		super.breakBlock(world, x, y, z, par5, par6);
	}

	public Icon getIcon(int side, int meta){
		if(meta == 3){
			if(side < 2){
				return this.icons[1];
			}
			return this.icons[3];
		}else{
			return this.icons[meta];
		}
	}

	public TileEntity createTileEntity(World world, int metadata){
		switch(metadata){
		case 4: return new TileEntityTankValve();

		default: return this.createNewTileEntity(world);
		}

	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTank();
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tabs, List list){
		list.add(new ItemStack(this.blockID, 1, 1));
		list.add(new ItemStack(this.blockID, 1, 2));
		list.add(new ItemStack(this.blockID, 1, 3));
		list.add(new ItemStack(this.blockID, 1, 4));
		list.add(new ItemStack(this.blockID, 1, 5));
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register){
		this.icons[0] = register.registerIcon("hydrocraft:empty");
		this.icons[1] = register.registerIcon("hydrocraft:tankWall");
		this.icons[2] = register.registerIcon("hydrocraft:tankGlass");
		this.icons[3] = register.registerIcon("hydrocraft:tankGauge");
		this.icons[4] = register.registerIcon("hydrocraft:tankValve");
		this.icons[5] = register.registerIcon("hydrocraft:tankHeater");
		this.connectedGlass = register.registerIcon("hydrocraft:tankGlassConnected");


	}

	@Override
	public boolean whack(World world, int x, int y, int z) {
		TileEntityTank tileEntity = (TileEntityTank) world.getBlockTileEntity(x, y, z);
		boolean used = false;
		if(tileEntity != null){
			if(!tileEntity.getIsValid()){
				used = tileEntity.checkIfProperlyFormed();
				if(used) {
					tileEntity.createTank();
				}
			}
		}
		return used;
	}

}
