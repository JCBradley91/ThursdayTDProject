/*
 * File Name: Game.java
 * Contributors:	Jonathan Bradley - 7/17/2013
 * 					Ryan Meier
 * 					Ben Emrick
 * 
 * Purpose: This file handles all of the main parts of the game
 */
package td;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import td.map.Map;
import td.map.pathfinder.Pathfinder;
import td.entity.mob.Mob;
import td.entity.mob.TestMob;
import td.entity.tower.Tower;
import td.graphics.Screen;

public class Game implements Runnable {

	private static final String NAME = "TD - Thursday Build";
	public static final int SCALE = 2; // controls the game's scale... 3 seems like a good number for now
	//public static final int HEIGHT = 720;
	//public static final int WIDTH = 1280;
	
	// creates needed variables
	public static Boolean inGame = false;		// variable used for checking the state of the game
	public static Map map;						// one map per instance of game
	public static Pathfinder path;				// public pathfinder for mob's use
	public static Screen screen;				// one screen per instance of game
	public static List<Mob> mobs = new ArrayList<Mob>();	// holds all of the mobs for a given level
	public static List<Tower> towers = new ArrayList<Tower>();	// holds all of the towers for a given level
	public static float standardMovementSpeed; // will bet set to 1/2 a tile per second
	
	// game constructor, calls init
	public Game() {
		inGame = false;
		init();
	}
	
	// creates new instance of map and screen
	private void init() {
		map = new Map(15, 15); 			// creates a new map of the size 15 by 15 - will be replaced with map config file
		path = new Pathfinder();		// creates our pathfinder and initiates
		path.init();
		screen = new Screen(this);		// creates our screen, which is essentially our graphics handler
		standardMovementSpeed = (map.getTile(0, 0).getWidth() / 2) / 60; // move commands will be called 60 times a second
		mobs = new ArrayList<Mob>();
		towers = new ArrayList<Tower>();
		mobs.add(new TestMob());
	}
	
	// the nervous system of the game, handles all background processes
	private void tick() {
		// First we do all of the processing for the mobs
		for (Mob m : mobs) {
			m.tick();
		}
		
		// next we process the towers
		for (Tower t : towers) {
			t.tick();
		}
	}
	
	// handles the rendering of the game, takes place after tick if needed
	public void render() {
		map.render(); 			// map creates a buffered image that will be called by screen at the end of the loop
		for (Tower t : towers) {
			t.render(); 		// have all of the towers render their buffered image
		}
		for (Mob m : mobs) {
			m.render();			// have all of the mobs rende their buffered image
		}
		screen.render();		// calls for the screen to put everything together
	}
		
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int ticks = 0;
		int frames = 0;
		long lastTimer1 = System.currentTimeMillis();
		inGame = true;
		while(inGame) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}	

	// Main call - creates a new instance of Game and sets up a JFrame
	//	then adds game.screen (JPanel)
	public static void main(String[] args) {
		Game game = new Game();
		
		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(Game.map.getWidthPixels(), Game.map.getHeightPixels());
		//frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(Game.screen, BorderLayout.CENTER);	// adding the screen to the frame seems to force a render, trying to find workaround
		frame.setVisible(true);
		
		game.run();
		
	}

}
