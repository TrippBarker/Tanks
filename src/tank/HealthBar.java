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
	
	public BufferedImage zeroBars, oneBar, twoBars, threeBars;
	
	public HealthBar(GamePanel gp) {
		this.gp = gp;
		getLifeImage();
	}
	
	public void getLifeImage() {
		try {
			zeroBars = ImageIO.read(getClass().getResourceAsStream("/sprites/health/0health.png"));
			oneBar = ImageIO.read(getClass().getResourceAsStream("/sprites/health/1health.png"));
			twoBars = ImageIO.read(getClass().getResourceAsStream("/sprites/health/2health.png"));
			threeBars = ImageIO.read(getClass().getResourceAsStream("/sprites/health/3health.png"));
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
			image = threeBars;
			break;
		case 2:
			image = twoBars;
			break;
		case 1:
			image = oneBar;
			break;
		default:
			image = zeroBars;
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
