package tank;

import main.GamePanel;
import main.KeyHandler;

public class GreenGun extends Gun{
	
	public GreenGun(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		// Defines where on the screen the sprite should be painted
		
		setDefaultValues();
		getGunImage();
	}
	
	public void setDefaultValues() {
		// pair position with the tank body
		screenX = gp.greenTank.screenX;
		screenY = gp.greenTank.screenY;
		// Defines starting direction
		direction = "left";
		fireDirection = "left";
		name = "greenTank";
		tankName = gp.greenTank;
	}

}
