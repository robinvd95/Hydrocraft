package hydrocraft;

import hydrocraft.items.ItemHydrocraft;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs{
	
	public CreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ItemHydrocraft.displayItem);
	}
}
