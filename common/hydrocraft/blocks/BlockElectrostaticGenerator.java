package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.client.models.ModelElectrostaticGenerator;
import hydrocraft.client.models.ModelHydrocraft;
import hydrocraft.client.renderer.RenderInventory;
import hydrocraft.tileentity.TileEntityElectrostaticGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockElectrostaticGenerator extends BlockContainer{

	public BlockElectrostaticGenerator(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		TileEntityElectrostaticGenerator var1 = (TileEntityElectrostaticGenerator) par1World.getBlockTileEntity(par2, par3, par4);
		if(var1 != null){
			par5EntityPlayer.openGui(BaseHydrocraft.instance, 0, par1World, par2, par3, par4);
			return true;
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = Block.cobblestone.getBlockTextureFromSide(0);
	}
	
	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		//TODO renderID
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityElectrostaticGenerator();
	}
}
