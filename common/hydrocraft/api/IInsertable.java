package hydrocraft.api;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public interface IInsertable {

	HashMap<Integer, Integer[]> getMap();
	
	void setSlotContent(int slotID, ItemStack itemStack);
	
	ItemStack getSlotContent(int slotID);
}
