package main.pkg;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Rectangle;

public class Food extends GameObject {

	private Handler handler;

	public Food(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public void tick() {
	}


	public void render(Graphics g) {

		g.setColor(Color.cyan);
		g.fillRect(x, y, 32, 32);
	}

}
