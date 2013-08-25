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

import td.map.tile.GrassTile;
import td.map.tile.StreetCTile;
import td.map.tile.StreetHTile;
import td.map.tile.StreetVTile;
import td.map.tile.Tile;

public class Map {

	// Set up needed variables
	private int mapWidth, mapHeight;
	private Tile[][] mapGrid;
	private int TileID;
	private int mapWidthPixels, mapHeightPixels;
	private Boolean hasChanged;
	private BufferedImage mapImage;
	private int startTileID, endTileID;
	//public Pathfinder path;

	// Constructor - this will need to be modified once we have a config file
	// set up for maps
	// currently only creates tiles of grass
	public Map(int width, int height) {
		this.mapGrid = new Tile[width][height]; // Map is nothing more than a 2D
												// array of Tiles
		TileID = 0;
		mapWidth = width;
		mapHeight = height;
		for (int i = 0; i < width; i++) {
			for (int k = 0; k < height; k++) {
				this.mapGrid[i][k] = new GrassTile(TileID++);
			}
		}
		mapWidthPixels = mapGrid[0][0].getWidth() * mapWidth; // Sprite width *
																// Tile width *
		mapHeightPixels = mapGrid[0][0].getHeight() * mapHeight; // Sprite
																 // height *
																 // Tile height

		// Test section for a simple maze to test the pathfinder
		startTileID = 45;
		changeTile(new StreetVTile(45), 45);
		changeTile(new StreetVTile(46), 46);
		changeTile(new StreetVTile(47), 47);
		changeTile(new StreetVTile(48), 48);
		changeTile(new StreetCTile(49), 49);
		changeTile(new StreetHTile(64), 64);
		changeTile(new StreetHTile(79), 79);
		changeTile(new StreetHTile(94), 94);
		changeTile(new StreetHTile(109), 109);
		changeTile(new StreetCTile(124), 124);
		changeTile(new StreetVTile(125), 125);
		changeTile(new StreetVTile(126), 126);
		changeTile(new StreetVTile(127), 127);
		changeTile(new StreetCTile(128), 128);
		changeTile(new StreetHTile(143), 143);
		changeTile(new StreetHTile(158), 158);
		changeTile(new StreetCTile(173), 173);
		changeTile(new StreetVTile(174), 174);
		changeTile(new StreetVTile(175), 175);
		changeTile(new StreetVTile(176), 176);
		changeTile(new StreetCTile(177), 177);
		changeTile(new StreetHTile(162), 162);
		changeTile(new StreetHTile(147), 147);
		changeTile(new StreetHTile(132), 132);
		changeTile(new StreetHTile(117), 117);
		changeTile(new StreetCTile(102), 102);
		changeTile(new StreetVTile(101), 101);
		changeTile(new StreetVTile(100), 100);
		changeTile(new StreetCTile(99), 99);
		changeTile(new StreetHTile(84), 84);
		changeTile(new StreetHTile(69), 69);
		changeTile(new StreetHTile(54), 54);
		changeTile(new StreetCTile(39), 39);
		changeTile(new StreetVTile(40), 40);
		changeTile(new StreetVTile(41), 41);
		changeTile(new StreetVTile(42), 42);
		changeTile(new StreetVTile(43), 43);
		changeTile(new StreetVTile(44), 44);
		endTileID = 44;
		// End test-map section

//		path = new Pathfinder();
//		path.init();

		generateMapImage();
	}

	// Returns the Map height (in tiles)
	public int getHeight() {
		return mapHeight;
	}

	// Returns the Map Width (in tiles)
	public int getWidth() {
		return mapWidth;
	}

	// Returns the Map Height (in pixels)
	public int getHeightPixels() {
		return mapHeightPixels;
	}

	// Returns the Map Width (in pixels)
	public int getWidthPixels() {
		return mapWidthPixels;
	}
	
	// returns the ID of the start tile of the map
	public int getStartTileID() {
		return startTileID;
	}
	
	// returns the ID of the ending tile of the map
	public int getEndTileID() {
		return endTileID;
	}

	// Returns a specific Tile, by x and y coordinates
	public Tile getTile(int x, int y) {
		return mapGrid[x][y];
	}

	// returns the BufferedImage of the map
	public BufferedImage getMapImage() {
		return mapImage;
	}

	// Creates a fresh BufferedImage if the map layout were to change
	private void generateMapImage() {
		mapImage = new BufferedImage(mapWidthPixels, mapHeightPixels,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = mapImage.createGraphics();
		for (int i = 0; i < mapWidth; i++) {
			for (int k = 0; k < mapHeight; k++) {
				Tile temp = mapGrid[i][k];
				int tempx = i * temp.getWidth();
				int tempy = (mapHeight - 1 - k) * temp.getHeight(); 	// We subtract k from mapHeight so that the image doesn't render upside down
				g.drawImage(temp.getImage(), tempx, tempy, temp.getWidth(),
						temp.getHeight(), null);
			}
		}
		hasChanged = false;
	}
	
	// Changes a specific tile by the tile's ID
	public void changeTile(Tile t, int ID) {
		mapGrid[(int) (Math.floor(ID / mapHeight))][(ID % mapHeight)] = t;
		hasChanged = true;
	}
	
	public void changeTile(Tile t, int i, int k) {
		mapGrid[i][k] = t;
		hasChanged = true;
	}

	// WIP - I'm not certain we even need anything done here
	public void tick() {

	}

	// If the map has changed, it renders a new map, else it simply ends
	// Screen.java will call the Buffered map image later in the render loop
	public void render() {
		if (hasChanged) {
			generateMapImage();
		}
	}
}
