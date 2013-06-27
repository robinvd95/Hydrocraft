package hydrocraft.tileentity;

import hydrocraft.Cable;
import hydrocraft.ElectricPower.PowerType;

public class TileEntityCable extends Cable {
	
	public TileEntityCable(){
		
	}

	@Override
	protected int getMaxEnergy() {
		return 50;
	}

	@Override
	public int getFlowSpeed() {
		return 10;
	}
}