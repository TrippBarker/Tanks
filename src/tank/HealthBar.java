package tank;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class HealthBar {
	GamePanel gp;
	public int screenX;
	public int screenY;
	public int lifePoints = 3;
	
	public BufferedImage zero, one, two, three;
	
	public HealthBar(GamePanel gp) {
		this.gp = gp;
		getLifeImage();
	}
	
	public void getLifeImage() {
		try {
			zero = ImageIO.read(getClass().getResourceAsStream("/sprites/health/0health.png"));
			one = ImageIO.read(getClass().getResourceAsStream("/sprites/health/1health.png"));
			two = ImageIO.read(getClass().getResourceAsStream("/sprites/health/2health.png"));
			three = ImageIO.read(getClass().getResourceAsStream("/sprites/health/3health.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePos(int tankScreenX, int tankScreenY) {
		screenX = tankScreenX;
		screenY = tankScreenY + (gp.tileSize / 2) + (gp.tileSize / 8);
	}
	
	public void updatePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch (lifePoints) {
		case 3:
			image = three;
			break;
		case 2:
			image = two;
			break;
		case 1:
			image = one;
			break;
		case 0:
			image = zero;
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
