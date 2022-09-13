package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		// Defines where on the screen the sprite should be painted
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle(9, 9, 36, 36);

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		// Defines starting position
		screenX = gp.tileSize * 13;
		screenY = gp.tileSize * 9;
		// Defines how many pixels object moves per refresh
		speed = 4;
		// Defines starting direction
		direction = "left";
	}

	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/moveUp1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/moveUp2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/moveDown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/moveDown2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/moveLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/moveLeft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/moveRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/moveRight2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update () {

		if (keyH.moveUp == true || keyH.moveDown == true || keyH.moveLeft == true || keyH.moveRight == true) {
			if (keyH.moveUp) {
				direction = "up";
			}
			else if (keyH.moveDown) {
				direction = "down";
			}
			else if (keyH.moveLeft) {
				direction = "left";
			}
			else if (keyH.moveRight) {
				direction = "right";
			}

			collisionOn = false;
			gp.cChecker.checkTile(this);

			if(collisionOn == false) {
				switch(direction) {
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
					break;
				}
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
