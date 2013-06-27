package hydrocraft.client.gui;

import org.lwjgl.opengl.GL11;

import hydrocraft.inventory.ContainerElectrostaticGenerator;
import hydrocraft.tileentity.TileEntityElectrostaticGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class GuiElectrostaticGenerator extends GuiContainer{

	private final TileEntityElectrostaticGenerator inventory;
	
	public GuiElectrostaticGenerator(InventoryPlayer par1, TileEntityElectrostaticGenerator par2) {
		super(new ContainerElectrostaticGenerator(par1, par2));
		this.inventory = par2;
	}
	
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	protected void drawGuiContainerForegroundLayer(int i, int j){
		String s = "Electrostatic Generator";
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("Inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3){
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/hydrocraft/textures/gui/electrostaticGenerator.png");
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        
        int var1 = this.inventory.getChargeScaled(79);
        this.drawTexturedModalRect(var5+53, var6+61, 176, 0, var1, 5);
        
        var1 = this.inventory.getProgressScaled(24);
        this.drawTexturedModalRect(var5+77, var6+36, 176, 5, var1, 17);
	}
}
