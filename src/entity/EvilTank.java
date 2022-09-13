package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class EvilTank extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	public int screenX;
	public int screenY;
	
	public EvilTank(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		// Defines where on the screen the sprite should be painted
		screenX = gp.screenWidth;
		screenY = gp.screenHeight;

		solidArea = new Rectangle(9, 9, 36, 36);

		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		// Defines starting position
		screenX = gp.tileSize * 2;
		screenY = gp.tileSize * 2;
		// Defines how many pixels object moves per refresh
		speed = 4;
		// Defines starting direction
		direction = "right";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/evilTank/evilUp1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/evilTank/evilUp2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/evilTank/evilDown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/evilTank/evilDown2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/evilTank/evilLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/evilTank/evilLeft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/evilTank/evilRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/evilTank/evilRight2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(direction) {
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
