package hydrocraft.items;

import hydrocraft.BaseHydrocraft;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class ItemIngots extends Item{

	private Icon[] icons = new Icon[3];
	private String[] names = new String[]{"copperIngot", "lithiumIngot", "zincIngot"};
	
	public ItemIngots(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1){
		return this.icons[par1];
	}

	public String getUnlocalizedName(ItemStack par1ItemStack){
		return "hydrocraft.ingotItems"+par1ItemStack.getItemDamage();
	}
	
	@Override
    public String getItemDisplayName(ItemStack itemStack) {
        switch(itemStack.getItemDamage()){
        default: return "Unnamed";
        
        case 0: return "Copper Ingot";
        case 1: return "Lithium Ingot";
        case 2: return "Zinc Ingot";
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List){
        for (int j = 0; j < 3; ++j){	
            par3List.add(new ItemStack(par1, 1, j));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister){
        for (int i = 0; i < icons.length; ++i){
            this.icons[i] = par1IconRegister.registerIcon("hydrocraft:"+this.names[i]);
        }
    }

}
