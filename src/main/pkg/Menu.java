package main.pkg;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import main.pkg.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		//Start button
		if(mouseOn(mx, my, 265, 200, 100, 64) && game.gameState == STATE.MENU) {
			game.gameState = STATE.GAME;
			handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, hud, game));
			handler.addObject(new Food(r.nextInt(Game.WIDTH - 22), r.nextInt(Game.HEIGHT - 74), ID.Food, handler));
		}
		
		//Help button
		if(mouseOn(mx, my, 165, 200, 100, 64) && game.gameState == STATE.MENU) {
			game.gameState = STATE.HELP;
		}
		
		//Back button in Help
		if(mouseOn(mx, my, 265, 385, 98, 50) && game.gameState == STATE.HELP) {
			game.gameState = STATE.MENU;
		}
		
		//Exit button in GameOver
		if(mouseOn(mx, my, 265, 350, 100, 50) && game.gameState == STATE.GAMEOVER) {
			game.gameState = STATE.MENU;
			System.exit(1);
		}
		
		//Quit button on menu
		if(mouseOn(mx, my, 365, 200, 100, 64) && game.gameState == STATE.MENU) {
			System.exit(1);
		}
	}
	
	public void spawnSnakes() {
		SnakeAnimation greenSnake = new SnakeAnimation(0, 0, ID.Animation, handler, Color.GREEN, game, 20);
		SnakeAnimation food = new SnakeAnimation(0, 80, ID.Animation, handler, Color.cyan, game, 1);
		handler.addObject(greenSnake);
		handler.addObject(food);

		if(game.gameState == STATE.GAME) {
			handler.removeObject(greenSnake);
			handler.removeObject(food);
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	private boolean mouseOn(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}
			return false;
		}
		return false;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if(game.gameState == STATE.MENU) {
		Font font = new Font("arial", 1, 50);
		Font f2 = new Font("arial", 1, 30);
		g.setFont(font);
		g.setColor(Color.GREEN);
		g.drawString("Ben's Snake Game", 100, 120);
		g.setFont(f2);
		g.setColor(Color.white);
		
		
		//Start button
		g.drawRect(265, 200, 100, 64);
		g.drawString("Start", 280, 243);
				
		//Help button
		g.drawRect(165, 200, 100, 64);
		g.drawString("Help", 183, 243);
		
		//Quit button
		g.drawRect(365, 200, 100, 64);
		g.drawString("Quit", 383, 243);
		
		}
		
		else if(game.gameState == STATE.HELP) {
			Font font = new Font("arial", 1, 50);
			Font f2 = new Font("arial", 1, 25);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Help", 270, 100);
			g.setFont(f2);
			g.drawString("1. Use the WASD keys to move around the screen", 25 , 175);
			g.drawString("2. Eat the blue food that appears on the screen", 25, 225);
			g.drawString("3. Do not hit the edges or yourself, or you'll die!", 25, 275);
			g.drawString("4. Beat the current highscore, and have fun", 25, 325);
			g.drawRect(265, 385, 98, 50);
			g.drawString("Back", 283, 420);
		}
		
		else if(game.gameState == STATE.GAMEOVER) {
			Font font = new Font("arial", 1, 50);
			Font f2 = new Font("arial", 1, 25);
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString("You DIED.", 200, 200);
			g.setColor(Color.white);
			g.setFont(f2);
			g.drawRect(265, 350, 100, 50);
			g.drawString("Exit", 290, 383);
		}
	}

}
