package hydrocraft;

import hydrocraft.api.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class EnergyStorage {

	public final int capacity;

	public float currentPower;

	public EnergyStorage(int par1){
		this.capacity = par1;
	}

	public float addEnergy(float par1, boolean doesEnergyAdd){
		float var1 = par1;
		float used = 0;
		while(var1 > 0){
			var1--;
			if(this.currentPower + used >= this.capacity){
				break;
			}
			used++;

		}
		if(doesEnergyAdd){
			this.currentPower += used;
		}
		return used;
	}
	
	public float getFreeSpace(){
		return this.capacity - this.currentPower;
	}

	public float drainEnergy(float par1, boolean par2){
		float var1 = par1;
		float used = 0;
		while(var1 > 0){
			var1--;
			if(this.currentPower - used < 0){
				break;
			}
			used++;

		}
		this.currentPower -= used;
		return used;
	}
}