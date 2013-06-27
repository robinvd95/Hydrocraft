package hydrocraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case -1:
			generateNether();
			break;
			
		case 0:
			generateSurface(world, random, chunkX*16, chunkZ*16);
			break;
			
		case 1:
			generateEnd();
			break;
		}
	}

	private void generateEnd() {
		
	}

	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		//Generate Copper
		for(int i = 0; i < 21; i++){
			int x = chunkX + rand.nextInt(16);
			int y = rand.nextInt(45);
			int z = chunkZ + rand.nextInt(16);
			
			(new WorldGenMinable(BaseHydrocraft.oreBlocks.blockID, 0, 8, Block.stone.blockID)).generate(world, rand, x, y, z);
		}
		
		for(int i = 0; i < 10; i++){
			int x = chunkX + rand.nextInt(16);
			int y = rand.nextInt(20);
			int z = chunkZ + rand.nextInt(16);
			
			(new WorldGenMinable(BaseHydrocraft.oreBlocks.blockID, 1, 5, Block.stone.blockID)).generate(world, rand, x, y, z);
		}
		
		for(int i = 0; i < 10; i++){
			int x = chunkX + rand.nextInt(16);
			int y = rand.nextInt(34);
			int z = chunkZ + rand.nextInt(16);
			
			(new WorldGenMinable(BaseHydrocraft.oreBlocks.blockID, 2, 10, Block.stone.blockID)).generate(world, rand, x, y, z);
		}
	}

	private void generateNether() {
		
	}

}
