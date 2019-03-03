package main.pkg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import main.pkg.Game.STATE;

public class SnakeAnimation extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private Trail trail;
	private Game game;
	private double alpha = 5;
	private Color color;
	int i;

	public SnakeAnimation(int x, int y, ID id, Handler handler, Color color, Game game, double alpha) {
		super(x, y, id);
		
		this.handler = handler;
		
		this.color = color;
		
		this.game = game;
		
		this.alpha = alpha;
		velX = 0;
		velY = 5;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 39);
		y = Game.clamp(y, 0, Game.HEIGHT - 65);
		
		if (x <= 0) {
			velY = 5;
			velX = 0;
		}
		
		if(y >= Game.HEIGHT- 66) {
			velY = 0;
			velX = 5;
		}
		
		if(x >= Game.WIDTH - 39) {
			velY = -5;
			velX = 0;
			if(y <= 0) {
				velY = 0;
				velX = -5;
			}
		}

		trail = new Trail(x, y, ID.Trail, color, 32, 32, .5, handler, alpha);
		handler.addObject(trail);
		
		despawn();
	}
	
	private void despawn() {
				if (game.gameState == STATE.GAME) {
					handler.removeObject(this);
				}
			}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	

}
