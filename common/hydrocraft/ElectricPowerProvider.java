package hydrocraft;

import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public abstract class ElectricPowerProvider extends ElectricPower {

	public ElectricPowerProvider() {
		super(PowerType.Provider);
	}
	
	public void updateEntity(){
		this.setDirections();
		/*for(ForgeDirection x : this.directions){
			ElectricPower var1 = (ElectricPower) this.worldObj.getBlockTileEntity(xCoord+x.offsetX, yCoord+x.offsetY, zCoord+x.offsetZ);
			if(var1.powerType == PowerType.Transfer){
				this.power = var1.requestPower(this.getMaxPower(), x);
			}
		}*/
	}
	
	public abstract ForgeDirection[] ouputSides();
}