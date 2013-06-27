package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.MechanicalPowerProvider;
import hydrocraft.api.utils.Utils;
import hydrocraft.client.ClientProxy;
import hydrocraft.tileentity.TileEntityWaterWheel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWaterWheel extends BlockMechanicalPower{

	public BlockWaterWheel(int par1, Material par2Material) {
		super(par1, par2Material, BlockType.Provider);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
		this.setTickRandomly(true);
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
	public MechanicalPowerProvider createNewProvider(World world) {
		return new TileEntityWaterWheel();
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = Block.planks.getBlockTextureFromSide(0);
    }

	@Override
	public boolean doesBlockHaveRotation() {
		return true;
	}
}
