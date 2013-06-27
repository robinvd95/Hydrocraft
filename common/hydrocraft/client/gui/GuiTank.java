package hydrocraft.client.gui;

import hydrocraft.api.utils.Utils;
import hydrocraft.inventory.ContainerTank;
import hydrocraft.tileentity.TileEntityTank;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

import org.lwjgl.opengl.GL11;

public class GuiTank extends GuiContainer{

	private final TileEntityTank inventory;
	
	public GuiTank(InventoryPlayer invPlayer, TileEntityTank tank) {
		super(new ContainerTank(invPlayer, tank));
		this.inventory = tank;
		Utils.printLine("x: "+inventory.xCoord + ", y :"+inventory.yCoord +", z: "+inventory.zCoord);
	}

	public boolean doesGuiPauseGame(){
		return false;
	}

	protected void drawGuiContainerForegroundLayer(int i, int j){
		
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		
		String s = "Tank";
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("Inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3){
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/hydrocraft/textures/gui/tank.png");
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		
		LiquidTank tank = this.inventory.getLiquidTank();
		if(tank != null && tank.containsValidLiquid()){
			this.fontRenderer.drawString(tank.getLiquidName(), var5 + 10, var6 + 10, 4210752);
			this.fontRenderer.drawString(tank.getLiquid().amount + "/" + tank.getCapacity(), var5 + 10, var6 + 20, 4210752);
		}
	}
	
}