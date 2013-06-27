package hydrocraft.tileentity;

import hydrocraft.MechanicalPowerProvider;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityStock extends MechanicalPowerProvider{
	
	public TileEntityStock(){
		
	}

	@Override
	public boolean canBlockBeplacedVertical() {
		return true;
	}
}
