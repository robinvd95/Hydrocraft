package hydrocraft.tileentity;

import hydrocraft.GearBox;
import hydrocraft.MechanicalPowerProvider;
import hydrocraft.blocks.BlockMechanicalPower;
import hydrocraft.blocks.BlockMechanicalPower.BlockType;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityGearBox extends GearBox{

	@Override
	public int maxStocks() {
		return 2;
	}

	/*private List<ForgeDirection> directions = new ArrayList();

	public TileEntityGearBox(){

	}

	public void updateEntity(){
		this.directions.clear();
		for(ForgeDirection x : ForgeDirection.VALID_DIRECTIONS){
			if(Block.blocksList[this.worldObj.getBlockId(xCoord+x.offsetX, yCoord+x.offsetY, zCoord+x.offsetZ)] instanceof BlockMechanicalPower){
				this.directions.add(x);
			}
		}

		if(!this.worldObj.isRemote){
			if(this.directions.size() > 2){
				this.destroyGearBox();
			}
		}
	}
	
	public void redirectPower(int par1, ForgeDirection par2){
		ForgeDirection otherDirection = ForgeDirection.UNKNOWN;
		for(ForgeDirection x : this.directions){
			if(x != par2.getOpposite() && x != par2){
				otherDirection = x;
			}
		}

		if(otherDirection != ForgeDirection.UNKNOWN){
			this.traceOtherStocks(otherDirection, par1);
		}
	}

	public void destroyGearBox(){
		this.worldObj.newExplosion((Entity)null, this.xCoord, this.yCoord, this.zCoord, 1f, true, false);
		this.worldObj.destroyBlock(this.xCoord, this.yCoord, this.zCoord, true);
	}

	public List<ForgeDirection> getDirections(){
		return this.directions;
	}

	@Override
	public boolean canBlockBeplacedVertical() {
		return false;
	}*/

}
