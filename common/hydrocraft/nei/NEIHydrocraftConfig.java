package hydrocraft.nei;

import hydrocraft.BaseHydrocraft;
import codechicken.nei.MultiItemRange;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.Mod;

public class NEIHydrocraftConfig implements IConfigureNEI{

	@Override
	public void loadConfig() {
		MultiItemRange main = new MultiItemRange();
		main.add(BaseHydrocraft.tankBlock.blockID, 1, 6);
		
		API.registerRecipeHandler(new NEIElectrostaticCraftingHandler());
		API.registerRecipeHandler(new NEICrusherCraftingHandler());
	}

	@Override
	public String getName() {
		return BaseHydrocraft.class.getAnnotation(Mod.class).name();
	}

	@Override
	public String getVersion() {
		return BaseHydrocraft.class.getAnnotation(Mod.class).version();
	}
}