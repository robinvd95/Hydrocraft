package hydrocraft.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityItemMagnet extends EntityItem{

	public boolean isBeingPulled;
	
	public EntityItemMagnet(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack) {
		super(par1World, par2, par4, par6, par8ItemStack);
	}
	
	public void onUpdate(){
		super.onUpdate();
		this.isBeingPulled = false;
	}

}
