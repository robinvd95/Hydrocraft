package hydrocraft.items;

import hydrocraft.EnumPowerType;

public class ItemMechanicalEngine extends ItemHydrocraft implements IRechargeable{

	public ItemMechanicalEngine(int par1, String par2) {
		super(par1, par2);
		this.setMaxDamage(5000);
		this.setMaxStackSize(1);
	}

	@Override
	public float chargeEffectiveness() {
		return 1;
	}

	@Override
	public EnumPowerType requiredPower() {
		return EnumPowerType.MECHANICAL;
	}

}
