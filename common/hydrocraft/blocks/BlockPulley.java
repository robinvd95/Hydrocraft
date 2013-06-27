package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.client.ClientProxy;
import hydrocraft.client.models.ModelHydrocraft;
import hydrocraft.client.models.ModelPulley;
import hydrocraft.client.renderer.RenderInventory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPulley extends BlockContainer{

	public BlockPulley(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityPulley();
	}
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving){
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 0);
    }
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1){
		this.blockIcon = Block.stoneDoubleSlab.getBlockTextureFromSide(0);
	}

	public boolean renderAsNormalBlock(){
		return false;
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		//TODO renderID
		return -1;
	}

}
