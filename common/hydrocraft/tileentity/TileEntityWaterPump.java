package hydrocraft.tileentity;

import hydrocraft.HydrocraftLiquidTank;
import hydrocraft.api.IMechanicMachine;
import hydrocraft.api.utils.Utils;
import hydrocraft.api.utils.Vector3;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityWaterPump extends TileEntity implements IMechanicMachine ,ITankContainer{

	private LiquidTank liquidTank = new LiquidTank(4000);

	private int powerStored = 0;
	private int requiredPower = 1000;
	private boolean pumpScheduled = false;

	private boolean lastPumpScheduled = false;

	private float progress = 0;

	public TileEntityWaterPump(){

	}

	@Override
	public void runMachine(int par1) {
		this.powerStored += par1;
	}

	public void updateEntity(){
		if(!this.worldObj.isRemote){
			if(this.canPump()){
				this.pumpScheduled = this.powerStored > this.requiredPower;
				if(this.pumpScheduled != this.lastPumpScheduled){
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				}
				this.lastPumpScheduled = this.pumpScheduled;
				if(this.pumpScheduled){
					this.progress += 0.02f;

					if(this.progress > 0.5f){
						this.pump();
						this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					}
				}
			}
			if(this.progress >= 1.0f){
				this.progress = 0.0f;
			}else if(progress > 0.0f){
				this.progress += 0.02f;
			}
		}else{
			if(this.pumpScheduled){
				this.progress += 0.02f;
			}
			if(this.progress >= 1.0f){
				this.pumpScheduled = false;
				this.progress = 0.0f;
			}else if(progress > 0.0f){
				this.progress += 0.02f;
			}
		}
	}

	private boolean canPump(){
		return worldObj.getBlockId(xCoord, yCoord-1, zCoord) == Block.waterStill.blockID && !this.isTankFull();
	}

	private boolean isTankFull(){
		if(!this.liquidTank.containsValidLiquid()){
			return false;
		}
		return this.liquidTank.getCapacity() <= this.liquidTank.getLiquid().amount;
	}

	private void pump(){
		this.powerStored = 0;
		int attempts = 0;
		boolean isValidPumpPosition = false;
		Vector3 vec = null;
		while(!isValidPumpPosition){
			attempts++;
			vec = this.getPumpPosition();
			if(this.worldObj.getBlockId(vec.getX(), vec.getY(), vec.getZ()) == Block.waterStill.blockID){
				break;
			}
			if(attempts > 30){
				vec = new Vector3(xCoord, yCoord-1, zCoord);
				break;
			}
		}

		this.liquidTank.fill(new LiquidStack(Block.waterStill, 1000), true);
		this.worldObj.setBlockToAir(vec.getX(), vec.getY(), vec.getZ());

	}

	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("isPumping", this.pumpScheduled);
		if(this.liquidTank.containsValidLiquid()){
			tag.setInteger("amount", this.liquidTank.getLiquid().amount);
		}else{
			tag.setInteger("amount", 0);
		}
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt){
		NBTTagCompound tag = pkt.customParam1;
		this.pumpScheduled = tag.getBoolean("isPumping");
		this.liquidTank.setLiquid(new LiquidStack(Block.waterStill.blockID, tag.getInteger("amount")));

	}

	private Vector3 getPumpPosition(){
		Random random = new Random();
		int xRand = random.nextInt(4) - 2;
		int zRand = random.nextInt(4) - 2;

		return new Vector3(xRand+this.xCoord, this.yCoord-1, zRand+this.zCoord);
	}

	public float getProgress(){
		return this.progress;
	}

	public LiquidTank getLiquidTank(){
		return this.liquidTank;
	}

	@Override
	public int getRequiredPower() {
		return 0;
	}

	@Override
	public ForgeDirection[] mechanicInput() {
		return new ForgeDirection[]{ForgeDirection.UP};
	}

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.liquidTank.drain(maxDrain, doDrain);
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		return this.liquidTank.drain(maxDrain, doDrain);
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		return new ILiquidTank[]{this.liquidTank};
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		return this.liquidTank;
	}

}
