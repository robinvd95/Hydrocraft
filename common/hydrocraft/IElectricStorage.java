package hydrocraft;

import net.minecraftforge.common.ForgeDirection;

public interface IElectricStorage extends IElectricFramework{
	
	EnergyStorage getEnergyStorage();
	
	float drainEnergy(float par1);
	
	float addEnergy(float f, ForgeDirection input);
}