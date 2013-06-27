package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;

import java.util.Random;

import buildcraft.BuildCraftBuilders;
import buildcraft.api.core.Position;
import buildcraft.api.tools.IToolWrench;
import buildcraft.core.GuiIds;
import buildcraft.core.proxy.CoreProxy;

import hydrocraft.api.IWhackable;
import hydrocraft.api.utils.Utils;
import hydrocraft.tileentity.TileEntityFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFurnace extends BlockContainer implements IWhackable{

	@SideOnly(Side.CLIENT)
	private Icon blockTexture;
	@SideOnly(Side.CLIENT)
	private Icon blockTextureFront;

	public BlockFurnace(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}

	@Override
	public Icon getIcon(int i, int j) {
		if (j == 0 && i == 3)
			return blockTextureFront;

		if (i == j)
			return blockTextureFront;

		return this.blockTexture;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack stack) {
		super.onBlockPlacedBy(world, i, j, k, entityliving, stack);
		ForgeDirection orientation = Utils.get2dOrientation(new Position(entityliving.posX, entityliving.posY, entityliving.posZ), new Position(i, j, k));

		world.setBlockMetadataWithNotify(i, j, k, orientation.getOpposite().ordinal(),1);
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {

		if (entityplayer.isSneaking())
			return false;

		if (!world.isRemote){
			entityplayer.openGui(BaseHydrocraft.instance, 4, world, i, j, k);
		}
		return true;

	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		this.blockTexture = Block.blockIron.getBlockTextureFromSide(0);
		this.blockTextureFront = par1IconRegister.registerIcon("hydrocraft:furnaceFront");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityFurnace();
	}

	@Override
	public boolean whack(World world, int x, int y, int z) {
		return false;
	}

}
