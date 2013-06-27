package hydrocraft.client.renderer;

import hydrocraft.client.HydrocraftRenderer;
import hydrocraft.client.IInventoryRenderer;
import hydrocraft.client.models.ModelWaterWheel;
import hydrocraft.tileentity.TileEntityWaterWheel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class RenderWaterWheel extends HydrocraftRenderer implements IInventoryRenderer{

	private ModelBase model = new ModelBase(){};
	
	private static int[] anglemap = new int[6];
	
	ModelRenderer base;
	ModelRenderer wing1;
	ModelRenderer wing2;
	ModelRenderer wing3;
	ModelRenderer wing4;
	ModelRenderer wing5;
	ModelRenderer wing6;
	ModelRenderer wing7;
	ModelRenderer wing8;
	
	static{
		anglemap[ForgeDirection.NORTH.ordinal()] = 180;
		anglemap[ForgeDirection.EAST.ordinal()] = 90;
		anglemap[ForgeDirection.SOUTH.ordinal()] = 0;
		anglemap[ForgeDirection.WEST.ordinal()] = 270;
		
	}

	public RenderWaterWheel(){
		base = new ModelRenderer(model, 0, 13);
		base.addBox(-1F, -1F, -7F, 2, 2, 15);
		base.setRotationPoint(0F, 0F, 0F);
		base.setTextureSize(64, 32);
		setRotation(base, 0F, 0F, 0F);
		
		wing1 = new ModelRenderer(model, 0, 0);
		wing1.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing1.setRotationPoint(0F, 0F, 0F);
		wing1.setTextureSize(64, 32);
		setRotation(wing1, 0F, 0F, 0F);
		
		/*wing2 = new ModelRenderer(model, 0, 0);
		wing2.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing2.setRotationPoint(0F, 0F, 0F);
		wing2.setTextureSize(64, 32);
		setRotation(wing2, 0F, 0F, 0.7853982F);
		
		wing3 = new ModelRenderer(model, 0, 0);
		wing3.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing3.setRotationPoint(0F, 0F, 0F);
		wing3.setTextureSize(64, 32);
		setRotation(wing3, 0F, 0F, 1.570796F);
		
		wing4 = new ModelRenderer(model, 0, 0);
		wing4.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing4.setRotationPoint(0F, 0F, 0F);
		wing4.setTextureSize(64, 32);
		setRotation(wing4, 0F, 0F, 2.356194F);
		
		wing5 = new ModelRenderer(model, 0, 0);
		wing5.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing5.setRotationPoint(0F, 0F, 0F);
		wing5.setTextureSize(64, 32);
		setRotation(wing5, 0F, 0F, 3.153228F);
		
		wing6 = new ModelRenderer(model, 0, 0);
		wing6.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing6.setRotationPoint(0F, 0F, 0F);
		wing6.setTextureSize(64, 32);
		setRotation(wing6, 0F, 0F, -0.7853982F);
		
		wing7 = new ModelRenderer(model, 0, 0);
		wing7.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing7.setRotationPoint(0F, 0F, 0F);
		wing7.setTextureSize(64, 32);
		setRotation(wing7, 0F, 0F, -1.570796F);
		
		wing8 = new ModelRenderer(model, 0, 0);
		wing8.addBox(1F, -0.5F, -6F, 20, 1, 12);
		wing8.setRotationPoint(0F, 0F, 0F);
		wing8.setTextureSize(64, 32);
		setRotation(wing8, 0F, 0F, -2.356194F);*/
	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		renderModel(((TileEntityWaterWheel) var1).getDirection(), var2, var4, var6, ((TileEntityWaterWheel)var1).getRotation());
	}

	private void renderModel(ForgeDirection direction, double x, double y, double z, float rotation){
		
		this.bindTexture("/mods/hydrocraft/textures/models/waterWheel.png");
		
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glColor3f(1, 1, 1);
		
		GL11.glTranslatef((float)x +.5f, (float)y +.5f , (float)z+.5f);
		
		int angle = 0;
		
		switch(direction){
		case NORTH:
		case SOUTH:
		case EAST:
		case WEST:
			angle = this.anglemap[direction.ordinal()];
		}
		
		
		GL11.glRotatef(angle, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(rotation, 0.0f, 0.0f, 1.0f);
		
		base.render(0.0625f);
		
		for(int i = 0; i < 8; i++){
			GL11.glRotatef(45, .0f, .0f, 1f);
			wing1.render(0.0625f);
		}
		
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

	@Override
	public void renderInventory() {
		GL11.glPushMatrix();
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		this.renderModel(ForgeDirection.EAST, 0, 0, 0, 0);
		GL11.glPopMatrix();
	}

	
	
}
