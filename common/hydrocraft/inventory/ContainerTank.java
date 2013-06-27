package hydrocraft.inventory;

import hydrocraft.tileentity.TileEntityTank;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerTank extends Container{

	private final TileEntityTank inventory;
	private int lastTankAmount = -1;
	private int lastTankLiquid = -1;
	private int lastTankCapacity = -1;

	public ContainerTank(InventoryPlayer par1, TileEntityTank par2){
		this.inventory = par2;

		this.addSlotToContainer(new Slot(par2, 0, 53, 16));
		/*this.addSlotToContainer(new Slot(par2, 1, 53, 34));
		this.addSlotToContainer(new Slot(par2, 2, 53, 52));
		this.addSlotToContainer(new SlotResult(par2, 3, 112, 22));
		this.addSlotToContainer(new SlotResult(par2, 4, 112, 46));*/

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


	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int var1 = 0; var1 < this.crafters.size(); ++var1){
			ICrafting var2 = (ICrafting)this.crafters.get(var1);
			if(this.inventory.getLiquidTank().containsValidLiquid()){
				if (this.lastTankAmount != this.inventory.getLiquidTank().getLiquid().amount){
					var2.sendProgressBarUpdate(this, 0, this.inventory.getLiquidTank().getLiquid().amount);
				}

				if (this.lastTankLiquid != this.inventory.getLiquidTank().getLiquid().itemID){
					var2.sendProgressBarUpdate(this, 1, this.inventory.getLiquidTank().getLiquid().itemID);
				}
				this.lastTankAmount = this.inventory.getLiquidTank().getLiquid().amount;
				this.lastTankLiquid = this.inventory.getLiquidTank().getLiquid().itemID;
			}

			if (this.lastTankCapacity != this.inventory.getLiquidTank().getCapacity()){
				var2.sendProgressBarUpdate(this, 2, this.inventory.getLiquidTank().getCapacity());
			}
		}

		this.lastTankCapacity = this.inventory.getLiquidTank().getCapacity();

	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if(this.inventory.getLiquidTank() == null){
			this.inventory.createDummyTank();
		}

		if(this.inventory.getLiquidTank().containsValidLiquid()){
			int liquidID = this.inventory.getLiquidTank().getLiquid().itemID;
			int liquidAmount = this.inventory.getLiquidTank().getLiquid().amount;
			switch(par1){
			case 0:
				this.inventory.getLiquidTank().setLiquid(new LiquidStack(this.inventory.getLiquidTank().getLiquid().itemID, par2));
				break;

			case 1:
				this.inventory.getLiquidTank().setLiquid(new LiquidStack(par2, liquidAmount));
				break;
			}
		}else if(par1 == 1){
			this.inventory.getLiquidTank().setLiquid(new LiquidStack(par2, 0));
		}

		if(par1 == 2){
			this.inventory.getLiquidTank().setCapacity(par2);
		}
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.inventory.isUseableByPlayer(par1EntityPlayer);
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2){
		return null;
	}
}
