package hydrocraft.bridge.buildcraft;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;
import buildcraft.core.DefaultProps;
import buildcraft.energy.Engine;
import buildcraft.energy.TileEngine;
import buildcraft.energy.Engine.EnergyStage;

public class EngineMechanical extends EngineHydrocraft{
	
	public EngineMechanical(TileHydrocraftEngine par1){
		super(par1);
	}

	@Override
	public float getBaseProductionPower() {
		return 1f;
	}

	@Override
	public float getMaxTemperature() {
		return 20f;
	}

	@Override
	public boolean canExplode() {
		return false;
	}
}
