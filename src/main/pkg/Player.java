package main.pkg;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import main.pkg.Game.STATE;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	HUD hud;
	private double alpha = 5;
	GameObject tempObject;
	Trail trail;
	Game game;
	int numCollisions = 0;

	public Player(int x, int y, ID id, Handler handler, HUD hud, Game game) {

		super(x, y, id);

		this.hud = hud;

		this.handler = handler;

		this.game = game;

	}

	public void tick() {

		// collision();
		x += velX;
		y += velY;

		x = Game.clamp(x, 0, Game.WIDTH - 39);
		y = Game.clamp(y, 0, Game.HEIGHT - 68);

		if (y <= 0 || y >= Game.HEIGHT - 68)
			game.gameState = STATE.GAMEOVER;
		if (x <= 0 || x >= Game.WIDTH - 39)
			game.gameState = STATE.GAMEOVER;
		
		trail = new Trail(x, y, ID.Trail, Color.green, 32, 32, .5, handler, alpha);
		handler.addObject(trail);

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Food) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					numCollisions++;
				//	handler.removeObject(trail);
					alpha+=4;
				//	handler.addObject(new Trail(x, y, ID.Trail, Color.green, 32, 32, .5, handler, alpha));
					handler.removeObject(tempObject);
					handler.addObject(new Food(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 80), ID.Food, handler));
					hud.setSize(hud.getSize() + 1);
				}
				
				if(this.getBounds().intersects(trail.getBounds())) {
					//game.gameState = STATE.GAMEOVER;
				}
			}

		}

	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 32, 32);
		g.setColor(Color.WHITE);
		g.drawRect(x+2, y+2, 4, 4);
	}

}
