package tank;

import main.GamePanel;
import main.KeyHandler;

public class RedGun extends Gun{
	
	public RedGun(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		// Defines where on the screen the sprite should be painted
		
		setDefaultValues();
		getGunImage();
	}
	
	public void setDefaultValues() {
		// pair position with the tank body
		screenX = gp.redTank.screenX;
		screenY = gp.redTank.screenY;
		// Defines starting direction
		direction = "right";
		fireDirection = "right";
		name = "redTank";
		tankName = gp.redTank;
	}

}
