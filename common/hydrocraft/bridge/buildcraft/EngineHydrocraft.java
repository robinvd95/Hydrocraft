package hydrocraft.bridge.buildcraft;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public abstract class EngineHydrocraft {

	protected float progress;
	public float temperature;
	public float energy;
	public EngineState engineState = EngineState.BLUE;

	public final TileHydrocraftEngine engineTile;

	protected boolean isGoingDown = false;

	public EngineHydrocraft(TileHydrocraftEngine tile){
		this.engineTile = tile;
	}

	public void updateEngine(){	
		/*this.temperature += 0.1f;
		this.energy += this.engineState.efficiency;

		if(this.progress > 0.5f){
			this.isGoingDown = true;
			engineTile.releaseEnergy();
			this.energy = 0;
		}else if(this.progress < 0f){
			this.isGoingDown = false;
		}

		if(this.isGoingDown){
			this.progress -= this.engineState.downSpeed;
		}else{
			this.progress+= this.engineState.upSpeed;
		}*/
	}

	public void setEngineState(){
		float var1 = this.temperature / this.getMaxTemperature();
		if(var1 < 1){
			this.engineState = EngineState.BLUE;
		}else if(var1 < 2){
			this.engineState = EngineState.GREEN;
		}else if(var1 < 3){
			this.engineState = EngineState.ORANGE;
		}else if(var1 < 4){
			this.engineState = EngineState.RED;
		}else{
			this.explode();
		}
	}

	private float getTemperatureAffinity(){
		return this.temperature / this.getMaxTemperature() + this.getBaseProductionPower();
	}

	public void explode(){

	}

	public float getProgress(){
		return this.progress;
	}

	public abstract float getBaseProductionPower();

	public abstract float getMaxTemperature();

	public abstract boolean canExplode();

}
