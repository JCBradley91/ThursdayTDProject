/*
 * File Name: Entity.java
 * Contributors:	Jonathan Bradley - 7/17/2013
 * 					Ryan Meier
 * 					Ben Emrick
 * 
 * Purpose: This class will be the parent class for all in-game entities
 * 
 * Needs: getXTile and getYTile methods, for dealing with tile specific events
 */
package td.entity;

import java.util.Timer;

import td.graphics.Sprite;

public class Entity {
	// set up needed variables
	protected double x, xr;			// changed to protected from private - these probably need changed to floats?
	protected double y, yr;			// changed to protected from private
	protected int onTileByID;		// keeps track of what tile the entity is located inside
	protected boolean isVisible, isAlive;	// changed to protected from private
	protected Sprite sprite;		// Added sprite to this class for subclass use
	protected enum attackType {		// Different attack types
		FIRE, ICE, LIGHTNING, EARTH
	}
	
	protected Timer timer;
	
	// Constructor
	public Entity() {
		isAlive = true;
		timer = new Timer();
	}
	
	// returns the x position of the entity
	public double getX() {
		return x;
	}
	
	// returns the y position of the entity
	public double getY() {
		return y;
	}
	
	// returns the top left corner for rendering correctly
	public double getXRender() {
		return xr;
	}
	
	// returns the top left corner for rendering correctly
	public double getYRender() {
		return yr;
	}
	
	// Placeholder for subclasses
	public void move() {
	}
	
	// placeholder for subclasses
	public void tick() {
	}
	
	// placeholder for subclasses
	public void render() {
	}
	
	// Check to see if the entity is visible or not (for rendering)
	public Boolean isVisible() {
		return isVisible;
	}
	
	// Check to see if the entity is still alive - used for removing from ArrayList
	public Boolean isAlive() {
		return isAlive;
	}
		
	// makes sure the dead entity isn't rendered - called at the end of the death animation
	public void remove(){
		isVisible = false;
		isAlive = false;
		
	}
}
