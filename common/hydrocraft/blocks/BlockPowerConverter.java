package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.ElectricPower;
import hydrocraft.client.renderer.RenderInventory;
import hydrocraft.tileentity.TileEntityPowerConverter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPowerConverter extends BlockElectricPower{

	public BlockPowerConverter(int par1, Material par2Material) {
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
		//TODO renderID
		return -1;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = Block.stoneDoubleSlab.getBlockTextureFromSide(0);
	}

	@Override
	public ElectricPower getPower() {
		return new TileEntityPowerConverter();
	}

}
