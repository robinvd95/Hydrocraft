package hydrocraft.client.renderer;

import hydrocraft.BaseHydrocraft;
import hydrocraft.ISpecialInventoryRenderer;
import hydrocraft.blocks.BlockGearBox.GearBoxType;
import hydrocraft.client.ClientProxy;
import hydrocraft.client.IInventoryRenderer;
import hydrocraft.client.models.ModelGearBox;
import hydrocraft.client.models.ModelHydrocraft;
import hydrocraft.client.models.ModelPowerConverter;
import hydrocraft.client.models.ModelStock;
import hydrocraft.client.models.ModelWaterPump;
import hydrocraft.client.models.ModelWaterWheel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderInventory implements ISimpleBlockRenderingHandler {

	public static HashMap<Integer, IInventoryRenderer> map = new HashMap<Integer, IInventoryRenderer>();

	static{
		map.put(BaseHydrocraft.waterPumpBlock.blockID, new RenderWaterPump());
		map.put(BaseHydrocraft.stockBlock.blockID, new RenderStock());
		map.put(BaseHydrocraft.gearBoxBlock.blockID, new RenderGearBox(GearBoxType.WOOD));
		map.put(BaseHydrocraft.ironGearBoxBlock.blockID, new RenderGearBox(GearBoxType.IRON));
		map.put(BaseHydrocraft.waterWheelBlock.blockID, new RenderWaterWheel());
		map.put(BaseHydrocraft.inserterBlock.blockID, new RenderInserter());
		map.put(BaseHydrocraft.extractorPipe.blockID, new RenderExtractorPipe());
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if(modelID == ClientProxy.BLOCK_RENDER_ID){
			if(map.containsKey(new Integer(block.blockID))){
				IInventoryRenderer var2 = map.get(block.blockID);
				var2.renderInventory();
			}
		}
	}
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	public boolean shouldRender3DInInventory() {
		return true;
	}

	public int getRenderId() {
		return ClientProxy.BLOCK_RENDER_ID;
	}
}