package hydrocraft;

public interface IElectricBattery extends IElectricFramework{

	EnergyStorage getEnergyStorage();
	
	float fill();
	
	float drain();
}
