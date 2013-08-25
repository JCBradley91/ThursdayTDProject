package td.entity.bullet;

import java.util.Iterator;

import td.Game;
import td.entity.Entity;
import td.entity.mob.Mob;
import td.entity.tower.Tower;
import td.graphics.Sprite;
import td.map.tile.Tile;

public class Bullet extends Entity {
	
	// setup needed variables
	private int BulletSpeed;	// sets the bullet speed
	private Tower tower;
	private Mob targetMob;		// used when targeting a specific mob
	private Tile targetTile;	// used when targeting a specific tile (AOE)
	private Sprite sprite;
	
	
	// Constructor for targeting a mob
	public Bullet(int i, int k, int bltSpd, int bltDmg, Tower twr, Mob targMob, String spriteLocation){
		this.x = i; 	// x coordinate - will be set by the tower's location
		this.y = k;		// y coordinate - will be set by the tower's location
		this.BulletSpeed = bltSpd;		// sets the bullet speed for the bullet
		this.tower = twr;
		this.targetMob = targMob;		// sets a specific mob as the target
		this.sprite = new Sprite(spriteLocation);	// create new sprite for the bullet
	}
	
	// Constructor for targeting a Tile (for AOE)
	public Bullet(int i, int k, int bltSpd, Tile targTile, String spriteLocation) {
		this.x = i; 	// x coordinate - will be set by the tower's location
		this.y = k;		// y coordinate - will be set by the tower's location
		this.BulletSpeed = bltSpd;		// sets the bullet speed for the bullet
		this.targetTile = targTile;		// sets a specific tile as the target (for AOE)
		this.sprite = new Sprite(spriteLocation);	// create new sprite for the bullet
	}
	
	// sets isAlive to false to trigger the death animation (explosion for bullets?)
	public void onBulletConnectMob(){
		doHurt(targetMob);
		this.isAlive = false;
	}
	
	// check each mob to see if it's inside the target tile, then do damage if true
	public void onBulletConnectTile() {
		Iterator<Mob> iT = Game.mobs.iterator();
		while (iT.hasNext()) {
			Mob m = iT.next();
			if (m.getInTileID() == targetTile.getID()) doHurt(m);
		}
		this.isAlive = false;
	}
	
	// applies damage to a specific mob, damage and attack type called from tower that fired
	public void doHurt(Mob m) {
		m.takeDamage(tower.attackDamage, tower.atkTyp);
	}
	
	// returns the sprite of the bullet - used by Tower for rendering
	public Sprite getSprite() {
		return sprite;
	}
}
