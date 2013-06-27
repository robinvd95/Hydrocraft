package hydrocraft.bridge.buildcraft.client;

import hydrocraft.bridge.buildcraft.CommonProxy;
import hydrocraft.bridge.buildcraft.TileHydrocraftEngine;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerRenderInformation(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileHydrocraftEngine.class, new RenderEngine());
		
		RenderingRegistry.registerBlockHandler(new RenderInventory());
	}
}
