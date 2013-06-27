package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.tileentity.TileEntityMagnet;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMagnet extends BlockContainer{

	public BlockMagnet(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityMagnet();
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = par1IconRegister.registerIcon("hydrocraft:electroMagnetBlock");
	}
}
