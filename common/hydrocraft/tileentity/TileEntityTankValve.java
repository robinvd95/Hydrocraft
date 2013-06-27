package hydrocraft.tileentity;

import hydrocraft.api.utils.Utils;
import net.minecraft.block.Block;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityTankValve extends TileEntityTank implements ITankContainer{

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		if(resource.itemID == Block.waterStill.blockID){
			
			LiquidTank tank = this.getTankFromMultiblock();

			if(tank != null){

				return tank.fill(resource, doFill);
			}
		}
		return 0;
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return this.fill(ForgeDirection.UNKNOWN, resource, doFill);
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.drain(0, maxDrain, doDrain);
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		LiquidTank tank = this.getTankFromMultiblock();
		if(tank != null){
			return tank.drain(maxDrain, doDrain);
		}
		return null;
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		return new ILiquidTank[]{this.getTankFromMultiblock()};
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		return this.getTankFromMultiblock();
	}

}
