package hydrocraft.api.utils;

import net.minecraft.world.World;

public class WorldPosition {

	private int x,y,z;

	private World world;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public World getWorld(){
		return this.world;
	}
	
	public void setNewPosition(int par1, int par2, int par3){
		this.x = par1;
		this.y = par2;
		this.z = par3;
	}

	public WorldPosition(int par1, int par2, int par3, World par4){
		this.x = par1;
		this.y = par2;
		this.z = par3;
		this.world = par4;
	}
}
