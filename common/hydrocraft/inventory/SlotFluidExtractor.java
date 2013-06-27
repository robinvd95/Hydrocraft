package hydrocraft.inventory;

import hydrocraft.items.ItemLatexBucket;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotFluidExtractor extends Slot{

	public SlotFluidExtractor(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}

	public boolean isItemValid(ItemStack par1){
		if(par1.itemID == Item.bucketEmpty.itemID){
			return true;
		}
		Item var1 = par1.getItem();
		if(var1 instanceof ItemLatexBucket){
			return true;
		}
		return false;
	}	
}