package hydrocraft.nei;

import hydrocraft.api.recipes.CrusherRecipes;
import hydrocraft.api.recipes.CrusherRecipes.CrusherRecipe;
import hydrocraft.api.recipes.CrusherRecipes;
import hydrocraft.api.recipes.CrusherRecipes.CrusherRecipe;
import hydrocraft.client.gui.GuiElectrostaticGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.ShapedRecipeHandler;

public class NEICrusherCraftingHandler extends ShapedRecipeHandler{

	private ShapedRecipeHandler.CachedShapedRecipe getShape(CrusherRecipe recipe) {
		ShapedRecipeHandler.CachedShapedRecipe shape = new ShapedRecipeHandler.CachedShapedRecipe(0, 0, null, recipe.getResult());

		int xPos = 30;
		int yPos = 17;
		
		for(int x = 0; x < 2; x++)
		{
			for(int y = 0; y < 2; y++)
			{
				if(recipe.getInput()[y*2 + x] == null)
				{
					continue;
				}
				PositionedStack stack = new PositionedStack(recipe.getInput()[y*2 + x], xPos+x*18, yPos+y*18);
				stack.setMaxSize(1);
				shape.ingredients.add(stack);
			}
		}
		if(recipe.getByProduct() != null){
			PositionedStack stack = new PositionedStack(recipe.getByProduct(), 107, 38);
			stack.setMaxSize(1);
			shape.ingredients.add(stack);
		}
		shape.result.relx = 107;
		shape.result.rely = 15;
		return shape;
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for(CrusherRecipe recipe: CrusherRecipes.getRecipes()) {
			if(NEIClientUtils.areStacksSameTypeCrafting(recipe.getResult(), result)) {
				this.arecipes.add(getShape(recipe));
			}
		}
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass()
	{
		return GuiElectrostaticGenerator.class;
	}

	@Override
	public String getRecipeName()
	{
		return "Crusher Recipes";
	}

	@Override
	public String getGuiTexture()
	{
		return "/mods/hydrocraft/textures/gui/crusher.png";
	}


	@Override
	public boolean hasOverlay(GuiContainer gui, Container container, int recipe)
	{
		return false;
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for(CrusherRecipe recipe: CrusherRecipes.getRecipes()) {
			for(ItemStack source : recipe.getInput()){
				if(NEIClientUtils.areStacksSameTypeCrafting(source, ingredient)) {
					this.arecipes.add(getShape(recipe));
					break;
				}
			}
		}
	}



	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if(outputId.equals("crafting") && getClass() == NEICrusherCraftingHandler.class) {
			for(CrusherRecipe recipe: CrusherRecipes.getRecipes()) {
				this.arecipes.add(getShape(recipe));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}
}
