package hydrocraft;

import hydrocraft.client.gui.GuiCrusher;
import hydrocraft.client.gui.GuiElectricStorage;
import hydrocraft.client.gui.GuiElectrostaticGenerator;
import hydrocraft.client.gui.GuiFluidExtractor;
import hydrocraft.client.gui.GuiFurnace;
import hydrocraft.client.gui.GuiGrinder;
import hydrocraft.client.gui.GuiRewinder;
import hydrocraft.client.gui.GuiTank;
import hydrocraft.inventory.ContainerCrusher;
import hydrocraft.inventory.ContainerElectricStorage;
import hydrocraft.inventory.ContainerElectrostaticGenerator;
import hydrocraft.inventory.ContainerFluidExtractor;
import hydrocraft.inventory.ContainerFurnace;
import hydrocraft.inventory.ContainerGrinder;
import hydrocraft.inventory.ContainerRewinder;
import hydrocraft.inventory.ContainerTank;
import hydrocraft.tileentity.TileEntityCrusher;
import hydrocraft.tileentity.TileEntityElectrostaticGenerator;
import hydrocraft.tileentity.TileEntityEnergyStorage;
import hydrocraft.tileentity.TileEntityFluidExtractor;
import hydrocraft.tileentity.TileEntityFurnace;
import hydrocraft.tileentity.TileEntityGrinder;
import hydrocraft.tileentity.TileEntityRewinder;
import hydrocraft.tileentity.TileEntityTank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		switch(ID){
		case 0: return new ContainerElectrostaticGenerator(player.inventory, (TileEntityElectrostaticGenerator) tileEntity);
		case 1: return new ContainerGrinder(player.inventory, (TileEntityGrinder) tileEntity);
		case 2: return new ContainerRewinder(player.inventory, (TileEntityRewinder) tileEntity);
		case 3: return new ContainerFluidExtractor(player.inventory, (TileEntityFluidExtractor) tileEntity);
		case 4: return new ContainerFurnace(player.inventory, (TileEntityFurnace)tileEntity);
		case 5: return new ContainerElectricStorage(player.inventory, (TileEntityEnergyStorage) tileEntity);
		case 6: return new ContainerTank(player.inventory, (TileEntityTank) tileEntity);
		case 7: return new ContainerCrusher(player.inventory, (TileEntityCrusher) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		switch(ID){
		case 0: return new GuiElectrostaticGenerator(player.inventory, (TileEntityElectrostaticGenerator) tileEntity);
		case 1: return new GuiGrinder(player.inventory, (TileEntityGrinder) tileEntity);
		case 2: return new GuiRewinder(player.inventory, (TileEntityRewinder) tileEntity);
		case 3: return new GuiFluidExtractor(player.inventory, (TileEntityFluidExtractor) tileEntity);
		case 4: return new GuiFurnace(player.inventory, (TileEntityFurnace)tileEntity);
		case 5: return new GuiElectricStorage(player.inventory, (TileEntityEnergyStorage) tileEntity);
		case 6: return new GuiTank(player.inventory, (TileEntityTank) tileEntity);
		case 7: return new GuiCrusher(player.inventory, (TileEntityCrusher) tileEntity);
		}
		return null;
	}

}
