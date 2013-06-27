package hydrocraft.client.gui;

import hydrocraft.BaseHydrocraft;
import hydrocraft.inventory.ContainerFluidExtractor;
import hydrocraft.tileentity.TileEntityElectrostaticGenerator;
import hydrocraft.tileentity.TileEntityFluidExtractor;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.liquids.LiquidStack;

import org.lwjgl.opengl.GL11;

public class GuiFluidExtractor extends GuiContainer{

	private final TileEntityFluidExtractor inventory;
	
	public GuiFluidExtractor(InventoryPlayer par1, TileEntityFluidExtractor var1) {
		super(new ContainerFluidExtractor(par1, var1));
		this.inventory = var1;
	}
	
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	protected void drawGuiContainerForegroundLayer(int i, int j){
    	this.fontRenderer.drawString(StatCollector.translateToLocal("Electrostatic Generator"), 30, +6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("Inventory"), 8, this.ySize - 91, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3){
		drawDefaultBackground();
		int var4 = this.mc.renderEngine.getTexture("/mods/hydrocraft/textures/gui/fluidExtractor.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        
        int var1 = this.inventory.getProgressScaled(24);
        this.drawTexturedModalRect(var5+44, var6+37, 192, 0, var1, 17);
        
        this.displayGauge(var5, var6, 14, 80, this.inventory.getFluidScaled(56), this.inventory.liquid);
	}
	
	private void displayGauge(int j, int k, int line, int col, int squaled, LiquidStack liquid) {
		if (liquid == null)
		{
			return;
		}
		
		int start = 0;

		Icon liquidIcon;
		String textureSheet;
		if(liquid.canonical().getRenderingIcon() != null) {
			textureSheet = liquid.canonical().getTextureSheet();
			liquidIcon = liquid.canonical().getRenderingIcon();
		} else {
			if (liquid.itemID < Block.blocksList.length && Block.blocksList[liquid.itemID].blockID > 0) {
				liquidIcon = Block.blocksList[liquid.itemID].getBlockTextureFromSide(0);
				textureSheet = "/terrain.png";
			} else {
				liquidIcon = Item.itemsList[liquid.itemID].getIconFromDamage(liquid.itemMeta);
				textureSheet = "/gui/items.png";
			}
		}
		mc.renderEngine.bindTexture(textureSheet);

		while (true) {
			int x = 0;

			if (squaled > 16) {
				x = 16;
				squaled -= 16;
			} else {
				x = squaled;
				squaled = 0;
			}

			drawTexturedModelRectFromIcon(j + col, k + line + 58 - x - start, liquidIcon, 16, 16 - (16 - x));
			start = start + 16;

			if (x == 0 || squaled == 0) {
				break;
			}
		}
	}
}
