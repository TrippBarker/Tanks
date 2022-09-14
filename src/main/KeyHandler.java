package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean redMoveUp, redMoveDown, redMoveLeft, redMoveRight, greenMoveUp, greenMoveDown, greenMoveLeft, greenMoveRight;
	public boolean redFireUp, redFireDown, redFireLeft, redFireRight, greenFireUp, greenFireDown, greenFireLeft, greenFireRight;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			redMoveUp = true;
		}
		if (code == KeyEvent.VK_S) {
			redMoveDown = true;
		}
		if (code == KeyEvent.VK_A) {
			redMoveLeft = true;
		}
		if (code == KeyEvent.VK_D) {
			redMoveRight = true;
		}
		if (code == KeyEvent.VK_Y) {
			redFireUp = true;
		}
		if (code == KeyEvent.VK_H) {
			redFireDown = true;
		}
		if (code == KeyEvent.VK_G) {
			redFireLeft = true;
		}
		if (code == KeyEvent.VK_J) {
			redFireRight = true;
		}
		if (code == KeyEvent.VK_UP) {
			greenMoveUp = true;
		}
		if (code == KeyEvent.VK_DOWN) {
			greenMoveDown = true;
		}
		if (code == KeyEvent.VK_LEFT) {
			greenMoveLeft = true;
		}
		if (code == KeyEvent.VK_RIGHT) {
			greenMoveRight = true;
		}
		if (code == KeyEvent.VK_NUMPAD5) {
			greenFireUp = true;
		}
		if (code == KeyEvent.VK_NUMPAD2) {
			greenFireDown = true;
		}
		if (code == KeyEvent.VK_NUMPAD1) {
			greenFireLeft = true;
		}
		if (code == KeyEvent.VK_NUMPAD3) {
			greenFireRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			redMoveUp = false;
		}
		if (code == KeyEvent.VK_S) {
			redMoveDown = false;
		}
		if (code == KeyEvent.VK_A) {
			redMoveLeft = false;
		}
		if (code == KeyEvent.VK_D) {
			redMoveRight = false;
		}
		if (code == KeyEvent.VK_Y) {
			redFireUp = false;
		}
		if (code == KeyEvent.VK_H) {
			redFireDown = false;
		}
		if (code == KeyEvent.VK_G) {
			redFireLeft = false;
		}
		if (code == KeyEvent.VK_J) {
			redFireRight = false;
		}
		if (code == KeyEvent.VK_UP) {
			greenMoveUp = false;
		}
		if (code == KeyEvent.VK_DOWN) {
			greenMoveDown = false;
		}
		if (code == KeyEvent.VK_LEFT) {
			greenMoveLeft = false;
		}
		if (code == KeyEvent.VK_RIGHT) {
			greenMoveRight = false;
		}
		if (code == KeyEvent.VK_NUMPAD5) {
			greenFireUp = false;
		}
		if (code == KeyEvent.VK_NUMPAD2) {
			greenFireDown = false;
		}
		if (code == KeyEvent.VK_NUMPAD1) {
			greenFireLeft = false;
		}
		if (code == KeyEvent.VK_NUMPAD3) {
			greenFireRight = false;
		}
		
	}
	

}
