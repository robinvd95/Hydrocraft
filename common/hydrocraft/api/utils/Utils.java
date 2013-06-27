package hydrocraft.api.utils;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.core.Position;

public class Utils {

	public static boolean debug = true;
	
	public static boolean canStackBeAddedToSlot(){
		return false;
	}

	public static ForgeDirection getWaterFlowDirection(World par1, int par2, int par3, int par4){
		int var1 = par1.getBlockMetadata(par2, par3, par4);
		
		if(par1.getBlockId(par2, par3, par4) != Block.waterStill.blockID || var1 == 0){
			return null;
		}
		
		for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS){
			int x = par2 + direction.offsetX;
			int y = par3 + direction.offsetY;
			int z = par4 + direction.offsetZ;
			int var2 = par1.getBlockId(x, y, z);
			if(var2 == Block.waterStill.blockID){
				int var3 = par1.getBlockMetadata(x, y, z);
				if(var3 > var1){
					return direction;
				}
			}
		}

		return ForgeDirection.UNKNOWN;
	}
	
	public static ForgeDirection rotate(ForgeDirection direction){
		if(direction == ForgeDirection.UNKNOWN || direction == null){
			return ForgeDirection.UNKNOWN;
		}
		int rotation = direction.ordinal() + 1;
		if(rotation > 5){
			rotation = 0;
		}
		return ForgeDirection.getOrientation(rotation);
	}
	
	public static ForgeDirection get2dOrientation(Position pos1, Position pos2) {
		double Dx = pos1.x - pos2.x;
		double Dz = pos1.z - pos2.z;
		double angle = Math.atan2(Dz, Dx) / Math.PI * 180 + 180;

		if (angle < 45 || angle > 315)
			return ForgeDirection.EAST;
		else if (angle < 135)
			return ForgeDirection.SOUTH;
		else if (angle < 225)
			return ForgeDirection.WEST;
		else
			return ForgeDirection.NORTH;
	}
	
	public static void printLine(Object o){
		if(debug){
			System.out.println(o);
		}
	}
	
	public static TileEntity getTileEntity(World world, Vector3 vec){
		return world.getBlockTileEntity((int)vec.getX(), (int)vec.getY(), (int)vec.getZ());
	}
	
	public static TileEntity getTileEntity(WorldPosition worldPos){
		return worldPos.getWorld().getBlockTileEntity(worldPos.getX(), worldPos.getY(), worldPos.getZ());
	}
	
	public static WorldPosition getWorldPositionFromForgeDirection(ForgeDirection direction, int x, int y, int z, World world){
		return new WorldPosition(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, world);
	}
	
	public static int clamp(int par1, int par2){
		if(par1 > par2){
			par1 = par2;
		}
		return par1;
	}

}
