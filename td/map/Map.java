/* 
 * File Name: Map.java
 * Contributors:	Jonathan Bradley 	- 7/18/2013
 * 					Ryan Meier			- 
 * 					Ben Emrick			-
 * 
 * Purpose: This class creates a map grid to keep track of each game tile
 * 
 * Future Goals: change constructor to read-in a text file for different
 * 		levels once we have the different types of tiles developed
 */
package td.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Map {

	private int mapWidth, mapHeight;
	private Tile[][] mapGrid;
	private int TileID;
	private int mapWidthPixels, mapHeightPixels;
	private Boolean hasChanged;
	private BufferedImage mapImage;

	public Map(int width, int height) {
		this.mapGrid = new Tile[height][width];
		TileID = 0;
		mapWidth = width;
		mapHeight = height;
		for (int i = 0; i < width; i++) {
			for (int k = 0; k < height; k++) {
				this.mapGrid[i][k] = new Tile("artAssets/grass.png", TileID++);
			}
		}
		mapWidthPixels = mapGrid[0][0].getWidth() * mapWidth;
		mapHeightPixels = mapGrid[0][0].getHeight() * mapHeight;
		generateMapImage();
	}

	public int getHeight() {
		return mapHeight;
	}

	public int getWidth() {
		return mapWidth;
	}

	public int getHeightPixels() {
		return mapHeightPixels;
	}

	public int getWidthPixels() {
		return mapWidthPixels;
	}

	public Tile[][] getMap() {
		return mapGrid;
	}

	public Tile getTile(int x, int y) {
		return mapGrid[x][y];
	}

	public BufferedImage getMapImage() {
		return mapImage;
	}

	private void generateMapImage() {
		mapImage = new BufferedImage(mapWidthPixels, mapHeightPixels,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = mapImage.createGraphics();
		for (int i = 0; i < mapWidth; i++) {
			for (int k = 0; k < mapHeight; k++) {
				Tile temp = mapGrid[i][k];
				g.drawImage(temp.getImage(), i * temp.getWidth(),
						k * temp.getHeight(), null);
			}
		}
		hasChanged = false;
	}

	public void changeTile(String img, int ID) {
		// use black magic to find the location in the grid
		mapGrid[(int) (Math.floor(ID / mapWidth))][(ID % mapWidth)] = new Tile(
				img, ID);
		hasChanged = true;
	}
	
	public void changeTile(String img, int i, int k) {
		mapGrid[i][k] = new Tile(img, ((i * mapHeight) + k));
		hasChanged = true;
	}

	public void tick() {

	}

	public void render() {
		if (hasChanged)  {
			generateMapImage();
		}
	}
}
