package hydrocraft.bridge.buildcraft;

import buildcraft.BuildCraftSilicon;
import hydrocraft.items.ItemHydrocraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "Hydrocraft_Bridge_BC", name = "HC Crossover: Buildcraft", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, versionBounds = "[1.0.0]")
//channels=(PacketHandler.CHANNEL), packetHandler = PacketHandler.class)
public class HydrocraftBridgeBC {

	private GuiHandler guiHandler = new GuiHandler();
	
	@Instance("Hydrocraft_Bridge_BC")
	public static HydrocraftBridgeBC instance;
	
	@SidedProxy(clientSide = "hydrocraft.bridge.buildcraft.client.ClientProxy", serverSide = "hydrocraft.bridge.buildcraft.CommonProxy")
	public static CommonProxy proxy;
	
	public static final int customBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
	
	public static final Block mechanicalEngineBlock = new BlockMechanicalEngine(3625, Material.iron);
	
	public static final Item trunkUpgradeOrangeItem = new ItemUpgrade(25680, "upgradeTrunkOrange", 0, 0).setUnlocalizedName("trunkUpgradeOrangeItem");
	public static final Item trunkUpgradeRedItem = new ItemUpgrade(25681, "upgradeTrunkRed", 0 , 1).setUnlocalizedName("trunkUpgradeRedItem");
	public static final Item pistonUpgradeStoneItem = new ItemUpgrade(25682, "upgradePistonStone", 1, 0).setUnlocalizedName("pistonUpgradeStone");
	
	@Init
	public void load(FMLInitializationEvent evt){
		proxy.registerRenderInformation();
		GameRegistry.registerBlock(mechanicalEngineBlock, "hydrobridgebc.mechanicalEngine");
		GameRegistry.registerTileEntity(TileHydrocraftEngine.class, "hcbridgebc.hydrocraftEngine");
		NetworkRegistry.instance().registerGuiHandler(instance, this.guiHandler);
		
		
		GameRegistry.addRecipe(new ItemStack(this.mechanicalEngineBlock), new Object[]{
			"XXX", " % ", "#@#",
			'X', Block.planks,
			'%', Block.glass,
			'#', ItemHydrocraft.gearItem,
			'@', Block.pistonBase
		});
		
		GameRegistry.addRecipe(new ItemStack(this.trunkUpgradeOrangeItem), new Object[]{
			" X ", "X%X", " X ",
			'X', Item.ingotIron,
			'%', new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 1)
		});
		
		GameRegistry.addRecipe(new ItemStack(this.trunkUpgradeRedItem), new Object[]{
			" X ", "X%X", " X ",
			'X', Item.ingotIron,
			'%', new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 2)
		});
	}
}