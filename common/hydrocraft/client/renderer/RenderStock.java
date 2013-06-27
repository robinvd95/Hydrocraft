package hydrocraft.client.renderer;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;
import hydrocraft.client.IInventoryRenderer;
import hydrocraft.tileentity.TileEntityStock;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderStock extends TileEntitySpecialRenderer implements IInventoryRenderer{

	private ModelBase model = new ModelBase(){};
	private static final int[] angleMap = new int[6];

	ModelRenderer stock;
	
	static {
		angleMap[EAST.ordinal()] = 270;
		angleMap[WEST.ordinal()] = 270;
		angleMap[UP.ordinal()] = 0;
		angleMap[DOWN.ordinal()] = 0;
		angleMap[SOUTH.ordinal()] = 270;
		angleMap[NORTH.ordinal()] = 270;
	}
	
	public RenderStock(){
		model.textureWidth = 64;
		model.textureHeight = 32;
		
		stock = new ModelRenderer(model, 0, 0);
		stock.addBox(-1F, -8F, -1F, 2, 16, 2);
		stock.setRotationPoint(0F, 0F, 0F);
		stock.setTextureSize(64, 32);
	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		render(var2+0.5, var4+0.5, var6+0.5, ((TileEntityStock)var1).rotation, ((TileEntityStock)var1).getDirection());
	}

	public void render(double x, double y, double z, float rotation, ForgeDirection orientation){
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glColor3f(1, 1, 1);
		
		GL11.glTranslatef((float) x, (float) y, (float) z);
		
		float[] angle = {0, 0, 0};
		
		switch(orientation){
		case EAST:
		case WEST:
		case DOWN:
			angle[2] = angleMap[orientation.ordinal()];
		case SOUTH:
		case NORTH:
			angle[0] = angleMap[orientation.ordinal()];
			break;
		}
		
		GL11.glRotatef(angle[0], 1.0f, 0.0f, 0f);
		GL11.glRotatef(angle[1], 0f, 1.0f, 0f);
		GL11.glRotatef(angle[2], 0f, 0f, 1.0f);
		
		bindTexture("/mods/hydrocraft/textures/models/woodenStockReinforced.png");
		
		GL11.glRotatef(rotation, 0.0f, 1.0f, 0.0f);
		
		stock.render(0.0625f);
		
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
	
	public void bindTexture(String par1){
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(par1);
	}

	@Override
	public void renderInventory() {
		this.render(-.5f, -.5f, -.5f, 0, UP);
	}
}
