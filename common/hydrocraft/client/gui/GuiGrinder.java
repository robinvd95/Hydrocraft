package hydrocraft.client.gui;

import hydrocraft.inventory.ContainerElectrostaticGenerator;
import hydrocraft.inventory.ContainerGrinder;
import hydrocraft.tileentity.TileEntityElectrostaticGenerator;
import hydrocraft.tileentity.TileEntityGrinder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiGrinder extends GuiContainer{

	private final TileEntityGrinder inventory;

	public GuiGrinder(InventoryPlayer par1, TileEntityGrinder par2) {
		super(new ContainerGrinder(par1, par2));
		this.inventory = par2;
	}

	public boolean doesGuiPauseGame(){
		return false;
	}

	protected void drawGuiContainerForegroundLayer(int i, int j){
		String s = "Grinder";
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("Inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3){
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/hydrocraft/textures/gui/grinder.png");
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		
		int var1 = this.inventory.getProgressScaled(24);
        this.drawTexturedModalRect(var5+77, var6+36, 176, 0, var1, 17);
	}
}
