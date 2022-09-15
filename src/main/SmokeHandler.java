package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tank.RedTank;
import tank.Tank;

public class SmokeHandler {
	public int screenX, screenY;
	public boolean tankDied = false;
	GamePanel gp;
	public BufferedImage smoke1, smoke2, smoke3, smoke4;
	public int imageTimer = 0;
	
	public SmokeHandler(GamePanel gp) {
		this.gp = gp;
		getSmokeImage();
	}
	
	public void getSmokeImage() {
		try {
			smoke1 = ImageIO.read(getClass().getResourceAsStream("/sprites/smoke/smoke1.png"));
			smoke2 = ImageIO.read(getClass().getResourceAsStream("/sprites/smoke/smoke2.png"));
			smoke3 = ImageIO.read(getClass().getResourceAsStream("/sprites/smoke/smoke3.png"));
			smoke4 = ImageIO.read(getClass().getResourceAsStream("/sprites/smoke/smoke4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tankDied(Tank deadTank) {
		if (deadTank instanceof RedTank) {
			screenX = gp.redTank.screenX;
			screenY = gp.redTank.screenY - (gp.tileSize / 4);
		} else {
			screenX = gp.greenTank.screenX;
			screenY = gp.greenTank.screenY - (gp.tileSize / 4);
		}
		tankDied = true;
	}
	
	public void update () {
		if (tankDied) {
			imageTimer++;
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		if(imageTimer < 20) {
			image = smoke1;
		} else if (imageTimer < 40) {
			image = smoke2;
		} else {
			if(imageTimer < 60) {
				image = smoke3;
			} else if ( imageTimer < 80) {
				image = smoke4;
				if (imageTimer == 79) {
					imageTimer = 41;
				}
			}
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
