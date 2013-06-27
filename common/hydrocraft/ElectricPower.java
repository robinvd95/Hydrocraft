package hydrocraft;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public abstract class ElectricPower extends TileEntity{

	public List<ForgeDirection> directions = new ArrayList();
	public List<Integer> directionID = new ArrayList();

	public int power;

	private boolean isRequesting;

	public PowerType powerType;

	public enum PowerType{
		Provider, Transfer;
	}

	public ElectricPower(PowerType par1){
		this.powerType = par1;
	}

	public void updateEntity(){
		this.setDirections();
	}

	public void setDirections(){
		this.directionID.clear();
		this.directions.clear();
		for(int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++){
			ForgeDirection x = ForgeDirection.getOrientation(i);
			TileEntity var1 = this.worldObj.getBlockTileEntity(xCoord+x.offsetX, yCoord+x.offsetY, zCoord+x.offsetZ);
			if(var1 instanceof ElectricPower){
				this.directions.add(x);
				this.directionID.add(i);
			}
		}
	}

	/*public int requestPower(int par1, ForgeDirection input){
		this.isRequesting = false;
		List<ForgeDirection> var1 = this.directions;
		if(var1.contains(input.getOpposite())){
			var1.remove(input.getOpposite());
		}

		int var2 = var1.size();

		if(var1.size() > 0){
			for(ForgeDirection x : var1){
				TileEntity var7 = this.worldObj.getBlockTileEntity(xCoord+x.offsetX, yCoord+x.offsetY, zCoord+x.offsetZ);;
				if(var7 instanceof ElectricPower){
					ElectricPower var4 = (ElectricPower) this.worldObj.getBlockTileEntity(xCoord+x.offsetX, yCoord+x.offsetY, zCoord+x.offsetZ);
					if(var4.powerType == PowerType.Provider){
						ElectricPowerProvider var6 = (ElectricPowerProvider) var4;
						if(var6.ouputSides().contains(x)){
							int var5 = par1;
							if(var5 > this.getMaxPower()){
								var5 = this.getMaxPower();
							}
							if(var5 > var4.power){
								var5 = var4.power;
							}
							var4.power -= var5;
							return var5;
						}
					}else{
						if(var4.powerType == PowerType.Transfer){
							if(!var4.isRequesting){
								this.isRequesting = true;
								return var4.requestPower(par1, x);
							}
						}
					}
				}
			}
		}
		return 0;
	}*/

	public abstract int getMaxPower();
}