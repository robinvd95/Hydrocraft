package hydrocraft.blocks;

import hydrocraft.ElectricPower;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockElectricPower extends BlockContainer {

	protected BlockElectricPower(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return this.getPower();
	}
	
	public abstract ElectricPower getPower();
}
