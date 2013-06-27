package hydrocraft.inventory;

import hydrocraft.tileentity.TileEntityEnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerElectricStorage extends Container{

	private TileEntityEnergyStorage inventory;
	
	public ContainerElectricStorage(InventoryPlayer par1, TileEntityEnergyStorage par2){
		this.inventory = par2;

		this.addSlotToContainer(new Slot(par2, 0, 103, 24));
		this.addSlotToContainer(new Slot(par2, 1, 103, 47));
		
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

	/*public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (int var1 = 0; var1 < this.crafters.size(); ++var1)
		{
			ICrafting var2 = (ICrafting)this.crafters.get(var1);

			if (this.lastProgressTime != this.inventory.progressTime)
			{
				var2.sendProgressBarUpdate(this, 0, this.inventory.progressTime);
			}

			if (this.lastcharge != this.inventory.charge)
			{
				var2.sendProgressBarUpdate(this, 1, this.inventory.charge);
			}
		}

		this.lastcharge = this.inventory.charge;
		this.lastProgressTime = this.inventory.progressTime;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0){
			this.inventory.progressTime = par2;
		}

		if (par1 == 1){
			this.inventory.charge = par2;
		}
	}*/

	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.inventory.isUseableByPlayer(par1EntityPlayer);
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		return null;
	}

}
