package hydrocraft.client.gui;

import hydrocraft.inventory.ContainerElectrostaticGenerator;
import hydrocraft.inventory.ContainerFurnace;
import hydrocraft.tileentity.TileEntityElectrostaticGenerator;
import hydrocraft.tileentity.TileEntityFurnace;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.liquids.LiquidStack;

public class GuiFurnace extends GuiContainer{

	private final TileEntityFurnace inventory;

	public GuiFurnace(InventoryPlayer par1, TileEntityFurnace par2) {
		super(new ContainerFurnace(par1, par2));
		this.inventory = par2;
	}
	
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	protected void drawGuiContainerForegroundLayer(int i, int j){
		String s = "Furnace";
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("Inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3){
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/hydrocraft/textures/gui/furnace.png");
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var1;
        var1 = this.inventory.targetSlot;
        if(var1 > -1){
        	this.drawTexturedModalRect(var5+52, var6+15 + (var1 * 18), 208, 32, 18, 18);
        }
        this.displayGauge(var5, var6, 14, 144, this.inventory.getScaledFluid(63), this.inventory.getLiquidStack());
        
        this.mc.renderEngine.bindTexture("/mods/hydrocraft/textures/gui/furnace.png");
        var1 = this.inventory.getElectricityScaled(52);
        this.drawTexturedModalRect(var5+16, var6+16+52-var1, 176, 0, 16, var1);
        
        this.drawTexturedModalRect(var5+144, var6+12, 192, 0, 16, 65);
        
        var1 = this.inventory.getScaledProgress(24);
        this.drawTexturedModalRect(var5+77, var6+33, 208, 16, var1, 16);
        
        var1 = this.inventory.getScaledBurnTime(16);
        this.drawTexturedModalRect(var5+79, var6+52+16-var1, 208, 16-var1, 16, var1);
	}
	
	private void displayGauge(int j, int k, int line, int col, int squaled, LiquidStack liquid) {
		if (liquid == null){
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

			drawTexturedModelRectFromIcon(j + col, k + line + 61 - x - start, liquidIcon, 16, 16 - (16 - x));
			start = start + 16;

			if (x == 0 || squaled == 0) {
				break;
			}
		}
	}

}
