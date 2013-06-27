package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.MechanicalPowerProvider;
import hydrocraft.tileentity.TileEntitySpringBox;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpringBox extends BlockMechanicalPower{

	private Icon[] icons = new Icon[2];
	
	public BlockSpringBox(int par1, Material par2Material) {
		super(par1, par2Material, BlockType.Provider);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}

	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side){
		TileEntitySpringBox tile = (TileEntitySpringBox) world.getBlockTileEntity(x, y, z);
		if(side == tile.getDirection().ordinal()){
			return this.icons[1];
		}
		return this.icons[0];
	}
	
	@Override
	public boolean doesBlockHaveRotation() {
		return true;
	}

	@Override
	public MechanicalPowerProvider createNewProvider(World world) {
		return new TileEntitySpringBox();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register){
		this.blockIcon = this.icons[0] = register.registerIcon("hydrocraft:springBoxInput");
		this.icons[1] = register.registerIcon("hydrocraft:springBoxOutput");
	}

}
