package td.entity.mob;

import td.graphics.Sprite;

public class TestMob extends Mob{

	public TestMob() {
		super(100, 0, 10, 1, 147, 21, 0, 0, 0, 0);
		this.sprite = new Sprite("artAssets/Zombie.png");
		this.isVisible = true;
	}
	


	
	
}
