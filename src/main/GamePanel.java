package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import tank.*;
import tile.*;

public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile size
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile size
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 Pixels wide
	public final int screenHeight = tileSize * maxScreenRow; // 576 Pixels tall
	int fps = 60;
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public BulletHitChecker bhChecker = new BulletHitChecker(this);
	public SmokeHandler sHandler = new SmokeHandler(this);
	public GreenTank greenTank = new GreenTank(this, keyH, "greenTank");
	public Gun greenGun = new Gun(this, keyH, greenTank, "left");
	public Bullet greenBullet = new Bullet(this, keyH, greenTank);
	public HealthBar greenHealth = new HealthBar(this);
	public RedTank redTank = new RedTank(this, keyH, "redTank");
	public Gun redGun = new Gun(this, keyH, redTank, "right");
	public Bullet redBullet = new Bullet(this, keyH, redTank);
	public HealthBar redHealth = new HealthBar(this);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/fps;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			update();
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime /= 1000000;
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void update() {
		greenTank.update();
		greenGun.update();
		redTank.update();
		redGun.update();
		redBullet.update();
		greenBullet.update();
		sHandler.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		greenBullet.draw(g2);
		redBullet.draw(g2);
		greenTank.draw(g2);
		greenGun.draw(g2);
		redTank.draw(g2);
		redGun.draw(g2);
		sHandler.draw(g2);
		greenHealth.draw(g2);
		redHealth.draw(g2);
		greenHealth.updatePos(greenTank.screenX, greenTank.screenY);
		redHealth.updatePos(redTank.screenX, redTank.screenY);
		g2.dispose();
		
	}
	
}
