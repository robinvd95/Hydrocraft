package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.client.ClientProxy;
import hydrocraft.client.renderer.RenderInventory;
import hydrocraft.tileentity.TileEntityWaterPump;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWaterPump extends BlockContainer {

	public BlockWaterPump(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		return ClientProxy.BLOCK_RENDER_ID;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityWaterPump();
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = Block.stoneDoubleSlab.getBlockTextureFromSide(0);
    }
}