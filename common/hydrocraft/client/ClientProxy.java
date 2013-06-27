package hydrocraft.client;

import hydrocraft.BaseHydrocraft;
import hydrocraft.CommonProxy;
import hydrocraft.blocks.BlockGearBox.GearBoxType;
import hydrocraft.blocks.TileEntityPulley;
import hydrocraft.client.renderer.PipeRenderer;
import hydrocraft.client.renderer.RenderCable;
import hydrocraft.client.renderer.RenderElectrolyzer;
import hydrocraft.client.renderer.RenderElectrostaticGenerator;
import hydrocraft.client.renderer.RenderEnergyStorage;
import hydrocraft.client.renderer.RenderExplosive;
import hydrocraft.client.renderer.RenderExtractorPipe;
import hydrocraft.client.renderer.RenderGearBox;
import hydrocraft.client.renderer.RenderGrinder;
import hydrocraft.client.renderer.RenderInserter;
import hydrocraft.client.renderer.RenderInventory;
import hydrocraft.client.renderer.RenderJetCutter;
import hydrocraft.client.renderer.RenderLatexExtractor;
import hydrocraft.client.renderer.RenderPowerConverter;
import hydrocraft.client.renderer.RenderPulley;
import hydrocraft.client.renderer.RenderRewinder;
import hydrocraft.client.renderer.RenderStock;
import hydrocraft.client.renderer.RenderTank;
import hydrocraft.client.renderer.RenderWaterPump;
import hydrocraft.client.renderer.RenderWaterTank;
import hydrocraft.client.renderer.RenderWaterWheel;
import hydrocraft.entity.EntityItemMagnet;
import hydrocraft.items.ItemHydrocraft;
import hydrocraft.tileentity.TileEntityCable;
import hydrocraft.tileentity.TileEntityElectrolyzer;
import hydrocraft.tileentity.TileEntityElectrostaticGenerator;
import hydrocraft.tileentity.TileEntityEnergyStorage;
import hydrocraft.tileentity.TileEntityExplosive;
import hydrocraft.tileentity.TileEntityExtractor;
import hydrocraft.tileentity.TileEntityFluidExtractor;
import hydrocraft.tileentity.TileEntityGearBox;
import hydrocraft.tileentity.TileEntityGrinder;
import hydrocraft.tileentity.TileEntityInserter;
import hydrocraft.tileentity.TileEntityIronGearBox;
import hydrocraft.tileentity.TileEntityJetCutter;
import hydrocraft.tileentity.TileEntityPowerConverter;
import hydrocraft.tileentity.TileEntityRewinder;
import hydrocraft.tileentity.TileEntityStock;
import hydrocraft.tileentity.TileEntityTank;
import hydrocraft.tileentity.TileEntityWaterPump;
import hydrocraft.tileentity.TileEntityWaterTank;
import hydrocraft.tileentity.TileEntityWaterWheel;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraftforge.client.IItemRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ClientProxy extends CommonProxy{

	public static final String PATH_MODEL_TEXTURE = "/mods/hydrocraft/textures/models/";
	
	public static int BLOCK_RENDER_ID;
	public static int PIPE_RENDER_ID;
	public static int GLASS_RENDER_ID;
	
	private IItemRenderer pipeRenderer = new PipeItemRenderer();
	
	public void registerRenderInformation(){
		
		BLOCK_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
		PIPE_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWaterPump.class, new RenderWaterPump());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWaterWheel.class, new RenderWaterWheel());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearBox.class, new RenderGearBox(GearBoxType.WOOD));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIronGearBox.class, new RenderGearBox(GearBoxType.IRON));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJetCutter.class, new RenderJetCutter());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStock.class, new RenderStock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPowerConverter.class, new RenderPowerConverter());
	//	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new RenderPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new RenderCable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergyStorage.class, new RenderEnergyStorage());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidExtractor.class, new RenderLatexExtractor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElectrostaticGenerator.class, new RenderElectrostaticGenerator());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrinder.class, new RenderGrinder());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRewinder.class, new RenderRewinder());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPulley.class, new RenderPulley());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWaterTank.class, new RenderWaterTank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElectrolyzer.class, new RenderElectrolyzer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExplosive.class, new RenderExplosive("/mods/hydrocraft/textures/models/hydrogenBomb.png"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInserter.class, new RenderInserter());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTank.class, new RenderTank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtractor.class, new RenderExtractorPipe());
		
		
		RenderingRegistry.registerBlockHandler(new PipeRenderer());
		//MinecraftForgeClient.registerItemRenderer(BuildCraftTransport.pipeItemsWood.itemID, pipeRenderer);
		
		RenderingRegistry.registerBlockHandler(new RenderInventory());
		RenderingRegistry.registerEntityRenderingHandler(EntityItemMagnet.class, new RenderItem());
		AddNames();
	}

	private void AddNames() {
		//LanguageRegistry.addName(BaseHydrocraft.copperOreBlock, "Copper Ore");
		LanguageRegistry.addName(BaseHydrocraft.electricityCableBlock, "Insulated Cable");
		LanguageRegistry.addName(BaseHydrocraft.electroMagnetBlock, "Magnet Block");
		LanguageRegistry.addName(BaseHydrocraft.electrostaticGeneratorBlock, "Electrostatic Generator");
		LanguageRegistry.addName(BaseHydrocraft.energyStorageBlock, "Electric Storage Unit P02");
		LanguageRegistry.addName(BaseHydrocraft.gearBoxBlock, "Wooden Gear Box");
		LanguageRegistry.addName(BaseHydrocraft.ironGearBoxBlock, "Iron Gear Box");
		LanguageRegistry.addName(BaseHydrocraft.grinderBlock, "Stone Grinder");
		LanguageRegistry.addName(BaseHydrocraft.fluidExtractorBlock, "Fluid Extractor");
		LanguageRegistry.addName(BaseHydrocraft.powerConverterBlock, "Electric Generator");
		LanguageRegistry.addName(BaseHydrocraft.stockBlock, "Wooden Stock");
		LanguageRegistry.addName(BaseHydrocraft.waterPipeBlock, "Water Pipe");
		LanguageRegistry.addName(BaseHydrocraft.waterPumpBlock, "Water Pump");
		LanguageRegistry.addName(BaseHydrocraft.waterTankBlock, "Water Tank");
		LanguageRegistry.addName(BaseHydrocraft.waterWheelBlock, "Water Wheel");
		//LanguageRegistry.addName(BaseHydrocraft.lithiumOreBlock, "Lithium Ore");
		LanguageRegistry.addName(BaseHydrocraft.mechanicalRewinderBlock, "Mechanical Rewinder");
		//LanguageRegistry.addName(ItemHydrocraft.copperIngot, "Copper Ingot");
		LanguageRegistry.addName(ItemHydrocraft.electroMagnetItem, "Magnet");
		LanguageRegistry.addName(ItemHydrocraft.gearItem, "Gear");
		LanguageRegistry.addName(ItemHydrocraft.latexBucketItem, "Bucket of Latex");
		//LanguageRegistry.addName(ItemHydrocraft.rubberItem, "Rubber");
		LanguageRegistry.addName(ItemHydrocraft.wrench, "Plumber's Wrench");
		LanguageRegistry.addName(ItemHydrocraft.lithiumIngot, "Lithium Ingot");
		LanguageRegistry.addName(ItemHydrocraft.flourItem, "Flour");
		LanguageRegistry.addName(ItemHydrocraft.mechanicalBattery, "Mechanic Motor");
		//LanguageRegistry.addName(ItemHydrocraft.springItem, "Spring");
		LanguageRegistry.addName(BaseHydrocraft.chainBlock, "Chain");
		LanguageRegistry.addName(ItemHydrocraft.chainsawItem, "Mechanical Chainsaw");
	}
}
