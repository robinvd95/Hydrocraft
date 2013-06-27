package hydrocraft.bridge.buildcraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hydrocraft.IOrientation;
import hydrocraft.api.IMechanicMachine;
import hydrocraft.api.utils.Utils;
import hydrocraft.api.utils.Vector3;
import hydrocraft.packets.PacketRequestRotation;
import hydrocraft.packets.PacketRotation;
import hydrocraft.tileentity.base.TileHydrocraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.core.Position;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerProvider;
import buildcraft.api.transport.IPipeConnection;

public class TileHydrocraftEngine extends TileHydrocraft implements IEngineProvider, IPipeConnection, IPowerReceptor, IMechanicMachine, IInventory, IOrientation{

	private EngineHydrocraft engine;
	public int orientation;
	public boolean isRunning;
	private int lastRotation;

	int syncTimer = 100;
	
	private boolean alreadyReleased = false;
	
	private boolean hasUpdate;

	public ItemStack[] inventory = new ItemStack[4];

	public TileHydrocraftEngine(){
		PowerProvider power;
		this.engine = new EngineMechanical(this);
		System.out.println(xCoord + ", "+yCoord+", "+zCoord);
	}
	
	public void init(){
		if(!this.worldObj.isRemote){
			
		}else{
			System.out.println(xCoord+", "+yCoord+", "+zCoord + "Side.client");
			PacketDispatcher.sendPacketToServer(new PacketRequestRotation(this.getPosition()).makePacket());
		}
	}

	public int getMaxEngineState(){
		if(this.inventory[0] == null){
			return EngineState.GREEN.ordinal();
		}else{
			ItemUpgrade var1 = (ItemUpgrade) this.inventory[0].getItem();
			return var1.getTier()+2;
		}
	}

	public void update(){
		if(!this.worldObj.isRemote){
			if(this.orientation != this.lastRotation){
				PacketDispatcher.sendPacketToAllInDimension(new PacketRotation(this, this).makePacket(), this.worldObj.provider.dimensionId);
			}
			this.lastRotation = this.orientation;
		}
		
		/*if(this.engine.engineState.ordinal() > this.getMaxEngineState()){
			this.explode();
			return;
		}*/

		if(this.isRunning){
			this.engine.energy += this.getEnergyPerTick();
			this.engine.progress += this.engine.engineState.pistonSpeed;
			this.engine.temperature += 0.02;
			if(this.engine.progress > 1){
				this.engine.progress = 0;
				this.isRunning = false;
			}
			if(!this.alreadyReleased && this.engine.progress >= 0.5f){
				this.releaseEnergy();
			}
		}
		this.engine.temperature -= 0.015f;
		if(this.engine.temperature < 0){
			this.engine.temperature = 0;
		}
		this.engine.setEngineState();
	}

	private void explode(){
		this.worldObj.destroyBlock(xCoord, yCoord, zCoord, true);
	}

	private void startCycle(){
		this.isRunning = true;
		this.alreadyReleased = false;
	}

	public float getEnergyPerTick(){
		float var1 = this.engine.engineState.efficiency;
		if(inventory[1] == null){
			return var1;
		}else{
			ItemUpgrade var2 = (ItemUpgrade) this.inventory[1].getItem();
			int var3 = var2.getTier();
			float var4 = var1 + (float)var3 * 0.25f;
			return var4;
		}
	}

	@Override
	public EngineHydrocraft getEngine() {
		return engine;
	}

	@Override
	public void runMachine(int par1) {
		Position pos = new Position(xCoord, yCoord, zCoord, ForgeDirection.getOrientation(orientation));
		pos.moveForwards(1.0);
		TileEntity tile = worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z);

		if (isPoweredTile(tile) && !this.isRunning) {
			this.startCycle();
		}
	}

	public void releaseEnergy(){
		this.alreadyReleased = true;
		Position pos = new Position(xCoord, yCoord, zCoord, ForgeDirection.getOrientation(orientation));
		pos.moveForwards(1.0);
		TileEntity tile = worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z);

		if (isPoweredTile(tile)) {
			IPowerProvider receptor = ((IPowerReceptor) tile).getPowerProvider();

			float extracted = engine.energy;

			if (extracted > 0) {
				receptor.receiveEnergy(extracted, ForgeDirection.getOrientation(orientation).getOpposite());
			}
			engine.energy = 0;
		}
	}

	@Override
	public int getRequiredPower() {
		return 0;
	}

	@Override
	public ForgeDirection[] mechanicInput() {
		return new ForgeDirection[]{ForgeDirection.getOrientation(orientation).getOpposite()};
	}

	public void switchOrientation() {
		for (int i = orientation + 1; i <= orientation + 6; ++i) {
			ForgeDirection o = ForgeDirection.values()[i % 6];

			Position pos = new Position(xCoord, yCoord, zCoord, o);

			pos.moveForwards(1);

			TileEntity tile = worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z);

			if (isPoweredTile(tile)) {
				if (engine != null) {
				}
				orientation = o.ordinal();
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlockId(xCoord, yCoord, zCoord));
				break;
			}
		}
	}

	public boolean isPoweredTile(TileEntity tile) {
		if (tile instanceof IPowerReceptor) {
			IPowerProvider receptor = ((IPowerReceptor) tile).getPowerProvider();

			return receptor != null && receptor.getClass().getSuperclass().equals(PowerProvider.class);
		}

		return false;
	}

	@Override
	public boolean isPipeConnected(ForgeDirection with) {
		return false;
	}

	@Override
	public void setPowerProvider(IPowerProvider provider) {

	}

	@Override
	public IPowerProvider getPowerProvider() {
		return null;
	}

	@Override
	public void doWork() {

	}

	@Override
	public int powerRequest(ForgeDirection from) {
		return 0;
	}

	//IInventory
	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.inventory[i];
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
		return "container.engine";
	}

	public void readFromNBT(NBTTagCompound par1)
	{
		super.readFromNBT(par1);

		this.orientation = par1.getInteger("orientation");

		NBTTagList var2 = par1.getTagList("Items");
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

	public void writeToNBT(NBTTagCompound par1)
	{
		super.writeToNBT(par1);
		par1.setInteger("orientation", this.orientation);

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

		par1.setTag("Items", var2);
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
	public ForgeDirection getOrientation() {
		return ForgeDirection.getOrientation(orientation);
	}

	@Override
	public void setOrientation(ForgeDirection par1) {
		this.orientation = par1.ordinal();
	}

	@Override
	public Vector3 getPosition() {
		return new Vector3(this.xCoord, this.yCoord, this.zCoord);
	}
}
