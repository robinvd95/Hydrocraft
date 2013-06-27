package hydrocraft.items;

import net.minecraft.item.ItemBlock;

public class ItemBlocks extends ItemBlock{

	public ItemBlocks(int par1) {
		super(par1);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int meta){
		return meta;
	}

}
