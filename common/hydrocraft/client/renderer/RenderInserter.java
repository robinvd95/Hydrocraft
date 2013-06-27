package hydrocraft.client.renderer;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;
import hydrocraft.api.utils.Utils;
import hydrocraft.client.HydrocraftRenderer;
import hydrocraft.client.IInventoryRenderer;
import hydrocraft.tileentity.TileEntityInserter;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class RenderInserter extends HydrocraftRenderer implements IInventoryRenderer{

	private ModelBase model = new ModelBase(){};
	private ModelRenderer block;

	private static final int[] angleMap = new int[6];

	static {
		angleMap[EAST.ordinal()] = 270;
		angleMap[WEST.ordinal()] = 90;
		angleMap[UP.ordinal()] = 0;
		angleMap[DOWN.ordinal()] = 180;
		angleMap[SOUTH.ordinal()] = 90;
		angleMap[NORTH.ordinal()] = 270;
	}

	public RenderInserter(){
		block = new ModelRenderer(model, 0, 0);
		block.addBox(-8F, -8F, -8F, 16, 16, 16);
		block.setRotationPoint(0F, 0F, 0F);
		block.setTextureSize(64, 32);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		this.render(d0, d1, d2, ((TileEntityInserter)tileentity).getOrientation(), ((TileEntityInserter)tileentity).getActivated());
	}

	public void render(double x, double y, double z, ForgeDirection orientation, boolean on){

		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glColor3f(1, 1, 1);

		GL11.glTranslatef((float) x+0.5f, (float) y+0.5f, (float) z+0.5f);

		float[] angle = {0, 0, 0};

		switch(orientation){
		case EAST:
		case WEST:
		case DOWN:
			angle[2] = angleMap[orientation.ordinal()];
			break;
		case SOUTH:
		case NORTH:
			angle[0] = angleMap[orientation.ordinal()];
			break;
		}

		bindTexture("/mods/hydrocraft/textures/models/inserter.png");
		if(on){
			bindTexture("/mods/hydrocraft/textures/models/inserterOn.png");
		}
		
		GL11.glRotatef(angle[0], 1.0f, 0.0f, 0f);
		GL11.glRotatef(angle[1], 0f, 1.0f, 0f);
		GL11.glRotatef(angle[2], 0f, 0f, 1.0f);

		block.render(0.0625f);

		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

	@Override
	public void renderInventory() {
		this.render(-.5f, -.5f, -.5f, DOWN, false);
	}

}
