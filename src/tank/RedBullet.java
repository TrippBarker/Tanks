package tank;

import java.awt.Rectangle;

import main.GamePanel;
import main.KeyHandler;

public class RedBullet extends Bullet{
	
	public RedBullet(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		solidArea = new Rectangle(21, 21, 12, 12);
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		// Defines starting position
		screenX = gp.tileSize - (gp.tileSize *2);
		screenY = 0;
		// Defines how many pixels object moves per refresh
		speed = 6;
		// Defines starting direction
		direction = "right";
		timeTilNextFire = 0;
	}

}
