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

import javax.swing.JFrame;

import td.map.Map;
import td.graphics.Screen;

public class Game implements Runnable {

	private static final String NAME = "TD - Thursday Build";
	public static final int SCALE = 2;
	//public static final int HEIGHT = 720;
	//public static final int WIDTH = 1280;
	
	public Boolean inGame = false;
	public Map map;
	public Screen screen;
	
	public ArrayList Mobs;
	public ArrayList Towers;
	
	public Game() {
		inGame = false;
		init();
	}
	
	private void init() {
		map = new Map(20, 20);
		screen = new Screen(this);
	}
	
	private void tick() {
		
	}
	
	public void render() {
		map.render();
		
		screen.render();
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
				//render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}	

	public static void main(String[] args) {
		Game game = new Game();
		
		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(game.map.getWidthPixels(), game.map.getWidthPixels());
		//frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game.screen, BorderLayout.CENTER);
		frame.setVisible(true);
		
		game.run();
		
	}

}
