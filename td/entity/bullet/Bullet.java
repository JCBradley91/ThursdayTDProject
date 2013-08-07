package td.entity.bullet;

import td.entity.Entity;
import td.entity.mob.Mob;
import td.graphics.Sprite;
import td.map.Tile;

public class Bullet extends Entity {
	
	private int BulletSpeed;
	private Mob targetMob;		// used when targeting a specific mob
	private Tile targetTile;	// used when targeting a specific tile (AOE)
	
	
	// Constructor for targeting a mob
	public Bullet(int i, int k, int bltSpd, Mob targMob, String spriteLocation){
		this.x = i;
		this.y = k;
		this.BulletSpeed = bltSpd;
		this.targetMob = targMob;
		this.sprite = new Sprite(spriteLocation);
	}
	
	// Constructor for targeting a Tile
	public Bullet(int i, int k, int bltSpd, Tile targTile, String spriteLocation) {
		this.x = i;
		this.y = k;
		this.BulletSpeed = bltSpd;
		this.targetTile = targTile;
		this.sprite = new Sprite(spriteLocation);
	}
	
	public void onBulletConnect(){
		this.isAlive = false;
	}
	
	public void onDeath() {
		
	}
}
