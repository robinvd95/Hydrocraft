package hydrocraft;

import hydrocraft.blocks.BlockMechanicalPower;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeDirection;

public abstract class GearBox extends MechanicalPowerProvider{

	private List<ForgeDirection> directions = new ArrayList();

	ForgeDirection input = ForgeDirection.UNKNOWN;
	
	private boolean areDirectionsAlreadySet = false;;
	
	public GearBox(){

	}
	
	public boolean hasRotation(){
		return false;
	}

	public void updateEntity(){
		if(!this.areDirectionsAlreadySet){
			this.setDirections();
		}
		this.input = ForgeDirection.UNKNOWN;
	}

	public void redirectPower(int par1, ForgeDirection par2){
		this.setDirections();
		for(ForgeDirection x : this.directions){
			if(x != par2.getOpposite() && x != par2){
				this.traceOtherStocks(x, par1);
			}
		}
	}
	
	public void setDirections(){
		this.directions.clear();
		if(this.input != ForgeDirection.UNKNOWN){
			this.directions.add(input.getOpposite());
		}
		for(ForgeDirection x : ForgeDirection.VALID_DIRECTIONS){
			int posX = xCoord+x.offsetX;
			int posY = yCoord+x.offsetY;
			int posZ = zCoord+x.offsetZ;
			if(Block.blocksList[this.worldObj.getBlockId(posX, posY, posZ)] instanceof BlockMechanicalPower){
				MechanicalPowerProvider var1 = (MechanicalPowerProvider) this.worldObj.getBlockTileEntity(posX, posY, posZ);
				if(var1.direction == x){
					this.directions.add(x);
				}
			}
		}

		if(!this.worldObj.isRemote){
			if(this.directions.size() > maxStocks()){
				this.destroyGearBox();
			}
		}
	}
	
	public void setInput(ForgeDirection par1){
		this.input = par1;
	}

	public void destroyGearBox(){
		this.worldObj.newExplosion((Entity)null, this.xCoord, this.yCoord, this.zCoord, 1f, true, false);
		this.worldObj.destroyBlock(this.xCoord, this.yCoord, this.zCoord, true);
	}

	public List<ForgeDirection> getDirections(){
		return this.directions;
	}
	
	public boolean canIAcceptPower(ForgeDirection par1){
		return true;
	}

	@Override
	public boolean canBlockBeplacedVertical() {
		return false;
	}

	public abstract int maxStocks();
}
