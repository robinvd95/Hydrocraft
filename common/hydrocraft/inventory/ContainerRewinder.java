package hydrocraft.inventory;

import hydrocraft.tileentity.TileEntityRewinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerRewinder extends Container {

	private final TileEntityRewinder inventory;

	public ContainerRewinder(InventoryPlayer par1, TileEntityRewinder par2){
		this.inventory = par2;

		this.addSlotToContainer(new Slot(par2, 0, 80, 35));
		
		int var3;
		for (var3 = 0; var3 < 3; ++var3){
			for (int var4 = 0; var4 < 9; ++var4){
				this.addSlotToContainer(new Slot(par1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3){
			this.addSlotToContainer(new Slot(par1, var3, 8 + var3 * 18, 142));
		}
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer){
		return this.inventory.isUseableByPlayer(par1EntityPlayer);
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2){
		return null;
	}

}