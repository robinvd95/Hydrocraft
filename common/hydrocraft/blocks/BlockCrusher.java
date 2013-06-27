package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.api.utils.Utils;
import hydrocraft.client.ClientProxy;
import hydrocraft.tileentity.TileEntityCrusher;
import hydrocraft.tileentity.TileEntityTank;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrusher extends BlockContainer {

	public BlockCrusher(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
		this.setHardness(2f);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
		if(player.isSneaking())
			return false;

		if(world.getBlockTileEntity(x, y, z) != null){
			player.openGui(BaseHydrocraft.instance, 7, world, x, y, z);
			return true;
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register){
		this.blockIcon = register.registerIcon("hydrocraft:crusher");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCrusher();
	}
}
