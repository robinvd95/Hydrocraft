package hydrocraft.bridge.buildcraft;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotUpgrade extends Slot{

	public SlotUpgrade(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}
	
	public boolean isItemValid(ItemStack par1){
		return par1.getItem() instanceof ItemUpgrade && ((ItemUpgrade)par1.getItem()).getSlot() == this.slotNumber;
	}

}
