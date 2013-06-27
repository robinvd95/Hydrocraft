package hydrocraft.bridge.buildcraft;

import hydrocraft.bridge.buildcraft.client.GuiEngine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity var1 = world.getBlockTileEntity(x, y, z);
		switch(ID){
		case 0: return new ContainerEngine(player.inventory, (TileHydrocraftEngine) var1);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity var1 = world.getBlockTileEntity(x, y, z);
		switch(ID){
		case 0: return new GuiEngine(player.inventory, (TileHydrocraftEngine) var1);
		}
		
		return null;
	}

}
