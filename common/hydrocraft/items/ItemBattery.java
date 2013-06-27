package hydrocraft.items;

import java.util.List;

import hydrocraft.BaseHydrocraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBattery extends Item{

	public enum BatteryType{
		LITHIUM(64000);
		
		public final int capacity;
		
		private BatteryType(int par1){
			this.capacity = par1;
		}
	}
	
	public final BatteryType type;
	
	private Icon[] icon = new Icon[6];
	
	public ItemBattery(int par1, BatteryType par2) {
		super(par1);
		this.type = par2;
		this.setMaxDamage(par2.capacity);
		this.setMaxStackSize(1);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List){
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, this.type.capacity));
    }
	
	@Override
    public String getItemDisplayName(ItemStack itemStack) {

        return EnumChatFormatting.BOLD + "Battery";
    }
	
	public Icon getIconFromDamage(int par1){
		int var1 = par1 * 6 / this.type.capacity;
		if(var1 > 5){
			var1 = 5;
		}
        return this.icon[var1];
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister){
		for(int i = 0; i < 6; i++){
			this.icon[i] = par1IconRegister.registerIcon("hydrocraft:battery"+i);
		}
    }

}
