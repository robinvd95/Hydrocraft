package hydrocraft;

import hydrocraft.api.utils.Vector3;
import net.minecraftforge.common.ForgeDirection;

public interface IOrientation {

	ForgeDirection getOrientation();
	
	void setOrientation(ForgeDirection par1);
	
	Vector3 getPosition();
}
