package hydrocraft;

import hydrocraft.items.ItemHydrocraft;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GrinderRecipe {
	
	private ItemStack ingredient;
	private int grindStoneTier;
	private ItemStack result;
	
	public static List<GrinderRecipe> recipeList = new ArrayList();
	
	static{
		recipeList.add(new GrinderRecipe(new ItemStack(Item.wheat), 0, new ItemStack(ItemHydrocraft.flourItem)));
		recipeList.add(new GrinderRecipe(new ItemStack(Item.potato), 0, new ItemStack(ItemHydrocraft.flourItem)));
	}
	
	private GrinderRecipe(ItemStack par1, int par2, ItemStack par3){
		this.ingredient = par1;
		this.grindStoneTier = par2;
		this.result = par3;
	}
	
	public static GrinderRecipe getMatchingRecipe(ItemStack par1, int par2){
		if(par1 == null){
			return null;
		}
		for(GrinderRecipe x : recipeList){
			if(doesItemStackMatch(par1, x.getIngredient())){
				return x;
			}
		}
		return null;
	}
	
	public ItemStack getIngredient(){
		return this.ingredient;
	}
	
	public int getStoneTier(){
		return this.grindStoneTier;
	}
	
	public ItemStack getResult(){
		return this.result;
	}
	
	private static boolean doesItemStackMatch(ItemStack par1, ItemStack par2){
		if(par1.itemID == par2.itemID && par1.getItemDamage() == par2.getItemDamage()){
			return true;
		}
		return false;
	}
}
