package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class PlayerBullet extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	public int screenX;
	public int screenY;
	public String firingDir;
	public boolean firing = false;
	
	public PlayerBullet(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		// Defines where on the screen the sprite should be painted
		screenX = gp.tileSize - (gp.tileSize *2);
		screenY = 0;
		
		solidArea = new Rectangle(0, 0, 0, 0);
		
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
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/bulletUp.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/bulletUp.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/bulletDown.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/bulletDown.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/bulletLeft.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/bulletLeft.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/bulletRight.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/bulletRight.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (firing) {
			switch(firingDir) {
			case "up":
				screenY -= speed;
				break;
			case "down":
				screenY += speed;
				break;
			case "left":
				screenX -= speed;
				break;
			case "right":
				screenX += speed;
			}
			
			if (screenY < gp.tileSize - (gp.tileSize * 2) ||
				screenX < gp.tileSize - (gp.tileSize * 2) ||
				screenY > gp.tileSize * gp.maxScreenRow + gp.tileSize||
				screenX > gp.tileSize * gp.maxScreenCol + gp.tileSize ||
				collisionOn) {
				firing = false;
				screenX = gp.tileSize - (gp.tileSize *2);
				screenY = 0;
			}
		}
		
		if(!firing) {
			if (keyH.fireUp == true || keyH.fireDown == true || keyH.fireLeft == true || keyH.fireRight == true) {
				firing = true;
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
					screenX = gp.player.screenX;
					screenY = gp.player.screenY - (gp.tileSize / 2);
					firingDir = "up";
					break;
				case "down":
					screenX = gp.player.screenX;
					screenY = gp.player.screenY + (gp.tileSize / 2);
					firingDir = "down";
					break;
				case "left":
					screenX = gp.player.screenX - (gp.tileSize / 2);
					screenY = gp.player.screenY;
					firingDir = "left";
					break;
				case "right":
					screenX = gp.player.screenX + (gp.tileSize / 2);
					screenY = gp.player.screenY;
					firingDir = "right";
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
