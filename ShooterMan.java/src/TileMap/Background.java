package TileMap;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Background {
	
	//image that is the background 
	private BufferedImage image;
	
	//positon 
	private double x;
	private double y;

	public Background(String s) {
		//uploads the image 
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//dras background
	public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null);
		
	}
	
}







