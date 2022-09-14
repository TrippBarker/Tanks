package tank;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public abstract class Tank {
	public int screenX, screenY;
	public int speed;
	public int lifePoints = 3;
	public String name;
	GamePanel gp;
	KeyHandler keyH;
	boolean moving = false;
	
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

		if ((keyH.redMoveUp == true || keyH.redMoveDown == true || keyH.redMoveLeft == true || keyH.redMoveRight == true ||
			keyH.greenMoveUp == true || keyH.greenMoveDown == true || keyH.greenMoveLeft == true || keyH.greenMoveRight == true) &&
			lifePoints > 0) {
			if (this instanceof RedTank) {
				if (keyH.redMoveUp) {
					direction = "up";
					moving = true;
				}
				else if (keyH.redMoveDown) {
					direction = "down";
					moving = true;
				}
				else if (keyH.redMoveLeft) {
					direction = "left";
					moving = true;
				}
				else if (keyH.redMoveRight) {
					direction = "right";
					moving = true;
				}
			} else if (this instanceof GreenTank) {
				if (keyH.greenMoveUp) {
					direction = "up";
					moving = true;
				}
				else if (keyH.greenMoveDown) {
					direction = "down";
					moving = true;
				}
				else if (keyH.greenMoveLeft) {
					direction = "left";
					moving = true;
				}
				else if (keyH.greenMoveRight) {
					direction = "right";
					moving = true;
				}
			}

			collisionOn = false;
			gp.cChecker.checkTile(this);

			if(collisionOn == false && moving) {
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
				if (spriteNum == 1 && moving) {
					image = up1;
				} else {
					image = up2;
				}
				break;
			case "down":
				if (spriteNum == 1 && moving) {
					image = down1;
				} else {
					image = down2;
				}
				break;
			case "left":
				if (spriteNum == 1 && moving) {
					image = left1;
				} else {
					image = left2;
				}
				break;
			case "right":
				if (spriteNum == 1 && moving) {
					image = right1;
				} else {
					image = right2;
				}
				break;
			}
			moving = false;
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
}
