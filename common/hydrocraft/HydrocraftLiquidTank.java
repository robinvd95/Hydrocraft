package hydrocraft;

import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class HydrocraftLiquidTank extends LiquidTank{

	private int temperature = 0;
	private int pressure = 0;
	
	private int maxPressure = 2000;
	
	public HydrocraftLiquidTank(int capacity) {
		super(capacity);
	}
	
	public HydrocraftLiquidTank(int capacity, LiquidStack liquidStack){
		super(liquidStack, capacity);
	}

	public HydrocraftLiquidTank(int i, int j, int k) {
		super(i,j,k);
	}
	
	
}
