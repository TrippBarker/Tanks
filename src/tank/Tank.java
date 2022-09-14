package tank;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Tank {
	public int screenX, screenY;
	public int speed;
	public String name;
	GamePanel gp;
	KeyHandler keyH;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public boolean collisionOn = false;
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/moveUp1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/moveUp2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/moveDown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/moveDown2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/moveLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/moveLeft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/moveRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/moveRight2.png"));
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
