package hydrocraft.bridge.buildcraft.client;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;
import hydrocraft.IRenderInventory;
import hydrocraft.bridge.buildcraft.EngineHydrocraft;
import hydrocraft.bridge.buildcraft.EngineState;
import hydrocraft.bridge.buildcraft.IEngineProvider;
import hydrocraft.bridge.buildcraft.IInventoryRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import buildcraft.core.DefaultProps;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderEngine extends TileEntitySpecialRenderer implements IInventoryRenderer{

	private ModelBase model = new ModelBase(){};
	ModelRenderer base;
	ModelRenderer trunk;
	ModelRenderer piston;
	ModelRenderer chamber;
	private String baseTexture = "/mods/hydrocraft/buildcraft/textures/models/engine.png";
	private static final float[] angleMap = new float[6];

	static {
		angleMap[EAST.ordinal()] = (float) -Math.PI / 2;
		angleMap[WEST.ordinal()] = (float) Math.PI / 2;
		angleMap[UP.ordinal()] = 0;
		angleMap[DOWN.ordinal()] = (float) Math.PI;
		angleMap[SOUTH.ordinal()] = (float) Math.PI / 2;
		angleMap[NORTH.ordinal()] = (float) -Math.PI / 2;
	}

	public RenderEngine(){
		model.textureWidth = 64;
		model.textureHeight = 32;
		base = new ModelRenderer(model, 0, 0);
		base.addBox(-8F, -8F, -8F, 16, 4, 16);
		base.setRotationPoint(8F, 8F, 8F);
		base.setTextureSize(128, 64);

		trunk = new ModelRenderer(model, 0, 0);
		trunk.addBox(-4F, -4F, -4F, 8, 12, 8);
		trunk.setRotationPoint(8F, 8F, 8F);
		trunk.setTextureSize(128, 64);
		
		piston = new ModelRenderer(model, 0, 0);
		piston.addBox(-7F, -4F, -7F, 14, 4, 14);
		piston.setRotationPoint(8F, 8F, 8F);
		piston.setTextureSize(128, 64);
		
		chamber = new ModelRenderer(model, 0, 0);
		chamber.addBox(-5F, -4, -5F, 10, 2, 10);
		chamber.rotationPointX = 8F;
		chamber.rotationPointY = 8F;
		chamber.rotationPointZ = 8F;
	}

	public RenderEngine(String baseTexture) {
		this();
		this.baseTexture = baseTexture;
		//setTileEntityRenderer(TileEntityRenderer.instance);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void renderTileEntityAt(TileEntity par1, double x, double y, double z, float f) {
		EngineHydrocraft var1 = ((IEngineProvider)par1).getEngine();
		if(var1 != null){
			this.render(var1.getProgress(), var1.engineState, ForgeDirection.getOrientation(var1.engineTile.orientation), x, y, z);
		}
	}

	private void render(float progress, EngineState engineState, ForgeDirection orientation, double x, double y, double z) {

		/*if (BuildCraftCore.render == RenderMode.NoDynamic) {
			return;
		}*/

		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glColor3f(1, 1, 1);

		GL11.glTranslatef((float) x, (float) y, (float) z);

		float step;

		if (progress > 0.5) {
			step = 7.99F - (progress - 0.5F) * 2F * 7.99F;
		} else {
			step = progress * 2F * 7.99F;
		}

		float translatefact = step / 16;

		float[] angle = {0, 0, 0};
		float[] translate = {orientation.offsetX, orientation.offsetY, orientation.offsetZ};

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

		trunk.rotateAngleX = angle[0];
		trunk.rotateAngleY = angle[1];
		trunk.rotateAngleZ = angle[2];

		piston.rotateAngleX = angle[0];
		piston.rotateAngleY = angle[1];
		piston.rotateAngleZ = angle[2];
		
		chamber.rotateAngleX = angle[0];
		chamber.rotateAngleY = angle[1];
		chamber.rotateAngleZ = angle[2];

		float factor = (float) (1.0 / 16.0);

		this.bindTexture("/mods/hydrocraft/buildcraft/textures/models/engine.png");

		base.render(factor);

		this.bindTexture("/mods/hydrocraft/buildcraft/textures/models/pistonWood.png");
		GL11.glTranslatef(translate[0] * translatefact, translate[1] * translatefact, translate[2] * translatefact);
		piston.render(factor);
		GL11.glTranslatef(-translate[0] * translatefact, -translate[1] * translatefact, -translate[2] * translatefact);

		float chamberf = 2F / 16F;

		bindTexture("/mods/hydrocraft/buildcraft/textures/models/chamber.png");
		
		for (int i = 0; i <= step + 2; i += 2) {
			chamber.render(factor);
			GL11.glTranslatef(translate[0] * chamberf, translate[1] * chamberf, translate[2] * chamberf);
		}

		for (int i = 0; i <= step + 2; i += 2) {
			GL11.glTranslatef(-translate[0] * chamberf, -translate[1] * chamberf, -translate[2] * chamberf);
		}

		String texture = "";

		switch (engineState) {
			case BLUE:
				texture = "/mods/hydrocraft/buildcraft/textures/models/trunkBlue.png";
				break;
			case GREEN:
				texture = "/mods/hydrocraft/buildcraft/textures/models/trunkGreen.png";
				break;
			case ORANGE:
				texture = "/mods/hydrocraft/buildcraft/textures/models/trunkOrange.png";
				break;
			default:
				texture = "/mods/hydrocraft/buildcraft/textures/models/trunkRed.png";
				break;
		}

		this.bindTexture(texture);
		
		trunk.render(factor);

		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

	@Override
	public void renderInventory() {
		render(0.25f, EngineState.BLUE, ForgeDirection.UP, -.5f,  -.5f,  -.5f);
	}
	
	public void bindTexture(String par1){
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(par1);
	}
}
