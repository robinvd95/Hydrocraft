package hydrocraft.tileentity;

import hydrocraft.ElectricPowerProvider;
import hydrocraft.EnergyStorage;
import hydrocraft.IElectricStorage;
import hydrocraft.api.IMechanicMachine;
import hydrocraft.api.utils.Utils;
import hydrocraft.api.utils.WorldPosition;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityPowerConverter extends ElectricPowerProvider implements IMechanicMachine, IElectricStorage{

	public EnergyStorage storage = new EnergyStorage(1000);
	
	private final ForgeDirection[] inputDirections = new ForgeDirection[]{ForgeDirection.UP, ForgeDirection.DOWN};
	
	private final ForgeDirection[] outputDirections = new ForgeDirection[]{ForgeDirection.EAST, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.WEST};

	
	public TileEntityPowerConverter(){
	}

	@Override
	public void runMachine(int par1) {
		float output = 2f;
		for(ForgeDirection direction : this.outputDirections){
			WorldPosition worldPos = Utils.getWorldPositionFromForgeDirection(direction, xCoord, yCoord, zCoord, worldObj);
			TileEntity tileEntity = Utils.getTileEntity(worldPos);
			if(tileEntity instanceof IElectricStorage){
				IElectricStorage storage = (IElectricStorage) tileEntity;
				storage.addEnergy(output, direction);
			}
		}
	}
	
	public void updateEntity(){
		
		float output = 2f;
		for(ForgeDirection direction : this.outputDirections){
			WorldPosition worldPos = Utils.getWorldPositionFromForgeDirection(direction, xCoord, yCoord, zCoord, worldObj);
			TileEntity tileEntity = Utils.getTileEntity(worldPos);
			if(tileEntity instanceof IElectricStorage){
				IElectricStorage storage = (IElectricStorage) tileEntity;
				storage.addEnergy(output, direction);
			}
		}
	}

	@Override
	public int getRequiredPower() {
		return 0;
	}

	@Override
	public ForgeDirection[] mechanicInput() {
		return this.inputDirections;
	}

	@Override
	public int getMaxPower() {
		return 10000;
	}

	@Override
	public ForgeDirection[] ouputSides() {
		return this.outputDirections;
	}

	@Override
	public EnergyStorage getEnergyStorage() {
		return this.storage;
	}

	@Override
	public float drainEnergy(float par1) {
		return this.storage.drainEnergy(par1, true);
	}

	@Override
	public float addEnergy(float par1, ForgeDirection input) {
		return 0;
	}

	@Override
	public boolean canTransferTrough() {
		return false;
	}
}
