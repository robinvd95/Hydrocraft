package hydrocraft.api.utils;

import net.minecraft.world.World;

public class Vector3 {
	
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

	public Vector3(int par1, int par2, int par3){
		this.x = par1;
		this.y = par2;
		this.z = par3;
	}
	
	public Vector3(int par1, int par2, int par3, World par4){
		this.x = par1;
		this.y = par2;
		this.z = par3;
		this.world = par4;
	}
}
