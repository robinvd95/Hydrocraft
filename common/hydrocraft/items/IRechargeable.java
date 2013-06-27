package hydrocraft.items;

import hydrocraft.EnumPowerType;

public interface IRechargeable {

	float chargeEffectiveness();
	EnumPowerType requiredPower();
}
