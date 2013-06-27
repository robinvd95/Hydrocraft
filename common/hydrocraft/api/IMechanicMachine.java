package hydrocraft.api;

import net.minecraftforge.common.ForgeDirection;

public interface IMechanicMachine {
	void runMachine(int par1);
	int getRequiredPower();
	ForgeDirection[] mechanicInput();
}
