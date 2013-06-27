package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.MechanicalPowerProvider;
import hydrocraft.api.utils.Utils;
import hydrocraft.client.ClientProxy;
import hydrocraft.client.renderer.RenderInventory;
import hydrocraft.tileentity.TileEntityStock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockStock extends BlockMechanicalPower{

	public BlockStock(int par1, Material par2Material) {
		super(par1, par2Material, BlockType.Transfer);
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
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = Block.planks.getBlockTextureFromSide(0);
    }

	@Override
	public boolean doesBlockHaveRotation() {
		return true;
	}

	@Override
	public MechanicalPowerProvider createNewProvider(World world) {
		return new TileEntityStock();
	}
}
