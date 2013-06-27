package hydrocraft.blocks;

import java.util.ArrayList;

import buildcraft.BuildCraftCore;
import buildcraft.core.utils.Utils;
import buildcraft.factory.TileTank;
import hydrocraft.BaseHydrocraft;
import hydrocraft.client.models.ModelHydrocraft;
import hydrocraft.client.models.ModelWaterTank;
import hydrocraft.client.renderer.RenderInventory;
import hydrocraft.tileentity.TileEntityWaterTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class BlockWaterTank extends BlockContainer{

	private Icon textureTop;
	private Icon textureSide;
	private Icon textureBottomSide;
	
	public BlockWaterTank(int par1, Material par2) {
		super(par1, par2);
		setBlockBounds(0.125F, 0F, 0.125F, 0.875F, 1F, 0.875F);
		setHardness(0.5F);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		switch(par1){
		case 0:
		case 1:
			return textureTop;
		default:
			return textureBottomSide;
		}
	}
	
	@SuppressWarnings({ "all" })
	public Icon getBlockTexture(IBlockAccess par1, int i, int j, int k, int l) {
		switch (l) {
		case 0:
		case 1:
			return textureTop;
		default:
			if (par1.getBlockId(i, j - 1, k) == blockID)
				return textureSide;
			else
				return textureBottomSide;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
        textureSide = par1IconRegister.registerIcon("hydrocraft:tankSide");
        textureTop = par1IconRegister.registerIcon("hydrocraft:tankTop");
        textureBottomSide = par1IconRegister.registerIcon("hydrocraft:tankBottomSide");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityWaterTank();
	}
}
