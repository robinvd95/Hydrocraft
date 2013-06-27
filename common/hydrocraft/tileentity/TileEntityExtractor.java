package hydrocraft.tileentity;

import hydrocraft.api.utils.Utils;
import hydrocraft.tileentity.base.TileHydrocraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;

public class TileEntityExtractor extends TileHydrocraft{

	private ForgeDirection orientation = ForgeDirection.UP;
	private boolean redstoneState;

	private int maxDrainPerTick = 100;
	
	public TileEntityExtractor(){
	}

	public void update(){
		this.redstoneState = this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);

		if(this.redstoneState && !this.worldObj.isRemote){
			TileEntity frontTile = this.worldObj.getBlockTileEntity(xCoord+this.orientation.offsetX, yCoord+this.orientation.offsetY, zCoord+this.orientation.offsetZ);
			TileEntity backTile = this.worldObj.getBlockTileEntity(xCoord+this.orientation.getOpposite().offsetX, yCoord+this.orientation.getOpposite().offsetY, zCoord+this.orientation.getOpposite().offsetZ);

			if((frontTile instanceof ITankContainer) && (backTile instanceof ITankContainer)){
				
				ITankContainer frontTank = (ITankContainer) frontTile;
				ITankContainer backTank = (ITankContainer) backTile;

				ILiquidTank[] tanks = backTank.getTanks(orientation.getOpposite());
				if(tanks.length > 0){
					ILiquidTank tank = tanks[0];
					
					if(tank != null && tank.getLiquid() != null){

						LiquidStack maxDrained = backTank.drain(orientation.getOpposite(), this.maxDrainPerTick, false);
						int max = frontTank.fill(orientation.getOpposite(), maxDrained, true);
						
						
						LiquidStack drained = backTank.drain(orientation.getOpposite(), max, true);
					}
				}
			}
		}
	}

	public ForgeDirection getOrientation() {
		return orientation;
	}

	public void setOrientation(ForgeDirection orientation) {
		this.orientation = orientation;
	}

	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("orientation", this.orientation.ordinal());
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt){
		NBTTagCompound tag = pkt.customParam1;
		int i = tag.getInteger("orientation");
		Utils.printLine(i);
		this.orientation = ForgeDirection.getOrientation(tag.getInteger("orientation"));
	}

	public boolean getRedstoneState(){
		return redstoneState;
	}

	public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setInteger("orientation", this.orientation.ordinal());
	}

	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		this.orientation = ForgeDirection.getOrientation(compound.getInteger("orientation"));
	}
}
