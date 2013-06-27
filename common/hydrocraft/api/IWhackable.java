package hydrocraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IWhackable {

	boolean whack(World world, int x, int y, int z);
}
