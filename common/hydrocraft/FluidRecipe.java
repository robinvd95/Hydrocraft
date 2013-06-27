package hydrocraft;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FluidRecipe {

	private final int blockID;
	private final int percentage;
	private final FluidType fluidType;
	private final int amount;
	
	public enum FluidType{
		LATEX(10), WATER(Block.waterStill.blockID);
		
		public final int itemID;
		
		private FluidType(int par1){
			this.itemID = par1;
		}
		
		public int getItemID(){
			return this.itemID;
		}
	}
	
	public static List<FluidRecipe> fluidList = new ArrayList();
	
	static{
		fluidList.add(new FluidRecipe(Block.leaves.blockID, 10, FluidType.WATER, 4));
	}
	
	private FluidRecipe(int par1, int par2, FluidType par3, int par4){
		this.blockID = par1;
		this.percentage = par2;
		this.fluidType = par3;
		this.amount = par4;
	}
	
	public int getID(){
		return this.blockID;
	}
	
	public int getPercentage(){
		return this.percentage;
	}
	
	public FluidType getResult(){
		return this.fluidType;
	}
	
	public int getAmount(){
		return this.amount;
	}
	
	public static FluidRecipe getMatchingRecipe(int par1){
		for(FluidRecipe x : fluidList){
			if(x.getID() == par1){
				return x;
			}
		}
		return null;
	}
	
	
}
