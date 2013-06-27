package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOres extends Block{

	private Icon[] icons = new Icon[3];

	public BlockOres(int par1) {
		super(par1, Material.iron);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
		this.setHardness(3.0f);
	}

	public float getBlockHardness(World world, int x, int y, int z){
		int meta = world.getBlockMetadata(x, y, z);
		switch(meta){
		
		case 0: return 3f;
		case 1: return 5f;
		case 2: return 4f;
		
		default: return 2f;
		}
	}

	public int damageDropped(int meta){
		return meta;
	}

	public Icon getIcon(int side, int meta){
		return this.icons[meta];
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tabs, List list){
		list.add(new ItemStack(this.blockID, 1, 0));
		list.add(new ItemStack(this.blockID, 1, 1));
		list.add(new ItemStack(this.blockID, 1, 2));
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register){
		this.icons[0] = register.registerIcon("hydrocraft:copperOre");
		this.icons[1] = register.registerIcon("hydrocraft:lithiumOre");
		this.icons[2] = register.registerIcon("hydrocraft:zincOre");
	}

}
