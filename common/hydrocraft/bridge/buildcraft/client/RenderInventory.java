package hydrocraft.bridge.buildcraft.client;

import hydrocraft.api.utils.Utils;
import hydrocraft.bridge.buildcraft.HydrocraftBridgeBC;
import hydrocraft.bridge.buildcraft.IInventoryRenderer;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderInventory implements ISimpleBlockRenderingHandler {
	public static RenderInventory instance = new RenderInventory();


	public static HashMap<Integer, IInventoryRenderer> map = new HashMap<Integer, IInventoryRenderer>();
	
	static{
		map.put(HydrocraftBridgeBC.customBlockRenderID, new RenderEngine());
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if(block.getRenderType() == HydrocraftBridgeBC.customBlockRenderID){
			int var1 = HydrocraftBridgeBC.customBlockRenderID;
			if(map.containsKey(new Integer(HydrocraftBridgeBC.customBlockRenderID))){
				IInventoryRenderer var2 = map.get(var1);
				var2.renderInventory();
			}
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return HydrocraftBridgeBC.customBlockRenderID;
	}
}