package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class PlayerGun extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
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
		// Defines starting position
		worldX = gp.tileSize * 10;
		worldY = gp.tileSize * 10;
		// Defines how many pixels object moves per refresh
		speed = 4;
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
		if (keyH.fireUp == true || keyH.fireDown == true || keyH.fireLeft == true || keyH.fireRight == true) {
			if (keyH.fireUp) {
				direction = "up";
			} else if (keyH.fireDown) {
				direction = "down";
			} else if (keyH.fireLeft) {
				direction = "left";
			} else if (keyH.fireRight) {
				direction = "right";
			}
			
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
			
			spriteCounter++;
			if(spriteCounter > 15) {
				if(spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
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
