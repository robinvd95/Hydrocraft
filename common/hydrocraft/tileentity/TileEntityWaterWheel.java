package hydrocraft.tileentity;

import hydrocraft.IOrientation;
import hydrocraft.MechanicalPowerProvider;
import hydrocraft.api.utils.Utils;
import hydrocraft.api.utils.Vector3;
import hydrocraft.packets.PacketRequestRotation;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityWaterWheel extends MechanicalPowerProvider implements IOrientation{

	public ForgeDirection waterFlowDirection;

	public TileEntityWaterWheel(){
		
	}

	public void init(){
		if(!this.worldObj.isRemote){

		}else{
			PacketDispatcher.sendPacketToServer(new PacketRequestRotation(this.getPosition()).makePacket());
		}
	}

	public void update(){
		if(this.canProvidePower()){
			super.update();
			this.rotation += this.getRotationIncrease();
		}
	}

	@Override
	public boolean canProvidePower(){
		waterFlowDirection = Utils.getWaterFlowDirection(worldObj, xCoord, yCoord-1, zCoord);
		if(!this.getValidFlowDirections().contains(this.waterFlowDirection)){
			return false;
		}

		return this.worldObj.getBlockId(xCoord, yCoord-1, zCoord) == Block.waterStill.blockID && this.worldObj.getBlockMetadata(xCoord, yCoord-1, zCoord) > 0;
	}

	public boolean isRotationInversed(){

		return false;
	}

	public boolean canIAcceptPower(ForgeDirection powerFlowDirection){
		return false;

	}

	public float getRotation(){
		return this.rotation;
	}

	public int getProducingPower(){
		if(!this.canProvidePower()){
			return 0;
		}
		return 10;
	}

	@Override
	public boolean canBlockBeplacedVertical() {
		return false;


	}

	public List<ForgeDirection> getValidFlowDirections(){
		List<ForgeDirection> list = new ArrayList();
		if(this.direction == ForgeDirection.SOUTH || this.direction == ForgeDirection.NORTH){
			list.add(ForgeDirection.EAST);
			list.add(ForgeDirection.WEST);
		}
		else if(this.direction == ForgeDirection.EAST || this.direction == ForgeDirection.WEST){
			list.add(ForgeDirection.NORTH);
			list.add(ForgeDirection.SOUTH);
		}
		return list;
	}

	public float getRotationIncrease(){
		if(this.direction == ForgeDirection.NORTH){
			if(this.waterFlowDirection == ForgeDirection.EAST){
				return -0.5f;
			}
			return 0.5f;
		}
		if(this.direction == ForgeDirection.SOUTH){
			if(this.waterFlowDirection == ForgeDirection.EAST){
				return 0.5f;
			}
			return -0.5f;
		}
		if(this.direction == ForgeDirection.EAST){
			if(this.waterFlowDirection == ForgeDirection.NORTH){
				return 0.5f;
			}
			return -0.5f;
		}
		if(this.direction == ForgeDirection.WEST){
			if(this.waterFlowDirection == ForgeDirection.NORTH){
				return -0.5f;
			}
			return 0.5f;
		}
		return 0f;
	}
}