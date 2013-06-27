package hydrocraft.items;

import hydrocraft.api.IWhackable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWrench extends ItemHydrocraft{

	public ItemWrench(int par1) {
		super(par1, "wrench");
		this.setMaxStackSize(1);
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
		if(!par2EntityPlayer.isSneaking()){
			return false;
		}
		int var1 = par3World.getBlockId(par4, par5, par6);
		Block var2 = Block.blocksList[var1];
		if(var2 != null && var2 instanceof IWhackable){
			IWhackable var3 = (IWhackable) var2;
			return var3.whack(par3World, par4, par5, par6);
		}
		return false;
	}

}
