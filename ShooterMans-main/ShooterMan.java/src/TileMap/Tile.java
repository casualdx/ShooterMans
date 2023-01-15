package TileMap;

import java.awt.image.BufferedImage;

public class Tile {
	
	//what the image is gonna be 
	private BufferedImage image;
	private int type;
	
	// tile types
	public static final int normal = 0;
	public static final int block = 1;
	
	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}
	
	public BufferedImage getImage() { 
		return image; 
	}
	
	public int getType() { 
		return type; 
	}
	
}
