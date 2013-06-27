package hydrocraft.api.recipes;

import hydrocraft.BaseHydrocraft;
import hydrocraft.items.ItemHydrocraft;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrusherRecipes{

	public static List<CrusherRecipe> recipes = new ArrayList<CrusherRecipe>();

	public static void addRecipes(){
		recipes.add(new CrusherRecipe(new ItemStack[]{
				new ItemStack(ItemHydrocraft.ingotsItem, 1, 2),
				new ItemStack(ItemHydrocraft.ingotsItem, 1, 2),
				new ItemStack(ItemHydrocraft.ingotsItem, 1, 2),
				new ItemStack(ItemHydrocraft.ingotsItem, 1, 2),
		}, new ItemStack(ItemHydrocraft.materialsItem, 4, 1), null));

		recipes.add(new CrusherRecipe(new ItemStack[]{
				new ItemStack(Item.ingotIron),
				new ItemStack(Item.ingotIron),
				new ItemStack(Item.ingotIron),
				new ItemStack(Item.ingotIron),
		}, new ItemStack(ItemHydrocraft.materialsItem, 4, 3), null));

		recipes.add(new CrusherRecipe(new ItemStack[]{
				new ItemStack(Block.oreIron),
				null,
				null,
				null

		}, new ItemStack(ItemHydrocraft.dustItems, 2, 1), new ItemStack(ItemHydrocraft.dustItems, 1, 3)));

		recipes.add(new CrusherRecipe(new ItemStack[]{
				new ItemStack(BaseHydrocraft.oreBlocks, 1, 2),
				null,
				null,
				null,
		}, new ItemStack(ItemHydrocraft.dustItems, 2, 5), new ItemStack(ItemHydrocraft.dustItems, 1, 3)));
		
		recipes.add(new CrusherRecipe(new ItemStack[]{
				new ItemStack(BaseHydrocraft.oreBlocks, 1, 0),
				null,
				null,
				null
		}, new ItemStack(ItemHydrocraft.dustItems, 2, 2), new ItemStack(ItemHydrocraft.dustItems, 1, 3)));
	}

	public static class CrusherRecipe{
		private final ItemStack source[];
		private final ItemStack result;
		private final ItemStack byProduct;

		public CrusherRecipe(ItemStack[] input, ItemStack output, ItemStack byProduct){
			this.source = input;
			this.result = output;
			this.byProduct = byProduct;
		}

		public ItemStack[] getInput(){
			return this.source;
		}

		public ItemStack getResult(){
			return this.result;
		}

		public ItemStack getByProduct(){
			return this.byProduct;
		}
	}

	public static List<CrusherRecipe> getRecipes() {
		return recipes;
	}

	public static CrusherRecipe compareRecipe(ItemStack[] input){
		for(CrusherRecipe recipe : recipes){
			if(isRecipeEqual(recipe, input)){
				return recipe;
			}
		}
		return null;
	}

	private static boolean isRecipeEqual(CrusherRecipe recipe, ItemStack[] input){
		int i = 0;
		for(ItemStack recipeInput : recipe.getInput()){
			if(!isItemStackEqual(recipeInput, input[i])){
				return false;
			}
			i++;
		}
		return true;
	}

	private static boolean isItemStackEqual(ItemStack stack1, ItemStack stack2){
		if(stack1 == null){
			if(stack2 == null){
				return true;
			}else{
				return false;
			}
		}

		if(stack2 == null){
			return false;
		}

		return stack1.itemID == stack2.itemID && stack1.getItemDamage() == stack1.getItemDamage();
	}

}
