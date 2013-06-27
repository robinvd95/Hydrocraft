package hydrocraft;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler{

	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.SERVER);
	}


	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}


	public void tickEnd(EnumSet<TickType> type, Object... tickData){
		onPlayerTickInGame(TickType.SERVER);
	}



	public void onRenderTick(){

	}

	public void onTickInGUI(){

	}

	public String getLabel() {
		return "Legendz server ticks";
	}

	public void onPlayerTickInGame(TickType SERVER){

	}

}
