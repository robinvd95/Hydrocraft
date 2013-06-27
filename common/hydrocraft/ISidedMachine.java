package hydrocraft;

import java.util.List;

import net.minecraftforge.common.ForgeDirection;

public interface ISidedMachine {

	ForgeDirection[] getOutput();
	
	ForgeDirection[] getInput();
	
}
