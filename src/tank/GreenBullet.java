package tank;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class PlayerBullet extends Tank{
	GamePanel gp;
	KeyHandler keyH;
	String firingDir;
	int timeTilNextFire;
	public boolean firing = false;
	
	public PlayerBullet(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		solidArea = new Rectangle(21, 21, 12, 12);
		
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
		timeTilNextFire = 0;
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedUp1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedUp2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedDown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedDown2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedLeft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedRight2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (timeTilNextFire > 0) {
			timeTilNextFire--;
		}
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
			collisionOn = false;
			gp.cChecker.checkTile(this);
			if (collisionOn) {
				firing = false;
				screenX = gp.tileSize - (gp.tileSize *2);
				screenY = 0;
			}
		}
		
		if(!firing && timeTilNextFire == 0) {
			if (keyH.fireUp == true || keyH.fireDown == true || keyH.fireLeft == true || keyH.fireRight == true) {
				timeTilNextFire = 50;
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
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(direction) {
		case "up":
			image = up2;
			break;
		case "down":
			image = down2;
			break;
		case "left":
			image = left2;
			break;
		case "right":
			image = right2;
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}

}
