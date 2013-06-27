package hydrocraft.inventory;

import hydrocraft.tileentity.TileEntityFurnace;
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

public class ContainerFurnace extends Container{

	private final TileEntityFurnace inventory;
	private int lastTankAmount = 0;
	private int lastProgress = 0;
	private int lastSlot = -1;
	private int lastBurnTime = 0;
	private int lastMaxBurnTime = 0;

	public ContainerFurnace(InventoryPlayer par1, TileEntityFurnace par2){
		this.inventory = par2;

		this.addSlotToContainer(new Slot(par2, 0, 53, 16));
		this.addSlotToContainer(new Slot(par2, 1, 53, 34));
		this.addSlotToContainer(new Slot(par2, 2, 53, 52));
		this.addSlotToContainer(new SlotResult(par2, 3, 112, 22));
		this.addSlotToContainer(new SlotResult(par2, 4, 112, 46));

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

			if (this.lastTankAmount != this.inventory.getTankAmount()){
				var2.sendProgressBarUpdate(this, 0, this.inventory.getTankAmount());
			}

			if(this.lastProgress != this.inventory.progress){
				var2.sendProgressBarUpdate(this, 1, this.inventory.progress);
			}

			if(this.lastSlot != this.inventory.targetSlot){
				var2.sendProgressBarUpdate(this, 2, this.inventory.targetSlot);
			}
			
			if(this.lastBurnTime != this.inventory.burnTime){
				var2.sendProgressBarUpdate(this, 3, this.inventory.burnTime);
			}
			
			if(this.lastMaxBurnTime != this.inventory.maxBurnTime){
				var2.sendProgressBarUpdate(this, 4, this.inventory.maxBurnTime);
			}
		}

		this.lastTankAmount = this.inventory.getTankAmount();
		this.lastProgress = this.inventory.progress;
		this.lastSlot = this.inventory.targetSlot;
		this.lastBurnTime = this.inventory.burnTime;
		this.lastMaxBurnTime = this.inventory.maxBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if(par1 == 0){
			this.inventory.tank.setLiquid(new LiquidStack(Block.waterStill, par2));
		}else if(par1 == 1){
			this.inventory.progress = par2;
		}else if(par1 == 2){
			this.inventory.targetSlot = par2;
		}else if(par1 == 3){
			this.inventory.burnTime = par2;
		}else if(par1 == 4){
			this.inventory.maxBurnTime = par2;
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
