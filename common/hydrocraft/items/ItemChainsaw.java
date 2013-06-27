package hydrocraft.items;

import hydrocraft.EnumPowerType;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;

public class ItemChainsaw extends ItemHydrocraftTool implements IRechargeable{

	public static final Block[] blocksEffectiveAgainst = new Block[] {Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.pumpkin, Block.pumpkinLantern};
	
	public ItemChainsaw(int par1) {
		super(par1, "chainsaw", ToolType.MECHANICAL, blocksEffectiveAgainst);
	}

    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return par2Block != null && (par2Block.blockMaterial == Material.wood || par2Block.blockMaterial == Material.plants || par2Block.blockMaterial == Material.vine) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
    }

	@Override
	public float chargeEffectiveness() {
		return 0.1f;
	}

	@Override
	public EnumPowerType requiredPower() {
		return EnumPowerType.MECHANICAL;
	}
	
}
