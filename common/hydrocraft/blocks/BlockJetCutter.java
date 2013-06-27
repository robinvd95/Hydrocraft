package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.client.renderer.RenderInventory;
import hydrocraft.tileentity.TileEntityJetCutter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockJetCutter extends BlockContainer{

	public BlockJetCutter(int par1, Material par2Material) {
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
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityJetCutter();
	}

}
