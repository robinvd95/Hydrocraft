package hydrocraft.tileentity;

import hydrocraft.BaseHydrocraft;
import hydrocraft.FluidRecipe;
import hydrocraft.api.IMechanicMachine;

import java.util.Random;


import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityFluidExtractor extends TileEntity implements IMechanicMachine, IInventory{

	public int progressTime;
	private final int maxProgressTime = 400;
	
	public static int MAX_LIQUID = 64;
	
	public LiquidStack liquid;

	public ItemStack[] inventory = new ItemStack[2];

	@Override
	public void runMachine(int par1) {
		if(this.inventory[0] != null){
			FluidRecipe var1 = FluidRecipe.getMatchingRecipe(this.inventory[0].itemID);
			if(this.canExtractLiquid(var1)){
				this.progressTime+= par1;
				System.out.println(par1);
				if(this.progressTime >= this.maxProgressTime){
					this.progressTime = 0;
					Random rand = new Random();
					int var2 = rand.nextInt(100);
					if(var2 >= var1.getPercentage()){
						this.fillTank(var1);
					}
					this.inventory[0].stackSize--;
					if(this.inventory[0].stackSize < 1){
						this.inventory[0] = null;
					}
				}
			}else{
				this.progressTime = 0;
			}
		}
	}
	
	private void fillTank(FluidRecipe par1) {
		if(this.liquid == null){
			this.liquid = new LiquidStack(par1.getResult().itemID, par1.getAmount());
		}else{
			this.liquid.amount = this.liquid.amount+par1.getAmount();
		}
	}

	private boolean canExtractLiquid(FluidRecipe par1){
		if(this.liquid == null) return true;
		if(this.liquid.amount >= this.MAX_LIQUID) return false;
		if(this.liquid.itemID != par1.getResult().itemID) return false;
		return true;
	}

	@Override
	public int getRequiredPower() {
		return 0;
	}

	@Override
	public ForgeDirection[] mechanicInput() {
		return new ForgeDirection[]{ForgeDirection.UP};
	}

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

		this.progressTime = par1NBTTagCompound.getShort("progress");
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("progress", (short)this.progressTime);
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

	public int getInventoryStackLimit()
	{
		return 64;
	}

	@SideOnly(Side.CLIENT)

	public int getProgressScaled(int par1){
		return this.progressTime * par1 / this.maxProgressTime;
	}
	
	public int getFluidScaled(int par1){
		if(liquid == null){
			return 0;
		}
		return this.liquid.amount * par1 / MAX_LIQUID;
	}

	public void updateEntity(){}

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

}
