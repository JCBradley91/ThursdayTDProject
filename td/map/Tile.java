/* 
 * File Name: Tile.java
 * Contributors:	Jonathan Bradley 	- 7/18/2013
 * 					Ryan Meier			- 
 * 					Ben Emrick			-
 * 
 * Purpose: This class creates a new game tile with a sprite
 */
package td.map;

import td.Game;
import td.graphics.Sprite;
import java.awt.Image;

public class Tile {
	
	// Set up needed variables
	private Sprite sprite;
	private final int tileID;
	private boolean isPassable;
	private int w, h; // might not be needed... could just call sprite.getWidth and sprite.getHeight instead of taking up more memory
	
	// Constructor - could maybe use a second constructor that takes in an x and y and then converts them to a tileID
	public Tile(String img, int ID, boolean bool) {
		this.tileID = ID;
		sprite = new Sprite(img);
		isPassable = bool;
		w = sprite.getWidth() * Game.SCALE;
		h = sprite.getHeight() * Game.SCALE;
	}
	
	// Returns whether or not the tile can be moved through
	public boolean canPass() {
		return isPassable;
	}
	
	// Returns the tileID
	public int getID() {
		return tileID;
	}
	
	// Returns the Tile height in pixels
	public int getHeight() {
		return h;
	}
	
	// Returns the Tile width in pixels
	public int getWidth() {
		return w;
	}
	
	// Returns the bufferedImage of the sprite
	public Image getImage() {
		return sprite.getImage();
	}
	
	// Might not be needed since Map.java handles rendering the tiles
	public void render() {
		
	}
	
}
