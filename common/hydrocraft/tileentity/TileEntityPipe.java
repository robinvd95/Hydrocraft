package hydrocraft.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityPipe extends TileEntity implements ITankContainer{

	public List<ForgeDirection> directions = new ArrayList();

	private ForgeDirection input = ForgeDirection.UNKNOWN;

	private LiquidTank liquidTank = new LiquidTank(200);

	private int inputResetTime = 20;
	
	public TileEntityPipe(){

	}

	public void updateEntity(){
		
		this.setValidDirections();

		if(this.liquidTank.containsValidLiquid()){
			this.spreadLiquid();
		}
		
		this.inputResetTime--;
		if(this.inputResetTime < 0){
			this.input = ForgeDirection.UNKNOWN;
		}
	}

	private void spreadLiquid() {
		
		int directionAmount = this.directions.size();
		if(this.input != null && this.directions.contains(input)){
			directionAmount--;
		}
		
		if(directionAmount < 1){
			return;
		}
		
		LiquidStack tempStack = this.liquidTank.getLiquid().copy();
		
		tempStack.amount = this.liquidTank.getLiquid().amount / directionAmount;
		
		int filled = 0;
		
		if(tempStack.amount > 100){
			tempStack.amount = 100;
		}
		
		for(ForgeDirection direction : directions){
			
			if(this.input != direction){
				int x = this.xCoord + direction.offsetX;
				int y = this.yCoord + direction.offsetY;
				int z = this.zCoord + direction.offsetZ;
				if(this.liquidTank.containsValidLiquid()){
					ITankContainer tile = (ITankContainer) this.worldObj.getBlockTileEntity(x, y, z);
					filled += tile.fill(direction.getOpposite(), tempStack, true);
				}
			}
			
		}
		
		this.liquidTank.drain(filled, true);
	}

	public void setValidDirections(){
		this.directions.clear();
		for(int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++){
			ForgeDirection x = ForgeDirection.getOrientation(i);
			TileEntity var1 = this.worldObj.getBlockTileEntity(xCoord+x.offsetX, yCoord+x.offsetY, zCoord+x.offsetZ);
			if(this.canConnect(var1)){
				this.directions.add(x);
			}
		}
	}

	private boolean canConnect(TileEntity target){
		return target instanceof ITankContainer;
	}

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		if(resource.itemID != Block.waterStill.blockID){
			return 0;
		}
		this.input = from;
		int filled = this.liquidTank.fill(resource, doFill);
		if(filled > 0){
			this.inputResetTime = 20;
		}
		return filled;
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return this.fill(ForgeDirection.UNKNOWN, resource, doFill);
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