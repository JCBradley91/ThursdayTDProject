package td.entity.tower;

import java.util.ArrayList;
import java.util.Iterator;

import td.entity.Entity;
import td.entity.bullet.Bullet;
import td.entity.mob.Mob;

public class Tower extends Entity {
	
	// set up needed variables
	public int attackDamage;
	private int bulletSpeed;
	private int attackSpeed;
	public attackType atkTyp;
	
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
	
	public void tick() {
		Iterator<Bullet> iT = bulletList.iterator();
		while (iT.hasNext()) {
			Bullet b = iT.next();
			b.tick();
		}
	}
	
}
