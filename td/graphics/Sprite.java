/* 
 * File Name: Tile.java
 * Contributors:	Jonathan Bradley 	- 7/18/2013
 * 					Ryan Meier			- 
 * 					Ben Emrick			-
 * 
 * Purpose: This class creates a new sprite
 * 
 * Future Goals: Create spritesheet class to reduce number of image files
 * 		and re-work class to incorporate this
 */
package td.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	
	// set up needed variables
	private BufferedImage spriteIMG;
	private String resourceLocation; // we might not need this... if performance becomes an issue we can try and remove it
	private int spriteWidth, spriteHeight;
	
	// Constructor
	public Sprite(String imgString) {
		this.resourceLocation = imgString;
		try {
			spriteIMG = ImageIO.read(new File(resourceLocation));
		} catch (IOException e) {
			System.out.println("Failed to open file");
			e.printStackTrace();
		}
		
		spriteWidth = spriteIMG.getWidth();
		spriteHeight = spriteIMG.getHeight();
	}
	
	// Returns the sprite height
	public int getHeight() {
		return spriteHeight;
	}
	
	// Returns the sprite width
	public int getWidth() {
		return spriteWidth;
	}
	
	// Returns the BufferedImage of the sprite... might not be needed?
	public BufferedImage getImage() {
		return spriteIMG;
	}
}
