package bkcraft.bedwars.world.schematic;

public class Region {

	public int width;
	public int height;
	public int length;

	short[][][] blocks;
	byte[][][] blockData;

	public Region(int width, int height, int length, short[] blocks, byte[] blockData) {
		this.width = width;
		this.height = height;
		this.length = length;

		this.blocks = this.blocksTo3D(blocks);
		this.blockData = this.dataTo3D(blockData);
	}

	private short[][][] blocksTo3D(short[] blocks) {
		short[][][] returnBlocks = new short[this.width][this.height][this.length];
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				for (int z = 0; z < this.length; z++) {
					int index = (y * this.length + z) * this.width + x;
					returnBlocks[x][y][z] = blocks[index];
				}
			}
		}
		return returnBlocks;
	}

	private byte[][][] dataTo3D(byte[] blockData) {
		byte[][][] returnData = new byte[this.width][this.height][this.length];
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				for (int z = 0; z < this.length; z++) {
					int index = (y * this.length + z) * this.width + x;
					returnData[x][y][z] = blockData[index];
				}
			}
		}
		return returnData;
	}

	public short[][][] getBlocks() {
		return this.blocks;
	}

	public byte[][][] getBlockData() {
		return this.blockData;
	}
}
