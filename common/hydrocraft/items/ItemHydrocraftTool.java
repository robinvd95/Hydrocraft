package hydrocraft.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemHydrocraftTool extends ItemHydrocraft{

	public enum ToolType{
		MECHANICAL(10f, 2, 200);

		public float efficiency;
		public int damageVsEntity;
		public int maxUses;

		private ToolType(float par1, int par2, int par3){
			this.efficiency = par1;
			this.damageVsEntity = par2;
			this.maxUses = par3;
		}

		public int getHarvestLevel(){
			return 3;
		}

	}

	private Block[] blocksEffectiveAgainst;
	public float efficiencyOnProperMaterial = 4.0F;

	public int damageVsEntity;

	protected ToolType toolMaterial;

	protected ItemHydrocraftTool(int par1, String par2, ToolType par3EnumToolMaterial, Block[] par4ArrayOfBlock)
	{
		super(par1, par2);
		this.toolMaterial = par3EnumToolMaterial;
		this.blocksEffectiveAgainst = par4ArrayOfBlock;
		this.maxStackSize = 1;
		this.setMaxDamage(par3EnumToolMaterial.maxUses);
		this.efficiencyOnProperMaterial = par3EnumToolMaterial.efficiency;
		this.damageVsEntity = 2;
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block){
		for (int var3 = 0; var3 < this.blocksEffectiveAgainst.length; ++var3){
			if (this.blocksEffectiveAgainst[var3] == par2Block){
				return this.efficiencyOnProperMaterial;
			}
		}

		return 1.0F;
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving){
		return false;
	}

	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving){
		if ((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D && par1ItemStack.getItemDamage() < this.toolMaterial.maxUses){
			par1ItemStack.damageItem(1, par7EntityLiving);
		}

		return true;
	}

	public int getDamageVsEntity(Entity par1Entity){
		return this.damageVsEntity;
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	public int getItemEnchantability(){
		return 0;
	}

	public String getToolMaterialName(){
		return this.toolMaterial.toString();
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack){
		return false;
	}

	/** FORGE: Overridden to allow custom tool effectiveness */
	@Override
	public float getStrVsBlock(ItemStack stack, Block block, int meta) {
		if(stack.getItemDamage() < this.toolMaterial.maxUses){
			if (ForgeHooks.isToolEffective(stack, block, meta)){
				return efficiencyOnProperMaterial;
			}
			return getStrVsBlock(stack, block);
		}else{
			return 1.0f;
		}
	}
}