/*
 * File Name: Entity.java
 * Contributors:	Jonathan Bradley - 7/17/2013
 * 					Ryan Meier
 * 					Ben Emrick
 * 
 * Purpose: This class will be the parent class for all in-game entities
 */
package td.entity;

import td.graphics.Sprite;

public class Entity {
	protected int x, xb;			// changed to protected from private
	protected int y, yb;			// changed to protected from private
	protected boolean isVisible, isAlive;	// changed to protected from private
	protected Sprite sprite;		// Added sprite to this class for subclass use
	protected enum attackType {
		FIRE, ICE, LIGHTNING, EARTH
	}
	
	public Entity() {
		isAlive = true;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Boolean isVisible() {
		return isVisible;
	}
	
	public void move() {
		
	}
	
	public void tick() {
		
	}
	
	public void render() {
	}
	
	public void remove(){
		isVisible = false;
		isAlive = false;
		
	}
}
