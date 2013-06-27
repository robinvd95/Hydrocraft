package hydrocraft;

import hydrocraft.api.recipes.CrusherRecipes;
import hydrocraft.api.recipes.ElectrostaticGeneratorRecipes;
import hydrocraft.api.recipes.RecipeHandler;
import hydrocraft.blocks.BlockCable;
import hydrocraft.blocks.BlockChain;
import hydrocraft.blocks.BlockDisplay;
import hydrocraft.blocks.BlockElectrolyzer;
import hydrocraft.blocks.BlockElectrostaticGenerator;
import hydrocraft.blocks.BlockEnergyStorage;
import hydrocraft.blocks.BlockExplosive;
import hydrocraft.blocks.BlockExtractorPipe;
import hydrocraft.blocks.BlockFluidExtractor;
import hydrocraft.blocks.BlockFurnace;
import hydrocraft.blocks.BlockGearBox;
import hydrocraft.blocks.BlockGearBox.GearBoxType;
import hydrocraft.blocks.BlockCrusher;
import hydrocraft.blocks.BlockGrinder;
import hydrocraft.blocks.BlockHydraulicPiston;
import hydrocraft.blocks.BlockInserter;
import hydrocraft.blocks.BlockMagnet;
import hydrocraft.blocks.BlockOres;
import hydrocraft.blocks.BlockPipe;
import hydrocraft.blocks.BlockPlasma;
import hydrocraft.blocks.BlockPowerConverter;
import hydrocraft.blocks.BlockPulley;
import hydrocraft.blocks.BlockRewinder;
import hydrocraft.blocks.BlockSpringBox;
import hydrocraft.blocks.BlockStainedGlass;
import hydrocraft.blocks.BlockStock;
import hydrocraft.blocks.BlockTank;
import hydrocraft.blocks.BlockWaterPump;
import hydrocraft.blocks.BlockWaterTank;
import hydrocraft.blocks.BlockWaterWheel;
import hydrocraft.blocks.TileEntityPulley;
import hydrocraft.entity.EntityItemMagnet;
import hydrocraft.items.ItemBlockTank;
import hydrocraft.items.ItemBlocks;
import hydrocraft.items.ItemHydrocraft;
import hydrocraft.packets.PacketBase;
import hydrocraft.packets.PacketHandler;
import hydrocraft.tileentity.TileEntityCable;
import hydrocraft.tileentity.TileEntityElectrolyzer;
import hydrocraft.tileentity.TileEntityElectrostaticGenerator;
import hydrocraft.tileentity.TileEntityEnergyStorage;
import hydrocraft.tileentity.TileEntityExplosive;
import hydrocraft.tileentity.TileEntityExtractor;
import hydrocraft.tileentity.TileEntityFluidExtractor;
import hydrocraft.tileentity.TileEntityFurnace;
import hydrocraft.tileentity.TileEntityGearBox;
import hydrocraft.tileentity.TileEntityGrinder;
import hydrocraft.tileentity.TileEntityInserter;
import hydrocraft.tileentity.TileEntityIronGearBox;
import hydrocraft.tileentity.TileEntityJetCutter;
import hydrocraft.tileentity.TileEntityMagnet;
import hydrocraft.tileentity.TileEntityPipe;
import hydrocraft.tileentity.TileEntityPowerConverter;
import hydrocraft.tileentity.TileEntityRewinder;
import hydrocraft.tileentity.TileEntitySpringBox;
import hydrocraft.tileentity.TileEntityStock;
import hydrocraft.tileentity.TileEntityTank;
import hydrocraft.tileentity.TileEntityTankValve;
import hydrocraft.tileentity.TileEntityWaterPump;
import hydrocraft.tileentity.TileEntityWaterTank;
import hydrocraft.tileentity.TileEntityWaterWheel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "hydrocraft", name = "HydroCraft", version = "1.0.0a")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, versionBounds = "[1.0.0a]",
channels=(PacketBase.CHANNEL), packetHandler = PacketHandler.class)
public class BaseHydrocraft {

	public static CreativeTab hydroCraftTab = new CreativeTab("Hydrocraft");

	private IGuiHandler guiHandler = new GuiHandler();

	@Instance("hydrocraft")
	public static BaseHydrocraft instance;

	@SidedProxy(clientSide = "hydrocraft.client.ClientProxy", serverSide = "hydrocraft.CommonProxy")
	public static CommonProxy proxy;

	public static HashMap<Integer, Integer[]> furnaceInsertionMap = new HashMap();

	/**
	 * 		Block Index:
	 * 		Mechanical Machines: 2340 - 2355
	 * 			Providers: 2340 - 2343
	 * 			Transfer: 2344 - 2345
	 * 			Redirector: 2346 - 2348
	 * 			Machines: 2349 - 2359
	 * 		Electric Machines: 2360 - 2380
	 * 			Generator: 2360 - 2362
	 * 			Cable: 2363
	 * 			Machines: 2364 - 2380
	 * 		Hydraulic Machines: 2381 - 2400
	 * 		Ore's: 2401 - 2405
	 * 		Ore Blocks: 2406 - 2410
	 * 		Other 2411+
	 */

	public static int waterPumpBlockID;
	public static int waterWheelBlockID;
	public static int gearBoxBlockID;
	public static int stockBlockID;
	public static int powerConverterBlockID;
	public static int waterPipeBlockID;
	public static int waterTankBlockID;
	public static int electricityCableBlockID;
	public static int energyStorageBlockID;
	public static int fluidExtractorBlockID;
	public static int electrostaticGeneratorBlockID;
	public static int grinderBlockID;
	public static int oreBlockID;
	public static int electroMagnetBlockID;
	public static int mechanicalRewinderBlockID;
	public static int latexDisplayBlockID;
	public static int ironGearBoxBlockID;
	public static int chainBlockID;
	public static int pulleyBlockID;
	public static int furnaceBlockID;
	public static int electrolyzerBlockID;
	public static int hydraulicPistonBlockID;
	public static int hydrogenBombID;
	public static int plasmaBlockID;
	public static int inserterBlockID;
	public static int extractorPipeBlockID;
	public static int tankBlockID;
	public static int springBoxBlockID;
	public static int crusherBlockID;
	public static int stainedGlassBlockID;

	/**
	 * Mechanical blocks
	 */
	public static Block waterPumpBlock;
	public static Block waterWheelBlock;
	public static Block gearBoxBlock;
	//public static final Block jetCutterBlock = new BlockJetCutter(, Material.rock).setHardness(4.0f).setUnlocalizedName("jetCutterBlock");
	public static Block stockBlock;
	public static Block powerConverterBlock;
	public static Block waterPipeBlock;
	public static Block waterTankBlock;
	public static Block electricityCableBlock;
	public static Block energyStorageBlock;
	public static Block fluidExtractorBlock;
	public static Block electrostaticGeneratorBlock;
	public static Block grinderBlock;
	//public static final Block copperOreBlock = new BlockOre(2401, Material.iron, OreType.COPPER).setUnlocalizedName("copperOreBlock");
	//public static final Block lithiumOreBlock = new BlockOre(2402, Material.iron, OreType.LITHIUM).setUnlocalizedName("lithiumOreBlock");
	public static Block oreBlocks;
	public static Block electroMagnetBlock;
	public static Block mechanicalRewinderBlock;
	public static Block latexDisplayBlock;
	public static Block ironGearBoxBlock;
	public static Block chainBlock;
	public static Block pulleyBlock;
	public static Block furnaceBlock;
	public static Block electrolyzerBlock;
	public static Block hydraulicPistonBlock;
	public static Block hydrogenBombBlock;
	public static Block plasmaBlock;
	public static Block inserterBlock;
	public static Block extractorPipe;
	public static Block tankBlock;
	public static Block crusherBlock;
	public static Block stainedGlassBlock;
	public static Block springBoxBlock;


	private ServerTickHandler serverTickHandler = new ServerTickHandler();

	private void loadBlocks(){
		waterPumpBlock = new BlockWaterPump(waterPumpBlockID, Material.rock).setUnlocalizedName("waterPumpBlock");
		waterWheelBlock = new BlockWaterWheel(waterWheelBlockID, Material.rock).setUnlocalizedName("waterWheelBlock");
		gearBoxBlock = new BlockGearBox(gearBoxBlockID, Material.wood, GearBoxType.WOOD).setUnlocalizedName("gearBoxBlock");
		stockBlock = new BlockStock(stockBlockID, Material.wood).setUnlocalizedName("stockBlock");
		powerConverterBlock = new BlockPowerConverter(powerConverterBlockID, Material.rock).setUnlocalizedName("powerConverterBlock");
		waterPipeBlock = new BlockPipe(waterPipeBlockID, Material.wood).setUnlocalizedName("waterPipeBlock");
		waterTankBlock = new BlockWaterTank(waterTankBlockID, Material.wood).setUnlocalizedName("waterTankBlock");
		electricityCableBlock = new BlockCable(electricityCableBlockID, Material.cloth).setUnlocalizedName("electricityCableBlock");
		energyStorageBlock = new BlockEnergyStorage(energyStorageBlockID, Material.rock).setUnlocalizedName("energyStorageBlock");
		fluidExtractorBlock = new BlockFluidExtractor(fluidExtractorBlockID, Material.rock).setUnlocalizedName("latexExtractorBlock");
		electrostaticGeneratorBlock = new BlockElectrostaticGenerator(electrostaticGeneratorBlockID, Material.rock).setUnlocalizedName("electrostaticGeneratorBlock");
		grinderBlock = new BlockGrinder(grinderBlockID, Material.rock).setUnlocalizedName("grinderBlock");
		oreBlocks = new BlockOres(oreBlockID).setUnlocalizedName("oreBlocks");
		electroMagnetBlock = new BlockMagnet(electroMagnetBlockID, Material.iron).setHardness(4f).setUnlocalizedName("magnetBlock");
		mechanicalRewinderBlock = new BlockRewinder(mechanicalRewinderBlockID, Material.rock).setHardness(2F).setUnlocalizedName("rewinderBlock");
		latexDisplayBlock = new BlockDisplay(latexDisplayBlockID, "latex");
		ironGearBoxBlock = new BlockGearBox(ironGearBoxBlockID, Material.rock, GearBoxType.IRON).setHardness(2f).setUnlocalizedName("ironGearBoxBlock");
		chainBlock = new BlockChain(chainBlockID, Material.rock).setUnlocalizedName("chainBlock");
		pulleyBlock = new BlockPulley(pulleyBlockID, Material.rock).setUnlocalizedName("hydrocraft.pulleyBlock");
		furnaceBlock = new BlockFurnace(furnaceBlockID, Material.rock).setUnlocalizedName("hydrocraft.furnaceBlock");
		electrolyzerBlock = new BlockElectrolyzer(electrolyzerBlockID, Material.rock).setUnlocalizedName("hydrocraft.electrolyzerBlock");
		hydraulicPistonBlock = new BlockHydraulicPiston(hydraulicPistonBlockID, Material.rock).setUnlocalizedName("hydrocraft.hydraulicPiston");
		hydrogenBombBlock = new BlockExplosive(hydrogenBombID).setUnlocalizedName("hydrocraft.hydrogenBombBlock");
		plasmaBlock = new BlockPlasma(plasmaBlockID).setUnlocalizedName("hydrocraft.plasmaBlock");
		inserterBlock = new BlockInserter(inserterBlockID, Material.rock);
		extractorPipe = new BlockExtractorPipe(extractorPipeBlockID, Material.rock);
		tankBlock = new BlockTank(tankBlockID, Material.iron);
		crusherBlock = new BlockCrusher(crusherBlockID, Material.rock);
		stainedGlassBlock = new BlockStainedGlass(stainedGlassBlockID, Material.glass).setStepSound(Block.soundGlassFootstep);
		springBoxBlock = new BlockSpringBox(springBoxBlockID, Material.wood);

		GameRegistry.registerBlock(waterPumpBlock, "waterPumpBlock");
		GameRegistry.registerBlock(waterWheelBlock, "waterWheelBlock");
		GameRegistry.registerBlock(gearBoxBlock, "gearBoxBlock");
		//GameRegistry.registerBlock(lithiumOreBlock, "lithiumOreBlock");
		//GameRegistry.registerBlock(jetCutterBlock, "jetCutterBlock");
		GameRegistry.registerBlock(stockBlock, "stockBlock");
		GameRegistry.registerBlock(powerConverterBlock, "powerConverterBlock");
		GameRegistry.registerBlock(waterPipeBlock, "waterPipeBlock");
		GameRegistry.registerBlock(waterTankBlock, "waterTankBlock");
		GameRegistry.registerBlock(electricityCableBlock, "electricityCableBlock");
		GameRegistry.registerBlock(energyStorageBlock, "energyStorageBlock");
		GameRegistry.registerBlock(fluidExtractorBlock, "latexExtractorBlock");
		GameRegistry.registerBlock(electrostaticGeneratorBlock, "electrostaticGeneratorBlock");
		GameRegistry.registerBlock(grinderBlock, "grinderBlock");
		//GameRegistry.registerBlock(copperOreBlock, "copperBlock");
		GameRegistry.registerBlock(electroMagnetBlock, "hydrocraft.electroMagnetBlock");
		GameRegistry.registerBlock(mechanicalRewinderBlock, "hydrocraft.mechanicalRewinderBlock");
		GameRegistry.registerBlock(ironGearBoxBlock, "hydrocraft.ironGearBocBlock");
		GameRegistry.registerBlock(chainBlock, "hydrocraft.chainBlock");
		GameRegistry.registerBlock(pulleyBlock, "hydrocraft.pulleyBlock");
		GameRegistry.registerBlock(furnaceBlock, "hydrocraft.furnaceBlock");
		GameRegistry.registerBlock(electrolyzerBlock, "hydrocraft.electrolyzerBlock");
		GameRegistry.registerBlock(hydraulicPistonBlock, "hydrocraft.hydraulicPistonBlock");
		GameRegistry.registerBlock(hydrogenBombBlock, "hydrocraft.hydrogenBombBlock");
		GameRegistry.registerBlock(plasmaBlock, "hydrocraft.plasmaBlock");
		GameRegistry.registerBlock(inserterBlock, "hydrocraft.inserterBlock");
		GameRegistry.registerBlock(extractorPipe, "hydrocraft.extractorPipeBlock");
		GameRegistry.registerBlock(springBoxBlock, "hydrocraft.springBoxBlock");
		GameRegistry.registerBlock(crusherBlock, "hydrocraft.crusherBlock");
		GameRegistry.registerBlock(stainedGlassBlock, ItemBlocks.class, "hydrocraft.stainedGlassBlock");
		GameRegistry.registerBlock(tankBlock, ItemBlockTank.class, "hydrocraft.tankBlock");
		GameRegistry.registerBlock(oreBlocks, ItemBlocks.class, "hydrocraft.oreBlocks");
	}

	@PreInit
	public void PreLoad(FMLPreInitializationEvent evt){

		Configuration config = new Configuration(evt.getSuggestedConfigurationFile());

		config.load();
		this.readFromConfig(config);
		config.save();



		Map map = FurnaceRecipes.smelting().getSmeltingList();

		Set keys = map.keySet();

		for (Iterator i = keys.iterator(); i.hasNext();){
			try{
				Integer target = (Integer)i.next();
				furnaceInsertionMap.put(target, new Integer[]{0, 1, 2});
			}catch(ClassCastException e){
				e.printStackTrace();
			}
		}
		//NEIModContainer.plugins.add(new NEIHydrocraftConfig());
	}

	private void readFromConfig(Configuration config) {
		waterPumpBlockID = config.get("Block ID's", "waterPumpBlock", 2354).getInt();
		waterWheelBlockID = config.get("Block ID's", "waterWheelBlock", 2340).getInt();
		gearBoxBlockID = config.get("Block ID's", "gearBoxBlock", 2346).getInt();
		stockBlockID = config.get("Block ID's", "stockBlock", 2344).getInt();
		powerConverterBlockID = config.get("Block ID's", "powerConverterBlock", 2360).getInt();
		waterPipeBlockID = config.get("Block ID's", "waterPipeBlock", 2381).getInt();
		waterTankBlockID = config.get("Block ID's", "waterTankBlock", 2382).getInt();
		electricityCableBlockID = config.get("Block ID's", "electricityCableBlock", 2363).getInt();
		energyStorageBlockID = config.get("Block ID's", "energyStorageBlock", 2364).getInt();
		fluidExtractorBlockID = config.get("Block ID's", "fluidExtractorBlock", 2349).getInt();
		electrostaticGeneratorBlockID = config.get("Block ID's", "electrostaticGeneratorBlock", 2350).getInt();
		grinderBlockID = config.get("Block ID's", "grinderBlock", 2351).getInt();
		oreBlockID = config.get("Block ID's", "oreBlock", 2403).getInt();
		electroMagnetBlockID = config.get("Block ID's", "magnetBlock", 2411).getInt();
		mechanicalRewinderBlockID = config.get("Block ID's", "mechanicalRewinderBlock", 2352).getInt();
		latexDisplayBlockID = config.get("Block ID's", "latexDisplayBlock", 2420).getInt();
		ironGearBoxBlockID = config.get("Block ID's", "ironGearBoxBlock", 2347).getInt();
		chainBlockID = config.get("Block ID's", "chainBlock", 2412).getInt();
		pulleyBlockID = config.get("Block ID's", "pulleyBlock", 2353).getInt();
		furnaceBlockID = config.get("Block ID's", "furnaceBlock", 2365).getInt();
		electrolyzerBlockID = config.get("Block ID's", "electrolyzerBlock", 2366).getInt();
		hydraulicPistonBlockID = config.get("Block ID's", "hydraulicPistonBlock", 2383).getInt();
		hydrogenBombID = config.get("Block ID's", "hydrogenBombBlock", 2430).getInt();
		plasmaBlockID = config.get("Block ID's", "plasmaBlock", 2435).getInt();
		inserterBlockID = config.get("Block ID's", "inserterBlock", 2436).getInt();
		extractorPipeBlockID = config.get("Block ID's", "extractorPipeBlock", 2437).getInt();
		tankBlockID = config.get("Block ID's", "tankBlock", 2538).getInt();
		springBoxBlockID = config.get("Block ID's", "springBoxBlock", 2539).getInt();
		crusherBlockID = config.get("Block ID's", "crusherBlock",2540).getInt();
		stainedGlassBlockID = config.get("Block ID's", "stainedGlassBlock", 2541).getInt();
	}

	@Init
	public void Load(FMLInitializationEvent evt){
		loadBlocks();
		this.registerItems();
		NetworkRegistry.instance().registerGuiHandler(instance, this.guiHandler);
		TickRegistry.registerTickHandler(serverTickHandler, Side.SERVER);
		this.registerTileEntities();
		GameRegistry.registerWorldGenerator(new WorldGenerator());
		try {
			Class.forName("hydrocraft.items.ItemHydrocraft");
			Class.forName("hydrocraft.GrinderRecipe");
			Class.forName("hydrocraft.FluidRecipe");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		this.registerOres();
		this.addRecipes();
		this.addSmelting();
		proxy.registerRenderInformation();

		LiquidDictionary.getOrCreateLiquid("Latex", new LiquidStack(this.latexDisplayBlock, LiquidContainerRegistry.BUCKET_VOLUME));


		/*RecipeHandler.registerRecipeHandler(new CrusherRecipes());
		RecipeHandler.registerRecipeHandler(new ElectrostaticGeneratorRecipes());
		RecipeHandler.registerRecipes();*/
		CrusherRecipes.addRecipes();
		ElectrostaticGeneratorRecipes.addRecipes();
	}

	private void addSmelting() {
		//GameRegistry.addSmelting(this.copperOreBlock.blockID, new ItemStack(ItemHydrocraft.copperIngot), 1f);
		//GameRegistry.addSmelting(this.lithiumOreBlock.blockID, new ItemStack(ItemHydrocraft.lithiumIngot), 1f);
		GameRegistry.addSmelting(ItemHydrocraft.flourItem.itemID, new ItemStack(Item.bread), 1f);
		FurnaceRecipes.smelting().addSmelting(ItemHydrocraft.dustItems.itemID, 1, new ItemStack(Item.ingotIron), 1f);
		FurnaceRecipes.smelting().addSmelting(ItemHydrocraft.dustItems.itemID, 2, new ItemStack(ItemHydrocraft.ingotsItem, 1, 0), 1f);
		FurnaceRecipes.smelting().addSmelting(ItemHydrocraft.dustItems.itemID, 4, new ItemStack(ItemHydrocraft.ingotsItem, 1, 1), 1f);
		FurnaceRecipes.smelting().addSmelting(ItemHydrocraft.dustItems.itemID, 5, new ItemStack(ItemHydrocraft.ingotsItem, 1, 2), 1f);
		for(int i = 0; i < 3; i++){
			FurnaceRecipes.smelting().addSmelting(oreBlocks.blockID, i, new ItemStack(ItemHydrocraft.ingotsItem, 1, i), 1f);
		}

	}

	public void registerItems(){
		//GameRegistry.registerItem(ItemHydrocraft.copperIngot, "hydrocraft.copperIngotItem");
	}

	private void addRecipes() {

		GameRegistry.addRecipe(new ItemStack(ItemHydrocraft.materialsItem, 6, 0), new Object[]{
			"X",
			'X', ItemHydrocraft.latexBucketItem
		});

		GameRegistry.addRecipe(new ItemStack(waterPipeBlock, 16), new Object[]{
			"X#X","X%X","X#X",
			'X', new ItemStack(ItemHydrocraft.ingotsItem, 1, 2),
			'#', new ItemStack(ItemHydrocraft.materialsItem, 1, 0),
			'%', Block.glass
		});

		GameRegistry.addRecipe(new ItemStack(tankBlock, 4, 1), new Object[]{
			"X$X","$%$", "X$X",
			'X', new ItemStack(ItemHydrocraft.materialsItem, 1, 0),
			'$', new ItemStack(ItemHydrocraft.materialsItem, 1, 1),
			'%', Block.stone
		});

		GameRegistry.addRecipe(new ItemStack(tankBlock, 4, 2), new Object[]{
			"X$X","$%$", "X$X",
			'X', new ItemStack(ItemHydrocraft.materialsItem, 1, 0),
			'$', new ItemStack(ItemHydrocraft.materialsItem, 1, 1),
			'%', Block.glass
		});

		GameRegistry.addRecipe(new ItemStack(this.electricityCableBlock, 12), new Object[]{
			"XXX", "###", "XXX",
			'X', new ItemStack(ItemHydrocraft.materialsItem, 1, 0),
			'#', new ItemStack(ItemHydrocraft.ingotsItem, 1, 0)
		});

		GameRegistry.addRecipe(new ItemStack(this.electrostaticGeneratorBlock), new Object[]{
			" X ", "%$%", "@#@",
			'X', this.stockBlock,
			'$', Block.cloth,
			'%', new ItemStack(ItemHydrocraft.materialsItem, 1, 0),
			'@', Block.cobblestone,
			'#', Block.planks
		});

		GameRegistry.addRecipe(new ItemStack(this.powerConverterBlock), new Object[]{
			"XIX", "$%$", "###",
			'I', this.stockBlock,
			'X', new ItemStack(ItemHydrocraft.ingotsItem, 1, 0),
			'$', Block.glass,
			'%', this.electroMagnetBlock,
			'#', Block.stone
		});

		GameRegistry.addRecipe(new ItemStack(this.energyStorageBlock), new Object[]{
			"X#X", "Z%Z", "$%$",
			'X', Block.glass,
			'%', ItemHydrocraft.lithiumIngot,
			'Z', new ItemStack(ItemHydrocraft.ingotsItem, 1, 0),
			'#', this.electricityCableBlock,
			'$', Block.stone
		});

		GameRegistry.addRecipe(new ItemStack(this.stockBlock, 6), new Object[]{
			"X", "#", "X",
			'X', Item.stick,
			'#', ItemHydrocraft.gearItem
		});

		GameRegistry.addRecipe(new ItemStack(ItemHydrocraft.gearItem, 3), new Object[]{
			" X ", "X X", " X ",
			'X', new ItemStack(ItemHydrocraft.ingotsItem, 1, 2)
		});

		GameRegistry.addRecipe(new ItemStack(this.gearBoxBlock), new Object[]{
			"$#$", "#X#", "$#$",
			'X', ItemHydrocraft.gearItem,
			'#', Block.thinGlass,
			'$', Block.planks
		});

		GameRegistry.addRecipe(new ItemStack(this.waterWheelBlock), new Object[]{
			"XXX", "X%X", "XXX",
			'X', Block.planks,
			'%', this.stockBlock
		});

		GameRegistry.addRecipe(new ItemStack(this.electroMagnetBlock), new Object[]{
			"XX", "XX",
			'X', ItemHydrocraft.electroMagnetItem
		});

		GameRegistry.addRecipe(new ItemStack(this.grinderBlock), new Object[]{
			" X ", "%$%", "&#&",
			'X', this.stockBlock,
			'%', ItemHydrocraft.gearItem,
			'$', Block.cobblestone,
			'&', Block.woodSingleSlab,
			'#', Block.stoneSingleSlab
		});

		GameRegistry.addRecipe(new ItemStack(ItemHydrocraft.mechanicalBattery, 1, 20000), new Object[]{
			"X%X", "X$X", "X%X",
			'X', Item.ingotIron,
			'%', new ItemStack(ItemHydrocraft.materialsItem, 1, 2),
			'$', this.stockBlock
		});

		GameRegistry.addRecipe(new ItemStack(ItemHydrocraft.materialsItem, 1, 2), new Object[]{
			"X","X","X",
			'X', Item.ingotIron
		});

		GameRegistry.addRecipe(new ItemStack(this.mechanicalRewinderBlock), new Object[]{
			"XXX", "%#%", "XXX",
			'X', Block.stoneSingleSlab,
			'#', Block.glass,
			'%', ItemHydrocraft.gearItem
		});

		GameRegistry.addRecipe(new ItemStack(this.ironGearBoxBlock), new Object[]{
			" $ ","%#%", " $ ",
			'$', ItemHydrocraft.gearItem,
			'%', new ItemStack(ItemHydrocraft.materialsItem, 1, 3),
			'#', this.gearBoxBlock
		});

		GameRegistry.addRecipe(new ItemStack(this.chainBlock, 4), new Object[]{
			"X","X",
			'X', Item.ingotIron
		});

		GameRegistry.addRecipe(new ItemStack(ItemHydrocraft.chainsawItem), new Object[]{
			" XP", "RPX", "GR ",
			'X', this.chainBlock,
			'P', Item.ingotIron,
			'R', new ItemStack(Item.dyePowder, 1, 1),
			'G', new ItemStack(ItemHydrocraft.mechanicalBattery, 1, 0)
		});

		GameRegistry.addRecipe(new ItemStack(ItemHydrocraft.drillItem), new Object[]{
			" XX", "RGX", "%R ",
			'X', Item.ingotIron,
			'R', new ItemStack(Item.dyePowder, 1, 1),
			'%', new ItemStack(ItemHydrocraft.mechanicalBattery, 1, 0),
			'G', new ItemStack(ItemHydrocraft.gearItem)
		});

		GameRegistry.addRecipe(new ItemStack(this.electrolyzerBlock), new Object[]{
			"@#@"," X ","%$%",
			'X', this.waterTankBlock,
			'$', this.electricityCableBlock,
			'%', new ItemStack(ItemHydrocraft.ingotsItem, 1, 0),
			'@', Block.glass
		});

		GameRegistry.addRecipe(new ItemStack(this.mechanicalRewinderBlock), new Object[]{
			"XXX","%$%", "XXX",
			'X', Block.stoneSingleSlab,
			'%', Block.glass,
			'$', ItemHydrocraft.gearItem
		});
		GameRegistry.addRecipe(new ItemStack(ItemHydrocraft.wrench), new Object[]{
			"X X", " G ", " X ",
			'X', Item.ingotIron,
			'G', ItemHydrocraft.gearItem
		});

		GameRegistry.addRecipe(new ItemStack(this.extractorPipe), new Object[]{
			"X","$","%",
			'X', Item.redstone,
			'$', this.waterPipeBlock,
			'%', Block.pistonBase
		});
	}

	private void registerOres(){

	}

	private void registerTileEntities(){
		GameRegistry.registerTileEntity(MechanicalPowerProvider.class, "hydrocraft.mechanicalPowerProvider");
		GameRegistry.registerTileEntity(TileEntityWaterPump.class, "hydrocraft.waterPump");
		GameRegistry.registerTileEntity(TileEntityWaterWheel.class, "hydrocraft.waterWheel");
		GameRegistry.registerTileEntity(TileEntityGearBox.class, "hydrocraft.gearBox");
		GameRegistry.registerTileEntity(TileEntityJetCutter.class, "hydrocraft.jetCuter");
		GameRegistry.registerTileEntity(TileEntityStock.class, "hydrocraft.stock");
		GameRegistry.registerTileEntity(TileEntityPipe.class, "hydrocraft.waterPipe");
		GameRegistry.registerTileEntity(TileEntityWaterTank.class, "hydrocraft.waterTank");
		GameRegistry.registerTileEntity(TileEntityPowerConverter.class, "hydrocraft.powerConverter");
		GameRegistry.registerTileEntity(TileEntityCable.class, "hydrocraft.electricityCable");
		GameRegistry.registerTileEntity(TileEntityEnergyStorage.class, "hydrocraft.energyStorage");
		GameRegistry.registerTileEntity(TileEntityFluidExtractor.class, "hydrocraft.latexExtractor");
		GameRegistry.registerTileEntity(TileEntityElectrostaticGenerator.class, "hydrocraft.electrostaticGenerator");
		GameRegistry.registerTileEntity(TileEntityGrinder.class, "hydrocraft.grinder");
		GameRegistry.registerTileEntity(TileEntityMagnet.class, "hydrocraft.electroMagnet");
		GameRegistry.registerTileEntity(TileEntityRewinder.class, "hydrocraft.rewinder");
		GameRegistry.registerTileEntity(TileEntityIronGearBox.class, "hydrocraft.ironGearBox");
		GameRegistry.registerTileEntity(TileEntityPulley.class, "hydrocraft.pulley");
		GameRegistry.registerTileEntity(TileEntityFurnace.class, "hydrocraft.tileEntityFurnace");
		GameRegistry.registerTileEntity(TileEntityElectrolyzer.class, "hydrocraft.tileEntityElectrolyzer");
		GameRegistry.registerTileEntity(TileEntityExplosive.class, "hydrocraft.tileEntityExplosive");
		GameRegistry.registerTileEntity(TileEntityInserter.class, "hydrocraft.tileEntityInserter");
		GameRegistry.registerTileEntity(TileEntityTank.class, "hydrocraft.tileEntityTank");
		GameRegistry.registerTileEntity(TileEntityTankValve.class, "hydrocraft.tileEntityTankValve");
		GameRegistry.registerTileEntity(TileEntityExtractor.class, "hydrocraft.tileEntityExtractor");
		GameRegistry.registerTileEntity(TileEntitySpringBox.class, "hydrocraft.tileEntitySpringBox");

		EntityRegistry.registerModEntity(EntityItemMagnet.class, "hydrocraft.magnetItem", 1, this, 16, 3, true);
	}

	@PostInit
	public void postLoad(FMLPostInitializationEvent evt){

	}

}