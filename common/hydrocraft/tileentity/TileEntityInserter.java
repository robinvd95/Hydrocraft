package hydrocraft.tileentity;

import hydrocraft.IOrientation;
import hydrocraft.api.IInsertable;
import hydrocraft.api.utils.Utils;
import hydrocraft.api.utils.Vector3;
import hydrocraft.api.utils.WorldPosition;
import hydrocraft.packets.PacketRequestRotation;
import hydrocraft.tileentity.base.TileHydrocraft;

import java.util.HashMap;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityInserter extends TileHydrocraft implements IOrientation {

	protected ForgeDirection direction = ForgeDirection.UP;
	private boolean activated = false;
	private int tickInterval = 1;

	private int slot = 0;

	private int curTick = tickInterval;

	public void init(){
		if(!this.worldObj.isRemote){

		}else{
			PacketDispatcher.sendPacketToServer(new PacketRequestRotation(this.getPosition()).makePacket());
		}
	}
	
	public void update(){
		if(this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)){
			this.activated = true;
		}else{
			this.activated = false;
		}

		if(this.activated){
			this.curTick--;
			if(this.curTick < 0){
				this.curTick = this.tickInterval;
				WorldPosition position = Utils.getWorldPositionFromForgeDirection(direction, xCoord, yCoord, zCoord, worldObj);
				TileEntity tileEntity = Utils.getTileEntity(position);

				position = Utils.getWorldPositionFromForgeDirection(direction.getOpposite(), xCoord, yCoord, zCoord, worldObj);
				TileEntity targetMachine = Utils.getTileEntity(position);

				if(tileEntity instanceof IInventory && targetMachine instanceof IInsertable){
					this.insertItem((IInventory)tileEntity, (IInsertable)targetMachine);
				}
			}
		}
	}

	public void insertItem(IInventory inv, IInsertable target){
		if(this.slot >= inv.getSizeInventory()){
			slot = 0;
		}
		ItemStack stack = inv.getStackInSlot(this.slot);
		if(stack != null){
			HashMap<Integer, Integer[]> map = target.getMap();
			int stackItemID = stack.itemID;
			if(map.containsKey(stackItemID)){
				Integer[] targetSlot = map.get(stackItemID);
				for(int i = 0; i < targetSlot.length; i++){
					ItemStack targetStack = target.getSlotContent(targetSlot[i]);
					if(targetStack == null){
						target.setSlotContent(targetSlot[i], stack);
						inv.setInventorySlotContents(this.slot, null);
						break;
					}
				}
			}
		}
		this.slot++;
	}

	public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setInteger("orientation", this.direction.ordinal());
	}

	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		this.direction = ForgeDirection.getOrientation(compound.getInteger("orientation"));
	}

	public boolean getActivated(){
		return this.activated;
	}

	@Override
	public ForgeDirection getOrientation() {
		return this.direction;
	}

	@Override
	public void setOrientation(ForgeDirection par1) {
		this.direction = par1;
	}

	@Override
	public Vector3 getPosition() {
		return new Vector3(this.xCoord, this.yCoord, this.zCoord);
	}

}
