package hydrocraft.client.gui;

import org.lwjgl.opengl.GL11;

import hydrocraft.inventory.ContainerCrusher;
import hydrocraft.tileentity.TileEntityCrusher;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiCrusher extends GuiContainer{

	private final TileEntityCrusher inventory;
	
	public GuiCrusher(InventoryPlayer playerInv, TileEntityCrusher inventory) {
		super(new ContainerCrusher(playerInv, inventory));
		this.inventory = inventory;
	}

	public boolean doesGuiPauseGame(){
		return false;
	}

	protected void drawGuiContainerForegroundLayer(int i, int j){
		String s = "Crusher";
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("Inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3){
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/hydrocraft/textures/gui/crusher.png");
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	}

}
