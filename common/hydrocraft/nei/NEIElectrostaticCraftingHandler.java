package hydrocraft.nei;

import hydrocraft.api.recipes.ElectrostaticGeneratorRecipes;
import hydrocraft.api.recipes.ElectrostaticGeneratorRecipes.ElectrostaticGeneratorRecipe;
import hydrocraft.client.gui.GuiElectrostaticGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.ShapedRecipeHandler;

public class NEIElectrostaticCraftingHandler extends ShapedRecipeHandler{

	private ShapedRecipeHandler.CachedShapedRecipe getShape(ElectrostaticGeneratorRecipe recipe) {
		ShapedRecipeHandler.CachedShapedRecipe shape = new ShapedRecipeHandler.CachedShapedRecipe(0, 0, null, recipe.getResult());
		PositionedStack stack = new PositionedStack(recipe.getInput(), 48, 26);
		stack.setMaxSize(1);
		shape.ingredients.add(stack);
		shape.result.relx = 107;
		shape.result.rely = 26;
		return shape;
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for(ElectrostaticGeneratorRecipe recipe: ElectrostaticGeneratorRecipes.getRecipes()) {
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
		return "Electrostatic Generator";
	}

	@Override
	public String getGuiTexture()
	{
		return "/mods/hydrocraft/textures/gui/electrostaticGenerator.png";
	}


	@Override
	public boolean hasOverlay(GuiContainer gui, Container container, int recipe)
	{
		return false;
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for(ElectrostaticGeneratorRecipe recipe: ElectrostaticGeneratorRecipes.getRecipes()) {
				if(NEIClientUtils.areStacksSameTypeCrafting(recipe.getInput(), ingredient)) {
			        this.arecipes.add(getShape(recipe));
			        break;
			}
		}
	}

	

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if(outputId.equals("crafting") && getClass() == NEIElectrostaticCraftingHandler.class) {
			for(ElectrostaticGeneratorRecipe recipe: ElectrostaticGeneratorRecipes.getRecipes()) {
				this.arecipes.add(getShape(recipe));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}
}
