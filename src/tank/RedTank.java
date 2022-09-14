package tank;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class RedTank extends Tank{

	public RedTank(GamePanel gp, KeyHandler keyH, String name) {
		this.gp = gp;
		this.keyH = keyH;
		this.name = name;
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		// Defines starting position
		screenX = gp.tileSize * 2;
		screenY = gp.tileSize * 2;
		// Defines how many pixels object moves per refresh
		speed = 4;
		// Defines starting direction
		direction = "right";
		// Defines hit box
		solidArea = new Rectangle(9, 9, 36, 36);
	}
}