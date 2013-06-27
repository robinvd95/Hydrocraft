package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockOre extends Block{

	public enum OreType{
		COPPER("copperOre", 3f), LITHIUM("lithiumOre", 4f);
		
		public String iconName;
		public float hardness;
		
		private OreType(String par1, float par3){
			this.iconName = par1;
			this.hardness = par3;
		}
	}
	
	private final OreType oreType;
	
	public BlockOre(int par1, Material par2Material, OreType par3) {
		super(par1, par2Material);
		this.oreType = par3;
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
		this.setHardness(par3.hardness);
	}
	
	/*@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune){
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        int count = quantityDropped(metadata, fortune, world.rand);
        if(this.oreType == OreType.COPPER){
        	ret.add(new ItemStack(BaseHydrocraft.copperOreBlock));
        }else if(this.oreType == OreType.LITHIUM){
        	ret.add(new ItemStack(BaseHydrocraft.lithiumOreBlock));
        }
        return ret;
    }*/
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = par1IconRegister.registerIcon("hydrocraft:"+this.oreType.iconName);
    }

}
