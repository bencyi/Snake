package main.pkg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Trail extends GameObject {

	private double alpha = 5;
	private double life;
	private Handler handler;
	private Color color;
	private int width;
	private int height;
	Random r = new Random();

	public Trail(int x, int y, ID id, Color color, int width, int height, double life, Handler handler, double alpha) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		this.alpha = alpha;
	}

	public void tick() {
		alpha -= (.5 - .05);

		
	if(alpha <= .5)
		handler.removeObject(this);
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);


	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
}
