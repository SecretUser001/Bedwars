package bkcraft.bedwars.world.schematic;

import static bkcraft.bedwars.world.schematic.NBTSchematicReader.requireTag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import bkcraft.bedwars.world.jnbt.NBTInputStream;
import bkcraft.bedwars.world.jnbt.Tag;

public class SchematicReader {
	
	@SuppressWarnings("unchecked")
	public static Region read(File file) throws FileNotFoundException, IOException {
		NBTInputStream inputStream = new NBTInputStream(new FileInputStream(file));
		
		Tag rootTag = inputStream.readTag();
		
		if(!rootTag.getName().equals("Schematic")) {
			throw new IOException("Tag 'Schematic' does not exist or is not first");
		}

//		CompoundTag schematicTag = new CompoundTag(rootTag.getName(), (Map<String, Tag>) rootTag.getValue());

		// Check
//		Map<String, Tag> schematic = schematicTag.getValue();
		Map<String, Tag> schematic = (Map<String, Tag>) rootTag.getValue();
		
		if (!schematic.containsKey("Blocks")) {
			throw new IOException("Schematic file is missing a 'Blocks' tag");
		}

		// Check type of Schematic
		String materials = (String) requireTag(schematic, "Materials").getValue();
		if (!materials.equals("Alpha")) {
			throw new IOException("Schematic file is not an Alpha schematic");
		}

		// ====================================================================
		// Metadata
		// ====================================================================

		Region region;

		// Get information
		short width = (short) requireTag(schematic, "Width").getValue();
		short height = (short) requireTag(schematic, "Height").getValue();
		short length = (short) requireTag(schematic, "Length").getValue();
		
		// ====================================================================
		// Blocks
		// ====================================================================

		// Get blocks
		byte[] blockId = (byte[]) requireTag(schematic, "Blocks").getValue();
		byte[] blockData = (byte[]) requireTag(schematic, "Data").getValue();
		byte[] addId = new byte[0];
		short[] blocks = new short[blockId.length]; // Have to later combine IDs

		// We support 4096 block IDs using the same method as vanilla Minecraft, where
		// the highest 4 bits are stored in a separate byte array.
		if (schematic.containsKey("AddBlocks")) {
			addId = (byte[]) requireTag(schematic, "AddBlocks").getValue();
		}

		// Combine the AddBlocks data with the first 8-bit block ID
		for (int index = 0; index < blockId.length; index++) {
			if ((index >> 1) >= addId.length) { // No corresponding AddBlocks index
				blocks[index] = (short) (blockId[index] & 0xFF);
			} else {
				if ((index & 1) == 0) {
					blocks[index] = (short) (((addId[index >> 1] & 0x0F) << 8) + (blockId[index] & 0xFF));
				} else {
					blocks[index] = (short) (((addId[index >> 1] & 0xF0) << 4) + (blockId[index] & 0xFF));
				}
			}
		}
		
		region = new Region(width, height, length, blocks, blockData);
		
		return region;
	}
	
}
