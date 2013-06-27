package hydrocraft.client.renderer;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;
import hydrocraft.client.IInventoryRenderer;
import hydrocraft.tileentity.TileEntityExtractor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderExtractorPipe extends TileEntitySpecialRenderer implements IInventoryRenderer{

	private ModelBase model = new ModelBase(){};
	private static final float[] angleMap = new float[6];

	ModelRenderer pipe;
	ModelRenderer base;
	ModelRenderer piston;
	
	static {
		angleMap[EAST.ordinal()] = (float) -Math.PI / 2;
		angleMap[WEST.ordinal()] = (float) Math.PI / 2;
		angleMap[UP.ordinal()] = 0;
		angleMap[DOWN.ordinal()] = (float) Math.PI;
		angleMap[SOUTH.ordinal()] = (float) Math.PI / 2;
		angleMap[NORTH.ordinal()] = (float) -Math.PI / 2;
	}
	
	public RenderExtractorPipe(){
		model.textureWidth = 64;
		model.textureHeight = 32;
		base = new ModelRenderer(model, 0, 0);
		base.addBox(-6F, -8F, -6F, 12, 4, 12);
		base.setRotationPoint(8F, 8F, 8F);
		base.setTextureSize(64, 32);

		pipe = new ModelRenderer(model, 0, 0);
		pipe.addBox(-4F, -4F, -4F, 8, 12, 8);
		pipe.setRotationPoint(8F, 8F, 8F);
		pipe.setTextureSize(64, 32);
		
		piston = new ModelRenderer(model, 0, 0);
		piston.addBox(-5F, -0F, -5F, 10, 2, 10);
		piston.setRotationPoint(8F, 8F, 8F);
		piston.setTextureSize(64, 32);

	}

	private void render(ForgeDirection orientation, double x, double y, double z, boolean on) {

		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glColor3f(1, 1, 1);

		GL11.glTranslatef((float) x, (float) y, (float) z);


		float[] angle = {0, 0, 0};

		switch (orientation) {
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

		base.rotateAngleX = angle[0];
		base.rotateAngleY = angle[1];
		base.rotateAngleZ = angle[2];

		pipe.rotateAngleX = angle[0];
		pipe.rotateAngleY = angle[1];
		pipe.rotateAngleZ = angle[2];

		piston.rotateAngleX = angle[0];
		piston.rotateAngleY = angle[1];
		piston.rotateAngleZ = angle[2];

		this.bindTexture("/mods/hydrocraft/textures/models/extractorBase.png");
		base.render(0.0625f);

		
		String texture = "/mods/hydrocraft/textures/models/extractorPiston.png";
		if(on){
			texture = ("/mods/hydrocraft/textures/models/extractorPistonOn.png");
		}
		this.bindTexture(texture);
		piston.render(0.0625f);

		bindTexture("/mods/hydrocraft/textures/models/extractorPipe.png");
		pipe.render(0.0625f);
		
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
	
	public void bindTexture(String par1){
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(par1);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		TileEntityExtractor tile = (TileEntityExtractor) tileentity;
		this.render(tile.getOrientation(), x, y, z, tile.getRedstoneState());
	}

	@Override
	public void renderInventory() {
		this.render(UP, -.5f, -.5f, -.5f, false);
	}
	
}
