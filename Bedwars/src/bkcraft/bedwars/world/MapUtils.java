package bkcraft.bedwars.world;

import java.io.File;
import java.util.Random;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;

import bkcraft.bedwars.world.schematic.Region;

public class MapUtils {

	public static World createEmpthy(String worldName) {
		WorldCreator creator = new WorldCreator(worldName);
		creator.generator(new ChunkGenerator() {
			@Override
			public byte[] generate(World world, Random random, int x, int z) {
				return new byte[32768]; // Empty byte array
			}
		});
		return(creator.createWorld());
	}

	@SuppressWarnings("deprecation")
	public static void placeRegion(World world, Region region, int minX, int minY, int minZ) {
		short[][][] blocks = region.getBlocks();
		byte[][][] blockData = region.getBlockData();
				
		for(int x = 0; x < blocks.length; x++) {
			for(int y = 0; y < blocks[x].length; y++) {
				for(int z = 0; z < blocks[x][y].length; z++) {
					world.getBlockAt(minX + x, minY + y, minZ + z).setTypeId(blocks[x][y][z]);
					world.getBlockAt(minX + x, minY + y, minZ + z).setData(blockData[x][y][z]);
				}
			}
		}
	}
	
	public static void deleteFolderContent(File folder) {
	    for (File file : folder.listFiles()) {
	        if (file.isDirectory())
	            deleteFolderContent(file);
	        file.delete();
	    }
	}
	
}
