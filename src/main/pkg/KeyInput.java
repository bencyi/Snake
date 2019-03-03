package main.pkg;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				// key events for player 1

				if (key == KeyEvent.VK_W && tempObject.getVelY() != 4) {
					tempObject.setVelX(0);
					tempObject.setVelY(-4);
				}
				if (key == KeyEvent.VK_S && tempObject.getVelY() != -4) {
					tempObject.setVelY(4);
					tempObject.setVelX(0);
				}
				if (key == KeyEvent.VK_D && tempObject.getVelX() != -4) {
					tempObject.setVelX(4);
					tempObject.setVelY(0);
				}
				if (key == KeyEvent.VK_A && tempObject.getVelX() != 4) {
					tempObject.setVelX(-4);
					tempObject.setVelY(0);
				}

			}
		}

		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

}
