package hydrocraft.tileentity;

import hydrocraft.EnergyStorage;
import hydrocraft.IElectricStorage;
import hydrocraft.ISidedMachine;
import hydrocraft.api.utils.Utils;
import hydrocraft.items.ItemBattery;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityEnergyStorage extends TileEntity implements IElectricStorage, IInventory, ISidedMachine{

	public EnergyStorage storage = new EnergyStorage(256000);
	public ItemStack[] inventory = new ItemStack[2];
	
	private ForgeDirection[] inputSides = new ForgeDirection[]{ForgeDirection.UP};
	private ForgeDirection[] outputSides = new ForgeDirection[]{ForgeDirection.EAST, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.WEST};
	
	public TileEntityEnergyStorage(){
	}

	@Override
	public EnergyStorage getEnergyStorage() {
		return this.storage;
	}

	@Override
	public boolean canTransferTrough() {
		return false;
	}

	@Override
	public float drainEnergy(float par1) {
		// TODO Auto-generated method stub
		return 0;
	}

    public void updateEntity(){
    	if(this.inventory[0] != null && this.inventory[0].getItem() instanceof ItemBattery){
    		int batteryStorage = this.inventory[0].getMaxDamage() - this.inventory[0].getItemDamage();
    		if(batteryStorage > 100){
    			batteryStorage = 100;
    		}
    		float used = this.storage.addEnergy(batteryStorage, true);
    		this.inventory[0].setItemDamage(this.inventory[0].getItemDamage()+(int)used);
    	}
    	
    	if(this.inventory[1] != null && this.inventory[1].getItem() instanceof ItemBattery){
    		int batteryNeeds = this.inventory[1].getItemDamage();
    		if(batteryNeeds > 100){
    			batteryNeeds = 100;
    		}
    		float used= this.storage.drainEnergy(batteryNeeds, true);
    		this.inventory[1].setItemDamage(this.inventory[1].getItemDamage() - (int)used);
    	}
    }
	
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.inventory.length)
            {
                this.inventory[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.inventory.length; ++var3)
        {
            if (this.inventory[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.inventory[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }
    
    public int getElectricityScaled(int par1){
    	return (int) (this.storage.currentPower * par1 / this.storage.capacity);
    }
	
	//IInventory
	public int getSizeInventory()
    {
        return this.inventory.length;
    }

    public ItemStack getStackInSlot(int par1)
    {
        return this.inventory[par1];
    }

    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.inventory[par1] != null)
        {
            ItemStack var3;

            if (this.inventory[par1].stackSize <= par2)
            {
                var3 = this.inventory[par1];
                this.inventory[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.inventory[par1].splitStack(par2);

                if (this.inventory[par1].stackSize == 0)
                {
                    this.inventory[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.inventory[par1] != null)
        {
            ItemStack var2 = this.inventory[par1];
            this.inventory[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.inventory[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInvName()
    {
        return "container.grinder";
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	@Override
	public ForgeDirection[] getOutput() {
		return this.outputSides;
	}

	@Override
	public ForgeDirection[] getInput() {
		return this.inputSides;
	}

	@Override
	public float addEnergy(float f, ForgeDirection input) {
		return 0;
	}
}
