package tank;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public abstract class Bullet {
	GamePanel gp;
	KeyHandler keyH;
	String firingDir;
	int timeTilNextFire;
	public boolean firing = false;
	Rectangle solidArea;
	
	int screenX;
	int screenY;
	int speed;
	String direction;
	public BufferedImage up, down, left, right;
	
	public void getPlayerImage() {
		try {
			up = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedUp1.png"));
			down = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedDown2.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedLeft1.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/sprites/bullet/firedRight1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
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
