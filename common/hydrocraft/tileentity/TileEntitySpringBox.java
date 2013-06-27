package hydrocraft.tileentity;

import net.minecraftforge.common.ForgeDirection;
import hydrocraft.MechanicalPowerProvider;
import hydrocraft.api.IMechanicMachine;
import hydrocraft.tileentity.base.TileHydrocraft;

public class TileEntitySpringBox extends MechanicalPowerProvider implements IMechanicMachine{

	private int mechanicPower = 0;
	
	@Override
	public void runMachine(int par1) {
		this.mechanicPower += par1;
	}

	@Override
	public int getRequiredPower() {
		return 0;
	}

	@Override
	public ForgeDirection[] mechanicInput() {
		return null;
	}

	@Override
	public boolean canBlockBeplacedVertical() {
		return true;
	}

}
