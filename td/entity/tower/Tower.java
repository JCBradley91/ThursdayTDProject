package td.entity.tower;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import td.Game;
import td.entity.Entity;
import td.entity.bullet.Bullet;
import td.entity.mob.Mob;

public class Tower extends Entity {
	
	// set up needed variables
	public int attackDamage;
	private int bulletSpeed;
	private int attackSpeed;
	public attackType atkTyp;
	private BufferedImage towerRender;
	
	// set up an ArrayList of bullets and associated variables
	//bulletType; - we need to set up what kind of bullet a tower fires
	private ArrayList<Bullet> bulletList; 
	private int maxActiveBullets;		// this could be re-worked to function similar to how robocode did with weapon heat
	private int currentActiveBullets;
	
	
	// Constructor
	public Tower(int iTile, int kTyle, attackType aT, int aD, int aS,
			int bS, int maxBl) {
		this.x = iTile;
		this.y = kTyle;
		this.atkTyp = aT;
		this.attackDamage = aD;
		this.attackSpeed = aS;
		this.bulletSpeed = bS;
		this.maxActiveBullets = maxBl;
	}
	
	// When the turret fires, this is called and adds a new bullet to the bulletList ArrayList
	public void shootBullet(Mob m) {
		//bulletList.add();
	}	

	// We'll need a function to remove the tower for upgrading and recycling purposes
	public void removeTower() {
	}
	
	
	// The backbone of the tower
	public void tick() {
		Iterator<Bullet> iT = bulletList.iterator();
		while (iT.hasNext()) {
			Bullet b = iT.next();
			b.tick();
		}
	}
	
	// returns the buffered image - used by the graphics handler
	public BufferedImage getRender() {
		return towerRender;
	}
	
	// renders the buffered image in memory
	public void render() {
		towerRender = new BufferedImage(Game.map.getWidthPixels(),
										Game.map.getHeightPixels(),
										BufferedImage.TYPE_INT_ARGB);
		Graphics g = towerRender.createGraphics();
		g.drawImage(this.sprite.getImage(),
				    (int) x,
				    (int) (Game.map.getHeightPixels() - y),
				    this.sprite.getWidth() * Game.SCALE,
				    this.sprite.getHeight() * Game.SCALE, null);
		for (Bullet b: bulletList) {
			g.drawImage(b.getSprite().getImage(),
					    (int) b.getX(),
					    (int) (Game.map.getHeightPixels() - b.getY()),
					    b.getSprite().getWidth(),
					    b.getSprite().getHeight(), null);
		}
	}
	
}
