package hydrocraft;

import hydrocraft.api.utils.Utils;
import hydrocraft.api.utils.WorldPosition;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public abstract class Cable extends TileEntity implements IElectricStorage{

	public List<ForgeDirection> validDirections = new ArrayList();
	public EnergyStorage storage;
	private ForgeDirection inputDirection = ForgeDirection.UNKNOWN;

	public Cable(){
		this.storage = this.createEnergyStorage();
	}

	public void updateEntity(){
		this.setValidDirections();

		if(this.worldObj.getBlockId(xCoord, yCoord+1, zCoord) == Block.blockEmerald.blockID){
			Utils.printLine(this.storage.currentPower);
		}

		if(this.storage.currentPower > 0){
			
			for(ForgeDirection validDirection : validDirections){

				if(validDirection == inputDirection){
					
				}else{

					WorldPosition position = Utils.getWorldPositionFromForgeDirection(validDirection, this.xCoord, this.yCoord, this.zCoord, this.worldObj);
					TileEntity tileEntity = Utils.getTileEntity(position);

					if(tileEntity instanceof IElectricStorage){
						
						IElectricStorage storage = (IElectricStorage) tileEntity;

						if(storage.getEnergyStorage() != null){

							float freeSpace = storage.getEnergyStorage().getFreeSpace();
							float energyTransfer = this.storage.currentPower;

							if(energyTransfer > freeSpace){
								energyTransfer = freeSpace;
							}
							
							if(energyTransfer > this.getFlowSpeed()){
								energyTransfer = this.getFlowSpeed();
							}

							float energyUsed = storage.addEnergy(energyTransfer, validDirection);
							this.storage.drainEnergy(energyUsed, true);
						}
					}
				}
			}
		}
	}

	public void setValidDirections() {
		this.validDirections.clear();
		for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS){
			int x = this.xCoord + direction.offsetX;
			int y = this.yCoord + direction.offsetY;
			int z = this.zCoord + direction.offsetZ;
			if(this.canConnect(this.worldObj.getBlockTileEntity(x, y, z))){
				this.validDirections.add(direction);
			}
		}
	}

	public boolean canConnect(TileEntity par1){
		return par1 instanceof IElectricStorage;
	}
	public EnergyStorage createEnergyStorage(){
		return new EnergyStorage(this.getMaxEnergy());
	}

	protected abstract int getMaxEnergy();

	@Override
	public boolean canTransferTrough() {
		return true;
	}
	
	public abstract int getFlowSpeed();

	@Override
	public EnergyStorage getEnergyStorage() {
		return this.storage;
	}

	@Override
	public float drainEnergy(float par1) {
		return 0;
	}

	@Override
	public float addEnergy(float f, ForgeDirection input) {
		this.inputDirection = input.getOpposite();
		return this.storage.addEnergy(f, true);
	}

}