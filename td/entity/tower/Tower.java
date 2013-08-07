package td.entity.tower;

import java.util.List;

import td.entity.Entity;
import td.entity.bullet.Bullet;
import td.entity.mob.Mob;

public class Tower extends Entity {
	
	private int attackDamage;
	private int bulletSpeed;
	private int attackSpeed;
	private attackType atkTyp;
	
	private Bullet bulletType;
	private List<Bullet> bulletList; 
	private int maxBullets;
	private int currentActiveBullets;
	
	public Tower(int iTile, int kTyle, attackType aT, int aD, int aS,
			int bS, int maxBl) {
		this.x = iTile;
		this.y = kTyle;
		this.atkTyp = aT;
		this.attackDamage = aD;
		this.attackSpeed = aS;
		this.bulletSpeed = bS;
		this.maxBullets = maxBl;
	}
	
	public void shootBullet(Mob m) {
		//bulletList.add();
	}
	
	public void doHurt(Mob m) {
		m.takeDamage(attackDamage, atkTyp);
	}
	
	
}
