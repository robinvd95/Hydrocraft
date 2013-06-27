package hydrocraft.tileentity.base;

import net.minecraft.tileentity.TileEntity;

public abstract class TileHydrocraft extends TileEntity{
	
	private boolean needInitialization = true;
	
	public void init(){
		
	}
	
	public void updateEntity(){
		if(this.needInitialization){
			this.init();
			this.needInitialization = false;
		}
		this.update();
	}
	
	public void update(){
		
	}
}
