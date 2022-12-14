package tank;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Bullet {
	GamePanel gp;
	KeyHandler keyH;
	String firingDir;
	int timeTilNextFire;
	public boolean firing = false;
	public Rectangle solidArea = new Rectangle(21, 21, 12, 12);;
	public Tank tankName;
	public int timeTilNextShot = 0;
	public int lifePoints = 3;
	public int screenX = 0;
	public int screenY = 0;
	public int speed = 6;
	public String direction = "right";
	public BufferedImage up, down, left, right;
	public boolean collisionOn = false;
	
	public Bullet(GamePanel gp, KeyHandler keyH, Tank tankName) {
		this.gp = gp;
		this.keyH = keyH;
		this.tankName = tankName;
		
		getPlayerImage();
	}
	
	public void getPlayerImage() {
		try {
			up = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedUp2.png"));
			down = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedDown2.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedLeft2.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedRight2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (firing) {
			switch (direction) {
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
		
		if (((keyH.redFireUp == true || keyH.redFireDown == true || keyH.redFireLeft == true || keyH.redFireRight == true ||
			keyH.greenFireUp == true || keyH.greenFireDown == true || keyH.greenFireLeft == true || keyH.greenFireRight == true))
				&& timeTilNextShot <= 0 && !firing && lifePoints > 0) {
				if (tankName instanceof RedTank) {
					if (keyH.redFireUp) {
						direction = "up";
						firing = true;
					} else if (keyH.redFireDown) {
						direction = "down";
						firing = true;
					} else if (keyH.redFireLeft) {
						direction = "left";
						firing = true;
					} else if (keyH.redFireRight) {
						direction = "right";
						firing = true;
					}
					if (firing) {
						screenY = tankName.screenY;
						screenX = tankName.screenX;
						timeTilNextShot = 50;
					}
				}
				
				if (tankName instanceof GreenTank) {
					if (keyH.greenFireUp) {
						direction = "up";
						firing = true;
					} else if (keyH.greenFireDown) {
						direction = "down";
						firing = true;
					} else if (keyH.greenFireLeft) {
						direction = "left";
						firing = true;
					} else if (keyH.greenFireRight) {
						direction = "right";
						firing = true;
					}
					if (firing) {
						screenY = tankName.screenY;
						screenX = tankName.screenX;
						timeTilNextShot = 50;
					}
				}
				
			}
		collisionOn = false;
		gp.bhChecker.checkTile(this);
		
		if(collisionOn) {
			firing = false;
			screenX = gp.tileSize - (gp.tileSize *2);
			screenY = 0;
		}

		if(collisionOn == false && firing) {
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
	}
	
	public void draw(Graphics2D g2) {
		timeTilNextShot--;
		BufferedImage image = null;
		switch(direction) {
		case "up":
			image = up;
			break;
		case "down":
			image = down;
			break;
		case "left":
			image = left;
			break;
		case "right":
			image = right;
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
