package hydrocraft.items;

import hydrocraft.BaseHydrocraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLatexBucket extends Item {

	private Icon[] icon = new Icon[4];
	
	public ItemLatexBucket(int par1) {
		super(par1);
		this.setMaxDamage(16);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}
	
	public Icon getIconFromDamage(int par1){
		int var1 = par1/4;
        return this.icon[var1];
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerIcons(IconRegister par1IconRegister){
		for(int i = 0; i < 4; i++){
			icon[i] = par1IconRegister.registerIcon("hydrocraft:latexBucket"+i);
		}
    }
}
