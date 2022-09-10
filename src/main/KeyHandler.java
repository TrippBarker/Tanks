package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean moveUp, moveDown, moveLeft, moveRight, fireUp, fireDown, fireLeft, fireRight;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			moveUp = true;
		}
		if (code == KeyEvent.VK_S) {
			moveDown = true;
		}
		if (code == KeyEvent.VK_A) {
			moveLeft = true;
		}
		if (code == KeyEvent.VK_D) {
			moveRight = true;
		}
		if (code == KeyEvent.VK_UP) {
			fireUp = true;
		}
		if (code == KeyEvent.VK_DOWN) {
			fireDown = true;
		}
		if (code == KeyEvent.VK_LEFT) {
			fireLeft = true;
		}
		if (code == KeyEvent.VK_RIGHT) {
			fireRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			moveUp = false;
		}
		if (code == KeyEvent.VK_S) {
			moveDown = false;
		}
		if (code == KeyEvent.VK_A) {
			moveLeft = false;
		}
		if (code == KeyEvent.VK_D) {
			moveRight = false;
		}
		if (code == KeyEvent.VK_UP) {
			fireUp = false;
		}
		if (code == KeyEvent.VK_DOWN) {
			fireDown = false;
		}
		if (code == KeyEvent.VK_LEFT) {
			fireLeft = false;
		}
		if (code == KeyEvent.VK_RIGHT) {
			fireRight = false;
		}
		
	}
	

}
