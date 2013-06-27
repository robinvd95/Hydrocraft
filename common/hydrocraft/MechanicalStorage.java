package hydrocraft;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import hydrocraft.api.IMechanicMachine;
import hydrocraft.api.utils.WorldPosition;

public class MechanicalStorage {

	private int stored = 0;
	private int capacity = 0;
	
	private WorldPosition position;
	
	public MechanicalStorage(){
		this(0);
	}
	
	public MechanicalStorage(int capacity){
		this(capacity, 0);
	}
	
	public MechanicalStorage(int capacity, int power){
		this(capacity, power, null);
	}
	
	public void update(){
		if(this.position != null){
			return;
		}
	}
	
	public MechanicalStorage(int capacity, int power, WorldPosition worldPos){
		this.capacity = capacity;
		this.stored = power;
		this.position = worldPos;
	}
	
	public void setWorldPosition(WorldPosition pos){
		this.position = pos;
	}
	
	public void outputPower(ForgeDirection par1, int par2){
		for(int i = 1; i < 16; i++){
			int x = this.position.getX()+(par1.offsetX*i);
			int y = this.position.getY()+(par1.offsetY*i);
			int z = this.position.getZ()+(par1.offsetZ*i);

			int var1 = this.position.getWorld().getBlockId(x, y, z);
			if(var1 == 0){
				return;
			}
			TileEntity var6 = this.position.getWorld().getBlockTileEntity(x, y, z);

			if(var6 instanceof MechanicalPowerProvider){
				MechanicalPowerProvider var2 = (MechanicalPowerProvider) var6;
				if(!var2.canIAcceptPower(par1)){
					return;
				}
				//var2.rotateBlock(this);
				if(var2.isTransferBlock()){

				}
				else if(var2 instanceof GearBox){
					GearBox var3 = (GearBox) var2;
					var3.setInput(par1);
					var3.redirectPower(par2, par1);
				}
			}

			else if(var6 instanceof IMechanicMachine){
				IMechanicMachine var2 = (IMechanicMachine) var6;
				ForgeDirection[] var4 = var2.mechanicInput();
				if(var4 == null){
					return;
				}
				for(ForgeDirection var3 : var4){
					if(var3.getOpposite() == par1){
						var2.runMachine(par2);
					}
				}
			}
		}
	}
}
