package hydrocraft.tileentity;

import hydrocraft.api.recipes.CrusherRecipes;
import hydrocraft.api.recipes.CrusherRecipes.CrusherRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCrusher extends TileEntity implements IInventory{

	public ItemStack[] inventory = new ItemStack[5];
	private ItemStack[] craftInventory = new ItemStack[4];

	private boolean toggleState = false;

	private int progress = 0;

	public TileEntityCrusher(){

	}

	public void updateEntity(){
		CrusherRecipe recipe = this.getCurrentRecipe();

		if(recipe != null){
			if(canCraft(recipe.getResult())){
				this.progress ++;
			}else{
				this.progress = 0;
			}
		}else{
			this.progress = 0;
		}

		if(this.progress >= 150){
			this.createItem();
		}

		for(int i = 0; i < 4; i++){
			this.craftInventory[i] = this.inventory[i];
		}
	}

	private void createItem(){
		this.progress = 0;

		CrusherRecipe recipe = this.getCurrentRecipe();

		if (recipe != null && this.canCraft(recipe.getResult()))
		{

			if (this.inventory[4] == null)
			{
				this.inventory[4] = recipe.getResult().copy();
			}
			else if (this.inventory[4].isItemEqual(recipe.getResult()))
			{
				this.inventory[4].stackSize += recipe.getResult().stackSize;
			}


			for(int i = 0; i < 4; i++){
				if(this.inventory[i] != null){
					--this.inventory[i].stackSize;

					if (this.inventory[i].stackSize <= 0)
					{
						this.inventory[i] = null;
					}
				}
			}
		}
	}

	private CrusherRecipe getCurrentRecipe(){
		return CrusherRecipes.compareRecipe(this.craftInventory);
	}

	private boolean canCraft(ItemStack itemstack){
		if (this.inventory[4] == null) return true;
		if (!this.inventory[4].isItemEqual(itemstack)) return false;
		int result = inventory[4].stackSize + itemstack.stackSize;
		return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
	}

	public boolean isInventoryChanged(){
		for(int i = 0; i < 4; i++){
			if(inventory[i] != craftInventory[i]){
				return true;
			}
		}
		return false;
	}

	public void toggle(){
		if(this.toggleState){
			this.toggleState = false;
		}else{
			this.toggleState = true;
		}
	}

	public boolean getToggledState(){
		return this.toggleState;
	}

	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);

		this.toggleState = compound.getBoolean("toggled");

		NBTTagList itemTagList = compound.getTagList("Items");
		this.inventory = new ItemStack[this.getSizeInventory()];


		for (int var3 = 0; var3 < itemTagList.tagCount(); ++var3){
			NBTTagCompound var4 = (NBTTagCompound)itemTagList.tagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0 && var5 < this.inventory.length){
				this.inventory[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
	}

	public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);

		compound.setBoolean("toggled", this.toggleState);

		NBTTagList itemTagList = new NBTTagList();

		for (int var3 = 0; var3 < this.inventory.length; ++var3){
			if (this.inventory[var3] != null){
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte)var3);
				this.inventory[var3].writeToNBT(var4);
				itemTagList.appendTag(var4);
			}
		}

		compound.setTag("Items", itemTagList);
	}

	//IInventory

	@Override
	public int getSizeInventory(){
		return this.inventory.length;
	}

	public ItemStack getStackInSlot(int par1){
		return this.inventory[par1];
	}

	public ItemStack decrStackSize(int par1, int par2){
		if (this.inventory[par1] != null){
			ItemStack var3;

			if (this.inventory[par1].stackSize <= par2){
				var3 = this.inventory[par1];
				this.inventory[par1] = null;
				return var3;
			}
			else{
				var3 = this.inventory[par1].splitStack(par2);

				if (this.inventory[par1].stackSize == 0){
					this.inventory[par1] = null;
				}

				return var3;
			}
		}
		else{
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int par1){
		if (this.inventory[par1] != null){
			ItemStack var2 = this.inventory[par1];
			this.inventory[par1] = null;
			return var2;
		}
		else{
			return null;
		}
	}

	public void setInventorySlotContents(int par1, ItemStack par2ItemStack){
		this.inventory[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()){
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	public String getInvName(){
		return "container.electrostaticgenerator";
	}


	public int getInventoryStackLimit(){
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer){
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
