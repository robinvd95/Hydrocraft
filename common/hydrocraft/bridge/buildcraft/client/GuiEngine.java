package hydrocraft.bridge.buildcraft.client;

import hydrocraft.bridge.buildcraft.ContainerEngine;
import hydrocraft.bridge.buildcraft.TileHydrocraftEngine;
import hydrocraft.inventory.ContainerElectrostaticGenerator;
import hydrocraft.tileentity.TileEntityElectrostaticGenerator;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class GuiEngine extends GuiContainer{

private final TileHydrocraftEngine inventory;
	
	public GuiEngine(InventoryPlayer par1, TileHydrocraftEngine par2) {
		super(new ContainerEngine(par1, par2));
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
		this.mc.renderEngine.bindTexture("/mods/hydrocraft/buildcraft/textures/gui/engine.png");
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	}

}
