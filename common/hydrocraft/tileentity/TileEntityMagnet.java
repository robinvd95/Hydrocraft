package hydrocraft.tileentity;

import hydrocraft.entity.EntityItemMagnet;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityMagnet extends TileEntity {

	private double radius = 5d;
	private List<EntityItemMagnet> itemListMagnet = new ArrayList();

	public TileEntityMagnet(){

	}

	public void updateEntity(){
		if(this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)){
			List<Entity> itemList = this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getAABBPool().getAABB(xCoord+0.5D-radius, yCoord+0.5D-radius, zCoord+0.5D-radius, xCoord+0.5D+radius, yCoord+0.5D+radius, zCoord+0.5D+radius));
			double xPos = this.xCoord+.5D;
			double yPos = this.yCoord+.5D;
			double zPos = this.zCoord+.5D;
			double d0 = 8.0D;
			
			for(Entity x : itemList){
				if(x.getDistanceSq(xPos, yPos, zPos) >= 0.75D){
					double d1 = (xPos - x.posX) / d0;
					double d2 = (yPos - x.posY) / d0;
					double d3 = (zPos - x.posZ) / d0;
					double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
					double d5 = 1.0D - d4;

					if (d5 > 0.0D)
					{
						d5 *= d5;
						x.motionX += d1 / d4 * d5 * 0.01D;
						x.motionY += d2 / d4 * d5 * 0.01D;
						x.motionZ += d3 / d4 * d5 * 0.01D;
					}
					x.moveEntity(x.motionX, x.motionY, x.motionZ);
				}
				/*EntityItem y = (EntityItem)z;
				EntityItemMagnet var2 = new EntityItemMagnet(y.worldObj, y.posX, y.posY, y.posZ, y.getEntityItem());
				this.worldObj.spawnEntityInWorld(var2);
				this.itemListMagnet.add(var2);
				z.setDead();*/
			}
			
			/**/
		}
	}	
}
