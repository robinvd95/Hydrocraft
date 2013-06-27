package hydrocraft.tileentity;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import buildcraft.BuildCraftFactory;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityWaterTank extends TileEntity implements ITankContainer{
	
	public final LiquidTank tank = new LiquidTank(LiquidContainerRegistry.BUCKET_VOLUME * 4);
	public boolean hasUpdate = false;
	
	
	public TileEntityWaterTank(){
		this.tank.setLiquid(new LiquidStack(Block.waterStill, LiquidContainerRegistry.BUCKET_VOLUME * 2));
	}
	
	public void updateEntity(){
		if(hasUpdate){
			//Sync
			hasUpdate = false;
		}
		
		TileEntityWaterTank var1 = this.getTankBelow(this);
		if(var1 != null){
			this.moveLiquidDown(var1);
		}
	}
	
	private TileEntityWaterTank getBottomTank(){
		TileEntityWaterTank tank = this;
		
		while(true){
			TileEntityWaterTank var1 = this.getTankBelow(tank);
			if(var1 != null){
				tank = var1;
			}else{
				break;
			}
		}
		return tank;
	}
	
	private TileEntityWaterTank getTankBelow(TileEntityWaterTank par1){
		TileEntity var1 = this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		if(var1 instanceof TileEntityWaterTank){
			return (TileEntityWaterTank) var1;
		}else{
			return null;
		}
	}
	
	private TileEntityWaterTank getTankAbove(TileEntityWaterTank tankToFill) {
		TileEntity var1 = this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		if(var1 instanceof TileEntityWaterTank){
			return (TileEntityWaterTank) var1;
		}else{
			return null;
		}
	}
	
	private void moveLiquidDown(TileEntityWaterTank par1){
		if(par1.tank.getLiquid() == null ||!par1.tank.getLiquid().isLiquidEqual(this.tank.getLiquid())){
			return;
		}
		int used = par1.tank.fill(tank.getLiquid(), true);
		if (used > 0) {
			hasUpdate = true;
			par1.hasUpdate = true;
			tank.drain(used, true);
		}
	}

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		return fill(0 , resource, true);
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		if (tankIndex != 0 || resource == null) {
			return 0;
		}

		resource = resource.copy();
		int totalUsed = 0;
		TileEntityWaterTank tankToFill = getBottomTank();

		LiquidStack liquid = tankToFill.tank.getLiquid();
		if (liquid != null && liquid.amount > 0 && !liquid.isLiquidEqual(resource)) {
			return 0;
		}

		while (tankToFill != null && resource.amount > 0) {
			int used = tankToFill.tank.fill(resource, doFill);
			resource.amount -= used;
			if (used > 0) {
				tankToFill.hasUpdate = true;
			}

			totalUsed += used;
			tankToFill = getTankAbove(tankToFill);
		}
		return totalUsed;
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxEmpty, boolean doDrain) {
		return drain(0, maxEmpty, doDrain);
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxEmpty, boolean doDrain) {
		TileEntityWaterTank bottom = getBottomTank();
		bottom.hasUpdate = true;
		return bottom.tank.drain(maxEmpty, doDrain);
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		LiquidTank compositeTank = new LiquidTank(tank.getCapacity());

		TileEntityWaterTank tile = getBottomTank();

		int capacity = tank.getCapacity();

		if (tile != null && tile.tank.getLiquid() != null) {
			compositeTank.setLiquid(tile.tank.getLiquid().copy());
		} else {
			return new ILiquidTank[]{compositeTank};
		}

		tile = getTankAbove(tile);

		while (tile != null) {

			LiquidStack liquid = tile.tank.getLiquid();
			if (liquid == null || liquid.amount == 0) {
				// NOOP
			} else if (!compositeTank.getLiquid().isLiquidEqual(liquid)) {
				break;
			} else {
				compositeTank.getLiquid().amount += liquid.amount;
			}

			capacity += tile.tank.getCapacity();
			tile = getTankAbove(tile);
		}

		compositeTank.setCapacity(capacity);
		return new ILiquidTank[]{compositeTank};
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		if (direction == DOWN && worldObj != null && worldObj.getBlockId(xCoord, yCoord - 1, zCoord) != BuildCraftFactory.tankBlock.blockID) {
			return tank;
		}
		return null;
	}
}
