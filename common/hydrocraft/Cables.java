package hydrocraft;

import hydrocraft.api.utils.Vector3;
import hydrocraft.api.utils.WorldPosition;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class Cables {

	/*public List<Cable> getAllConnectedCables(ForgeDirection inputDirection, ForgeDirection[] validDirections, WorldPosition position) {
		List<Cable> cables = new ArrayList();
		for(ForgeDirection validDirection : validDirections){
			Vector3 vector = new Vector3(position.getX(), position.getY(), position.getZ());
			if(inputDirection == validDirection){
				//TODO Debug
			}else{
				int x = position.getX() + validDirection.offsetX;
				int y = position.getY() + validDirection.offsetY;
				int z = position.getZ() + validDirection.offsetZ;
				TileEntity entity = position.getWorld().getBlockTileEntity(x, y, z);
				if(entity instanceof Cable && ((Cable)entity).canIBeAddedToCableArray){
					//position.setNewPosition(x, y, z);
					Cable cable = (Cable) entity;
					cable.canIBeAddedToCableArray = false;
					//cables.addAll(cable.getAllConnectedCables(validDirection.getOpposite()));
					cable.canIBeAddedToCableArray = true;
				}
			}
		}
		return cables;
	}*/
	
}
