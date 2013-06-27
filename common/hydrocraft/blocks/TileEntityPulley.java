package hydrocraft.blocks;

import hydrocraft.api.IMechanicMachine;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityPulley extends TileEntity implements IMechanicMachine{

	public TileEntityPulley(){
		
	}
	
	@Override
	public void runMachine(int par1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRequiredPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ForgeDirection[] mechanicInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
