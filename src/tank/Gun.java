package tank;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Gun {
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
