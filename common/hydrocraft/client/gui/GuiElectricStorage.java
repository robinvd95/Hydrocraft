package hydrocraft.client.gui;

import org.lwjgl.opengl.GL11;

import hydrocraft.inventory.ContainerElectricStorage;
import hydrocraft.tileentity.TileEntityEnergyStorage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiElectricStorage extends GuiContainer{

	private final TileEntityEnergyStorage inventory;
	
	public GuiElectricStorage(InventoryPlayer par1, TileEntityEnergyStorage par2) {
		super(new ContainerElectricStorage(par1, par2));
		this.inventory = par2;
	}
	
	public boolean doesGuiPauseGame(){
		return false;
	}

	protected void drawGuiContainerForegroundLayer(int i, int j){
		String s = "Electric Storage";
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 5, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("Inventory"), 8, this.ySize - 96 + 3, 4210752);
	}

	
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3){
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/hydrocraft/textures/gui/electricStorage.png");
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        
        int var1 = this.inventory.getElectricityScaled(52);
        this.drawTexturedModalRect(var5+57, var6+18+52-var1, 176, 52-var1, 16, var1);
	}

}
