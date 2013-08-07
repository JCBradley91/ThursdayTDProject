package td.entity.mob;

import td.entity.Entity;

public class Mob extends Entity {
	
	// set up needed variables
	private final int minHealth = 0;
	private int maxHealth;
	private int currHealth;
	private int armorValue;
	private int attackDamage;
	private float movementSpeed;
	private float speedMod;	// speed modifier - if we want to have a tower that slows mobs
	private float fireResist, iceResist, lightningResist, earthResist;
	
	// Constructor
	public Mob(int mH, int aV, int aD, float mS, float i, float k,
			   float fR, float iR, float lR, float eR) {
		this.maxHealth = mH;
		this.armorValue = aV;
		this.attackDamage = aD;
		this.movementSpeed = mS;
		this.x = i;
		this.y = k;
		this.fireResist = fR;
		this.iceResist = iR;
		this.lightningResist = lR;
		this.earthResist = eR;
		this.speedMod = 1; // speed modifier - if we want to have a tower that slows mobs
	}
	
	// Move command, will take in standard movement speed of 1/2 tile per second and modifies it
	public void move(float i, float k) {
		x = x + ((i * movementSpeed) / speedMod);
		y = y + ((k * movementSpeed) / speedMod);
	}
	
	// Used for taking damage from a bullet... called by bullet.java
	//		This still needs to take mob armor into account
	public void takeDamage(int dmg, attackType aType) {
		int modDmg = 0; // modified damage value
		if (aType == attackType.FIRE) {
			modDmg = (int) (dmg * (1 - fireResist));
		} else if (aType == attackType.ICE) {
			modDmg = (int) (dmg * (1 - iceResist));
		} else if (aType == attackType.LIGHTNING) {
			modDmg = (int) (dmg * (1 - lightningResist));
		} else if (aType == attackType.EARTH) {
			modDmg = (int) (dmg * (1 - earthResist));
		}

		if (modDmg <= 0) modDmg = 1; // make sure we're at least doing 1 damage
		
		currHealth -= modDmg; // this is the important line
		if (currHealth <= minHealth) {
			remove();
		}
	}
	
	// Used for healing a Mob (if we want to have healers)
	public void heal(int dmg) {
		if (currHealth + dmg > maxHealth) {
			currHealth = maxHealth;
		} else {
			currHealth += dmg;
		}
	}
	
	
}
