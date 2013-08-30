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
	protected int attackDamage;
	protected int bulletSpeed;
	//private int attackSpeed;
	protected int range;
	protected attackType atkTyp;
	protected BufferedImage towerRender;
	
	//private int turretCooldownTime; // milliseconds before next fire
	protected int currentHeat;
	protected int bulletHeat;
	protected long lastFireTime;
	
	// set up an ArrayList of bullets and associated variables
	//bulletType; - we need to set up what kind of bullet a tower fires
	protected ArrayList<Bullet> bulletList; 
	//private int maxActiveBullets;		// this could be re-worked to function similar to how robocode did with weapon heat
	//private int currentActiveBullets;
	
	protected ArrayList<Mob> targetMobs;
	protected int targetMob;
	
	
	// Constructor
	public Tower(int iTile, int kTyle, attackType aT, int aD,
			int bS, int rng, int bH) {
		this.x = iTile;
		this.y = kTyle;
		this.atkTyp = aT;
		this.attackDamage = aD;
		//this.attackSpeed = aS;
		this.bulletSpeed = bS;
		//this.maxActiveBullets = maxBl;
		this.range = rng;
		//this.turretCooldownTime = tCT;
		this.bulletHeat = bH;
		
		targetMobs = new ArrayList<Mob>();
		bulletList = new ArrayList<Bullet>();
		this.currentHeat = 0;
	}
	
	// When the turret fires, this is called and adds a new bullet to the bulletList ArrayList
	public void shootBullet() {
		if (!targetMobs.isEmpty()){
			currentHeat = bulletHeat;
			lastFireTime = System.currentTimeMillis();
			//bulletList.add(new Bullet());
		}
		//bulletList.add();
	}	

	// We'll need a function to remove the tower for upgrading and recycling purposes
	public void removeTower() {
		// Please work!
	}
	
	
	// The backbone of the tower
	public void tick() {
		if (currentHeat <= 0) {
			shootBullet();
		} else {
			currentHeat -= (System.currentTimeMillis() - lastFireTime);
		}
		for (Bullet b : bulletList){
			b.tick();
		}
	}
	
	// scan area for available target mobs
	public void findTargets() {
		targetMobs.clear();
		double TempX = x * Game.map.getTile(0, 0).getWidth();
		double TempY = y * Game.map.getTile(0, 0).getHeight();
		for (Mob m : Game.mobs) {
			if (Math.sqrt(Math.pow((Math.abs(TempX - m.getX())), 2) + Math.pow(Math.abs(TempY - m.getY()), 2)) <= range) {
				targetMobs.add(m);
			}
		}
	}
	
	// returns the tower's attack damage - used by bullet
	public int getAttackDamage() {
		return attackDamage;
	}
	
	// returns the tower's attack type - used by bullet
	public attackType getAttackType() {
		return atkTyp;
	}
	
	// returns the buffered image - used by the graphics handler
	public BufferedImage getRender() {
		return towerRender;
	}
	
	// renders the buffered image in memory
	public void render() {
		towerRender = new BufferedImage(range * 2,
										range * 2,
										BufferedImage.TYPE_INT_ARGB);
		Graphics g = towerRender.createGraphics();
//		g.drawImage(this.sprite.getImage(),
//				    (int) x,
//				    (int) (Game.map.getHeightPixels() - y),
//				    this.sprite.getWidth() * Game.SCALE,
//				    this.sprite.getHeight() * Game.SCALE, null);
		g.drawImage(this.sprite.getImage(),
				    range - sprite.getWidth(), 
				    range - sprite.getHeight(),
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
