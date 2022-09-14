package tank;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class GreenGun extends Tank{
	GamePanel gp;
	KeyHandler keyH;
	
	public int screenX;
	public int screenY;
	public String fireDirection = "left";
	
	public PlayerGun(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		// Defines where on the screen the sprite should be painted
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		solidArea = new Rectangle(0, 0, 0, 0);
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		// pair position with the tank body
		screenX = gp.player.screenX;
		screenY = gp.player.screenY;
		// Defines starting direction
		direction = "right";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/fireUp.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/fireUp.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/fireDown.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/fireDown.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/fireLeft.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/fireLeft.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/fireRight.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/fireRight.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		screenY = gp.player.screenY;
		screenX = gp.player.screenX;
		if (keyH.fireUp == true || keyH.fireDown == true || keyH.fireLeft == true || keyH.fireRight == true) {
			if (keyH.fireUp) {
				fireDirection = "up";
			} else if (keyH.fireDown) {
				fireDirection = "down";
			} else if (keyH.fireLeft) {
				fireDirection = "left";
			} else if (keyH.fireRight) {
				fireDirection = "right";
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(fireDirection) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			} else {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			} else {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			} else {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			} else {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}

}
