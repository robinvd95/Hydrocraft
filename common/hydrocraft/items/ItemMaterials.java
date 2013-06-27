package hydrocraft.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hydrocraft.BaseHydrocraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;

public class ItemMaterials extends Item{

	private Icon[] icons = new Icon[4];
	private String[] names = new String[]{"rubber", "zincPlate", "spring", "ironPlate"};
	
	public ItemMaterials(int par1) {
		super(par1);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1){
		return this.icons[par1];
	}

	public String getUnlocalizedName(ItemStack par1ItemStack){
		return "hydrocraft.materials"+par1ItemStack.getItemDamage();
	}
	
	@Override
    public String getItemDisplayName(ItemStack itemStack) {
        switch(itemStack.getItemDamage()){
        default: return "Unnamed";
        
        case 0: return "Rubber Sheet";
        case 1: return "Zinc Plate";
        case 2: return "Spring";
        case 3: return "Iron Plate";
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List){
        for (int j = 0; j < icons.length; ++j){	
            par3List.add(new ItemStack(par1, 1, j));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register){
    	for(int i = 0; i < icons.length; i++){
    		this.icons[i] = register.registerIcon("hydrocraft:"+this.names[i]);
    	}
    }

}
