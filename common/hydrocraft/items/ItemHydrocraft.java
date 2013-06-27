package hydrocraft.items;

import hydrocraft.BaseHydrocraft;
import hydrocraft.items.ItemBattery.BatteryType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemHydrocraft extends Item{

	public static final Item displayItem = new ItemHydrocraft(4720, "displayItem").disableCreativeTab().setUnlocalizedName("displayItemHydro");
	public static final Item gearItem = new ItemHydrocraft(4721, "gear").setUnlocalizedName("hydrocraft.gear");
	public static final Item electroMagnetItem = new ItemHydrocraft(4722, "electroMagnet").setUnlocalizedName("hydrocraft.electroMagnet");
	public static final Item latexBucketItem = new ItemLatexBucket(4723).setUnlocalizedName("hydrocraft.latexBucket").setContainerItem(Item.bucketEmpty);
	//public static final Item copperIngot = new ItemHydrocraft(4750, "copperIngot").setUnlocalizedName("hydrocraft.copperIngot");
	//public static final Item rubberItem = new ItemHydrocraft(4725, "rubber").setUnlocalizedName("hydrocraft.rubber");
	public static final Item wrench = new ItemWrench(4719).setUnlocalizedName("hydrocraft.wrench").setFull3D();
	public static final Item lithiumIngot = new ItemHydrocraft(4751, "lithiumIngot").setUnlocalizedName("hydrocraft.lithiumIngot");
	public static final Item flourItem = new ItemHydrocraft(4726, "flour").setUnlocalizedName("hydrocraft.flourItem");
	//public static final Item springItem = new ItemHydrocraft(4727, "spring").setUnlocalizedName("hydrocraft.springItem");
	public static final Item mechanicalBattery = new ItemMechanicalEngine(4728, "mechanicalMotor").setUnlocalizedName("hydrocraft.mechanicalEngine").setMaxDamage(20000).setMaxStackSize(1);
	public static final Item chainsawItem = new ItemChainsaw(4780).setUnlocalizedName("hydrocraft.chainsawItem");
	public static final Item drillItem = new ItemDrill(4781).setUnlocalizedName("hydrocraft.drillItem");
	public static final Item batteryItem = new ItemBattery(4740, BatteryType.LITHIUM).setUnlocalizedName("hydrocraft.batteryItem");
	public static final Item ingotsItem = new ItemIngots(4802);
	public static final Item materialsItem = new ItemMaterials(4803);
	public static final Item dustItems = new ItemDusts(4804);
	
	protected String iconName;
	
	public ItemHydrocraft(int par1, String par2) {
		super(par1);
		this.iconName = par2;
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}
	
	public ItemHydrocraft disableCreativeTab(){
		this.setCreativeTab(null);
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister){
		this.itemIcon = par1IconRegister.registerIcon("hydrocraft:"+this.iconName);
    }
}
