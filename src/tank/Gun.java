package tank;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public abstract class Gun {
	GamePanel gp;
	KeyHandler keyH;
	
	public int screenX;
	public int screenY;
	public String direction;
	public String fireDirection;
	public String name;
	public Tank tankName;
	
	public BufferedImage up, down, left, right;
	
	public void getGunImage() {
		try {
			up = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/fireGunUp.png"));
			down = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/fireGunDown.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/fireGunLeft.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/sprites/" + name + "/fireGunRight.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		screenY = tankName.screenY;
		screenX = tankName.screenX;
		if (keyH.redFireUp == true || keyH.redFireDown == true || keyH.redFireLeft == true || keyH.redFireRight == true ||
			keyH.greenFireUp == true || keyH.greenFireDown == true || keyH.greenFireLeft == true || keyH.greenFireRight == true) {
			if (this instanceof RedGun) {
				if (keyH.redFireUp) {
					fireDirection = "up";
				} else if (keyH.redFireDown) {
					fireDirection = "down";
				} else if (keyH.redFireLeft) {
					fireDirection = "left";
				} else if (keyH.redFireRight) {
					fireDirection = "right";
				}
			}
			
			if (this instanceof GreenGun) {
				if (keyH.greenFireUp) {
					fireDirection = "up";
				} else if (keyH.greenFireDown) {
					fireDirection = "down";
				} else if (keyH.greenFireLeft) {
					fireDirection = "left";
				} else if (keyH.greenFireRight) {
					fireDirection = "right";
				}
			}
			
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(fireDirection) {
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
