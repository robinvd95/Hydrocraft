package hydrocraft.blocks;

import java.util.List;

import hydrocraft.BaseHydrocraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStainedGlass extends Block{

	private String[] names = new String[]{"Black", "Blue", "Brown", "Cyan", "Green", "Grey", "LightBlue", "LightGrey", "LimeGreen", "Magenta", 
			"Orange", "Pink", "Purple", "Red", "White", "Yellow"};
	private Icon[] icons = new Icon[16];
	
	public BlockStainedGlass(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}
	
	public int damageDropped(int meta){
		return meta;
	}

	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tabs, List list){
		for(int i = 0; i < this.names.length; i++){
			list.add(new ItemStack(this.blockID, 1, i));
		}
	}
	
	@SideOnly(Side.CLIENT)
    public int getRenderBlockPass(){
        return 1;
    }
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	public Icon getIcon(int side, int meta){
		return this.icons[meta];
	}

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
        return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
    }
    
    @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register){
    	for(int i = 0; i < this.names.length; i++){
    		this.icons[i] = register.registerIcon("hydrocraft:stainedGlass"+this.names[i]);
    	}
	}
}
