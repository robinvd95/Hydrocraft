package hydrocraft.tileentity;

import hydrocraft.BaseHydrocraft;
import hydrocraft.HydrocraftLiquidTank;
import hydrocraft.api.utils.Utils;
import hydrocraft.tileentity.base.TileHydrocraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityTank extends TileHydrocraft implements IInventory{

	private boolean isValidMultiblock = false;

	public boolean isMultiblock;

	public ItemStack[] inventory = new ItemStack[2];

	private int tankDiameter, tankHeigth;

	protected HydrocraftLiquidTank liquidTank;

	private TileEntityTank coreTank;
	int coreX;
	int coreY;
	int coreZ;

	private int syncTime;

	public void init(){
		TileEntity tileEntity = this.worldObj.getBlockTileEntity(coreX, coreY, coreZ);
		if(tileEntity instanceof TileEntityTank){
			this.setCore((TileEntityTank) tileEntity);
		}
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public boolean getIsValid(){
		return isValidMultiblock;
	}

	public void invalidateMultiblock(){
		isValidMultiblock = false;
		this.dissasembleTank();
	}

	public void update(){
		if(!this.worldObj.isRemote){
			this.syncWithClient();
		}
	}

	private void syncWithClient(){
		this.syncTime--;
		if(syncTime < 0){
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

			Random rand = new Random();
			this.syncTime = rand.nextInt(100) + 100;
		}
	}

	public boolean checkIfProperlyFormed(){
		int diameter = this.getDiameter();

		if(diameter < 3){
			return false;
		}
		int heigth = this.getHeigth();

		Utils.printLine(heigth);

		if(heigth < 3){
			return false;
		}

		int temp = (diameter - 1) / 2;
		for(int i = -temp; i <= temp; i++){

			for(int j = -temp; j <= temp; j++){

				for(int k = 0; k <= heigth; k++){

					int x = this.xCoord + i;
					int y = this.yCoord + k;
					int z = this.zCoord + j;

					int blockID = this.worldObj.getBlockId(x, y, z);
					int blockMetadata = this.worldObj.getBlockMetadata(x, y, z);

					if((k == 0 || k == heigth) && blockMetadata == 2){
						return false;
					}

					if(i < temp && i > -temp && k > 0 && k < heigth && j < temp && j > -temp){
						if(blockID != 0){
							return false;
						}
					}
					else{
						if(blockID != BaseHydrocraft.tankBlock.blockID){
							return false;
						}
					}

				}
			}
		}


		return true;
	}

	public void createTank(){

		int diameter = this.getDiameter();
		int heigth = this.getHeigth();

		this.tankDiameter = diameter;
		this.tankHeigth = heigth;

		int temp = (diameter - 1) / 2;
		for(int i = -temp; i <= temp; i++){

			for(int j = -temp; j <= temp; j++){

				for(int k = 0; k <= heigth; k++){

					int x = this.xCoord + i;
					int y = this.yCoord + k;
					int z = this.zCoord + j;

					int blockID = this.worldObj.getBlockId(x, y, z);

					if(i == 0 && j == 0 && k == 0){
						continue;
					}

					if(i < temp && i > -temp && k > 0 && k < heigth && j < temp && j > -temp){
						this.worldObj.setBlock(x, y, z, BaseHydrocraft.tankBlock.blockID, 0, 2);
					}

					TileEntityTank tileEntityTank = (TileEntityTank) this.worldObj.getBlockTileEntity(x, y, z);
					tileEntityTank.setCore(this);
					tileEntityTank.isMultiblock = true;
					this.worldObj.markBlockForUpdate(x, y, z);

				}
			}
		}
		this.isValidMultiblock = true;


		int tankCapacity = ((diameter * 2) * (heigth+1)) * (LiquidContainerRegistry.BUCKET_VOLUME * 16);
		Utils.printLine(tankCapacity);
		this.liquidTank = new HydrocraftLiquidTank(tankCapacity);
	}

	private void dissasembleTank() {
		int diameter = this.tankDiameter;
		int heigth = this.tankHeigth;

		int temp = (diameter - 1) / 2;
		for(int i = -temp; i <= temp; i++){

			for(int j = -temp; j <= temp; j++){

				for(int k = 0; k <= heigth; k++){

					int x = this.xCoord + i;
					int y = this.yCoord + k;
					int z = this.zCoord + j;

					int blockID = this.worldObj.getBlockId(x, y, z);

					/*if(i == 0 && j == 0 && k == 0){
						continue;
					}*/

					if(i < temp && i > -temp && k > 0 && k < heigth && j < temp && j > -temp){
						this.worldObj.setBlockToAir(x, y, z);
					}
					else{
						try{
							TileEntityTank tileEntityTank = (TileEntityTank) this.worldObj.getBlockTileEntity(x, y, z);
							if(tileEntityTank != null){
								tileEntityTank.setCoreToNull();
								tileEntityTank.isMultiblock = false;
							}
						}catch(ClassCastException e){
							e.printStackTrace();
						}
					}
					this.worldObj.markBlockForUpdate(x, y, z);

				}
			}
		}
	}

	private int getHeigth(){
		for(int i = 1; i < 9; i++){
			int blockID = this.worldObj.getBlockId(xCoord, yCoord+i, zCoord);
			if(blockID == BaseHydrocraft.tankBlock.blockID){
				return i;
			}
		}
		return 0;
	}

	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);

		this.isValidMultiblock = compound.getBoolean("valid");
		this.isMultiblock = compound.getBoolean("isPart");

		//Tank Data
		this.tankDiameter = compound.getInteger("tankDiameter");
		this.tankHeigth = compound.getInteger("tankHeigth");

		//Set Core
		this.coreX = compound.getInteger("coreX");
		this.coreY = compound.getInteger("coreY");
		this.coreZ = compound.getInteger("coreZ");

		if(this.isValidMultiblock){
			int capacity = compound.getInteger("tankCapacity");
			LiquidStack stack = LiquidStack.loadLiquidStackFromNBT(compound);
			this.liquidTank = new HydrocraftLiquidTank(capacity, stack);
		}
	}

	public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setBoolean("valid", this.isValidMultiblock);
		compound.setBoolean("isPart", this.isMultiblock);

		compound.setInteger("tankDiameter", this.tankDiameter);
		compound.setInteger("tankHeigth", this.tankHeigth);

		compound.setInteger("coreX", this.coreX);
		compound.setInteger("coreY", this.coreY);
		compound.setInteger("coreZ", this.coreZ);

		if(this.isValidMultiblock && this.liquidTank != null){
			compound.setInteger("tankCapacity", this.liquidTank.getCapacity());

			LiquidStack stack = this.liquidTank.getLiquid();
			if(stack != null){
				stack.writeToNBT(compound);
			}
		}
	}


	private int getDiameter(){
		int diameter = 1;
		boolean needCheck = true;
		int x = 1;
		for(int i = 0; i < 4; i++){
			int blockID = this.worldObj.getBlockId(xCoord + x, yCoord, zCoord);
			if(blockID == BaseHydrocraft.tankBlock.blockID){
				diameter += 2;
				x++;
			}else{
				break;
			}
		}

		return diameter;
	}

	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("isValid", this.getIsValid());
		tag.setBoolean("partOf", this.isMultiblock);
		if(this.isValidMultiblock){
			if(this.liquidTank.containsValidLiquid()){
				tag.setIntArray("tankData", new int[]{this.liquidTank.getCapacity(), this.liquidTank.getLiquid().amount, this.liquidTank.getLiquid().itemID, this.getTankDiameter(), this.getTankHeigth()});
			}else{
				tag.setIntArray("tankData", new int[]{this.liquidTank.getCapacity(), 0, 0, this.getTankDiameter(), this.getTankHeigth()});
			}
			
		}
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt){
		NBTTagCompound tag = pkt.customParam1;
		this.isValidMultiblock = tag.getBoolean("isValid");
		this.isMultiblock = tag.getBoolean("partOf");
		if(this.isValidMultiblock){
			int[] tankData = tag.getIntArray("tankData");
			if(tankData.length > 3){
				this.liquidTank = new HydrocraftLiquidTank(tankData[2], tankData[1], tankData[0]);
				this.tankDiameter = tankData[3];
				this.tankHeigth = tankData[4];
			}
		}
	}

	public boolean isPartOfMultiblock(){
		return this.isMultiblock;
	}

	public void setCoreToNull(){
		this.coreTank = null;
		this.coreX = 0;
		this.coreY = 0;
		this.coreZ = 0;
	}

	public void setCore(TileEntityTank tank){
		this.coreTank = tank;
		this.coreX = tank.xCoord;
		this.coreY = tank.yCoord;
		this.coreZ = tank.zCoord;
	}

	public TileEntityTank getCore(){
		if(this.coreTank == null && !this.isValidMultiblock){
			TileEntity tileEntity = this.worldObj.getBlockTileEntity(this.coreX, this.coreY, this.coreZ);
			if(tileEntity instanceof TileEntityTank){
				this.coreTank = (TileEntityTank) tileEntity;
			}
		}
		return this.coreTank;
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


	public void setInventorySlotContents(int par1, ItemStack par2ItemStack){
		this.inventory[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()){
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	public String getInvName(){
		return "container.tank";
	}

	public boolean isInvNameLocalized(){
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {

	}

	@Override
	public void closeChest() {

	}
	
	public int getTankHeigth(){
		return this.tankHeigth;
	}
	
	public int getTankDiameter(){
		return this.tankDiameter;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	protected HydrocraftLiquidTank getTankFromMultiblock(){
		if(this.getIsValid()){
			return this.liquidTank;
		}else if(this.getCore() != null){
			return this.getCore().liquidTank;
		}
		return null;
	}

	public HydrocraftLiquidTank getLiquidTank(){
		return this.liquidTank;
	}

	@SideOnly(Side.CLIENT)
	public void createDummyTank(){
		this.liquidTank = new HydrocraftLiquidTank(5000);
	}
}
