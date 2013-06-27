package hydrocraft.tileentity;

import hydrocraft.BaseHydrocraft;
import hydrocraft.EnergyStorage;
import hydrocraft.IElectricStorage;
import hydrocraft.api.IInsertable;
import hydrocraft.api.utils.Utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityFurnace extends TileEntity implements ISidedInventory, ITankContainer, IElectricStorage, IInsertable{

	public static int MAX_LIQUID = LiquidContainerRegistry.BUCKET_VOLUME * 16;
	public LiquidTank tank;

	public int targetSlot = -1;
	
	public EnergyStorage storage = new EnergyStorage(3200);

	public int burnTime;
	public int maxBurnTime = 1000;

	public int progress = 0;

	private static final int[] field_102010_d = new int[] {0};
	private static final int[] field_102011_e = new int[] {2, 1};
	private static final int[] field_102009_f = new int[] {1};

	private ItemStack[] inventory = new ItemStack[5];

	private String field_94130_e;
	
	private static HashMap<Integer, Integer[]> insertMap = new HashMap();

	static{
		
	}
	
	public TileEntityFurnace(){
		tank = new LiquidTank(MAX_LIQUID);
	}

	public int getSizeInventory(){
		return this.inventory.length;
	}

	public ItemStack getStackInSlot(int par1){
		return this.inventory[par1];
	}

	public ItemStack decrStackSize(int par1, int par2){
		if (this.inventory[par1] != null){
			ItemStack itemstack;

			if (this.inventory[par1].stackSize <= par2){
				itemstack = this.inventory[par1];
				this.inventory[par1] = null;
				return itemstack;
			}
			else{
				itemstack = this.inventory[par1].splitStack(par2);

				if (this.inventory[par1].stackSize == 0){
					this.inventory[par1] = null;
				}

				return itemstack;
			}
		}
		else{
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int par1){
		if (this.inventory[par1] != null){
			ItemStack itemstack = this.inventory[par1];
			this.inventory[par1] = null;
			return itemstack;
		}
		else{
			return null;
		}
	}

	public int getTargetSlot(){
		for(int i = 0; i < 3; i++){
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[i]);
			if(itemstack != null){
				return i;
			}
		}
		return -1;
	}

	public int getTargetResultSlot(ItemStack par1){
		for(int i = 3; i < 5; i++){
			if(inventory[i] == null){
				return i;
			}else{
				if(par1.itemID == inventory[i].itemID && par1.getItemDamage() == inventory[i].getItemDamage() && inventory[i].stackSize < inventory[i].getMaxStackSize()){
					return i;
				}
			}
		}
		return -1;
	}

	public int getTankAmount(){
		if(this.tank.getLiquid() == null){
			return 0;
		}else{
			return this.tank.getLiquid().amount;
		}
	}

	public void setInventorySlotContents(int par1, ItemStack par2ItemStack){
		this.inventory[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()){
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	public String getInvName(){
		return this.isInvNameLocalized() ? this.field_94130_e : "container.furnace";
	}

	public boolean isInvNameLocalized(){
		return this.field_94130_e != null && this.field_94130_e.length() > 0;
	}

	public void func_94129_a(String par1Str){
		this.field_94130_e = par1Str;
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound){
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		this.inventory = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i){
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inventory.length){
				this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		if (par1NBTTagCompound.hasKey("CustomName")){
			this.field_94130_e = par1NBTTagCompound.getString("CustomName");
		}
	}

	public int getScaledFluid(int i) {
		return this.tank.getLiquid() != null ? (int) (((float) this.tank.getLiquid().amount / (float) (MAX_LIQUID)) * i) : 0;
	}
	
	public int getElectricityScaled(int i){
		return (int) (this.storage.currentPower * i / this.storage.capacity);
	}

	public int getScaledProgress(int i){
		return this.progress * i / 100;
	}

	public int getScaledBurnTime(int par1){
		return this.burnTime * par1 / this.maxBurnTime;
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeToNBT(par1NBTTagCompound);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inventory.length; ++i){
			if (this.inventory[i] != null){
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

		if (this.isInvNameLocalized()){
			par1NBTTagCompound.setString("CustomName", this.field_94130_e);
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
	 * this more of a set than a get?*
	 */
	public int getInventoryStackLimit(){
		return 64;
	}

	public void updateEntity(){
		if(!this.worldObj.isRemote){
			//Utils.printLine(this.storage.currentPower);
			if(this.burnTime > 0){
				this.burnTime--;
			}

			boolean flag = false;

			int i = this.getTargetSlot();
			int j = 0;
			if(i != this.targetSlot){
				this.progress = 0;
			}
			this.targetSlot = i;

			if(this.targetSlot > -1){
				ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[this.targetSlot]);
				j = this.getTargetResultSlot(itemstack);
				if(j > -1){
					flag = true;
				}
			}

			if(this.tank.getLiquid() != null){
				if(this.tank.getLiquid().amount >= 1000 && this.burnTime < 1 && flag){
					this.ignite(1000);
				}
			}
			
			if(flag && this.burnTime > 0){
				this.progress++;

				if(this.progress > 100){
					this.smeltItem(this.targetSlot, j);
				}
			}else{
				this.progress = 0;
			}
		}
	}

	private void ignite(int par1) {
		this.burnTime = this.maxBurnTime = par1;
		this.tank.drain(1000, true);
	}

	public void smeltItem(int par1, int par2){
		this.progress = 0;
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[par1]);

		if (this.inventory[par2] == null){
			this.inventory[par2] = itemstack.copy();
		}
		else if (this.inventory[par2].isItemEqual(itemstack)){
			inventory[par2].stackSize += itemstack.stackSize;
		}

		--this.inventory[par1].stackSize;

		if (this.inventory[par1].stackSize <= 0){
			this.inventory[par1] = null;
		}
	}

	/*private boolean canSmelt(){
		if (this.inventory[0] == null){
			return false;
		}
		else{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
			if (itemstack == null) return false;
			if (this.inventory[2] == null) return true;
			if (!this.inventory[2].isItemEqual(itemstack)) return false;
			int result = inventory[2].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}*/

	/*public void smeltItem(){
		if (this.canSmelt()){
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);

			if (this.inventory[2] == null){
				this.inventory[2] = itemstack.copy();
			}
			else if (this.inventory[2].isItemEqual(itemstack)){
				inventory[2].stackSize += itemstack.stackSize;
			}

			--this.inventory[0].stackSize;

			if (this.inventory[0].stackSize <= 0){
				this.inventory[0] = null;
			}
		}
	}*/

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer){
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	public void openChest() {}

	public void closeChest() {}

	public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack){
		return false;
	}

	public int[] getAccessibleSlotsFromSide(int par1){
		return par1 == 0 ? field_102011_e : (par1 == 1 ? field_102010_d : field_102009_f);
	}

	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3){
		return this.isStackValidForSlot(par1, par2ItemStack);
	}

	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3){
		return par3 != 0 || par1 != 1 || par2ItemStack.itemID == Item.bucketEmpty.itemID;
	}

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill){
		return this.tank.fill(resource, doFill);
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return fill(ForgeDirection.UNKNOWN, resource, doFill);
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		return new ILiquidTank[]{tank};
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		return tank;
	}

	public LiquidStack getLiquidStack() {
		return this.tank.getLiquid();
	}

	@Override
	public EnergyStorage getEnergyStorage() {
		return this.storage;
	}

	@Override
	public float drainEnergy(float par1) {
		return 0;
	}

	@Override
	public float addEnergy(float par1, ForgeDirection input) {
		return this.storage.addEnergy(par1, true);
	}

	@Override
	public boolean canTransferTrough() {
		return false;
	}

	@Override
	public HashMap<Integer, Integer[]> getMap() {
		return BaseHydrocraft.furnaceInsertionMap;
	}

	@Override
	public void setSlotContent(int slotID, ItemStack itemStack) {
		this.inventory[slotID] = itemStack;
		Utils.printLine("Slot ID: "+slotID+", StackNr: "+itemStack.itemID+", Size: "+itemStack.stackSize);
	}

	@Override
	public ItemStack getSlotContent(int slotID) {
		return this.inventory[slotID];
	}
}
