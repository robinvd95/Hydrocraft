package hydrocraft.api.recipes;

import hydrocraft.items.ItemHydrocraft;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ElectrostaticGeneratorRecipes{

	public static List<ElectrostaticGeneratorRecipe> recipes = new ArrayList<ElectrostaticGeneratorRecipe>();
	
	public static void addRecipes(){
		recipes.add(new ElectrostaticGeneratorRecipe(new ItemStack(Item.ingotIron), new ItemStack(ItemHydrocraft.electroMagnetItem)));
	}
	
	public static class ElectrostaticGeneratorRecipe{
		private final ItemStack source;
		private final ItemStack result;
		
		public ElectrostaticGeneratorRecipe(ItemStack input, ItemStack output){
			this.source = input;
			this.result = output;
		}
		
		public ItemStack getInput(){
			return this.source;
		}
		
		public ItemStack getResult(){
			return this.result;
		}
		
	}
	
	

	public static List<ElectrostaticGeneratorRecipe> getRecipes() {
		return recipes;
	}
}
