package td.entity.tower;

import td.Game;
import td.entity.Entity;
import td.graphics.Sprite;

public class TestTower extends Tower {

	
	public TestTower(int i, int k) { // use 7,9 for testing
		super(i, k, Entity.attackType.FIRE, 10, 50, 42 * 4, 1000);
		this.sprite = new Sprite("artAssets/Bunker.png");
		// x * pixels - range
		this.xr = (this.x * Game.map.getTile(0, 0).getWidth()) - this.range + this.sprite.getWidth();
		// mapHeight - y * piexels
		this.yr = (((Game.map.getHeight() - 1) - this.y) * Game.map.getTile(0, 0).getHeight()) - range + this.sprite.getHeight();
	}
}
