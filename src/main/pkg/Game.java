package main.pkg;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.event.MouseEvent;

import main.pkg.Game.STATE;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1263205318889995195L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;

	private Handler handler;
	private HUD hud;
	private Menu menu;

	public enum STATE {
		MENU, GAME, HELP, GAMEOVER;
	};

	public STATE gameState = STATE.MENU;

	public Game() {
		handler = new Handler();
		hud = new HUD();

		menu = new Menu(this, handler, hud);

		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		menu.spawnSnakes();
	
		
		new Window(WIDTH, HEIGHT, "Ben's Snake Game", this);
		

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus(); // dont have to press screen to have keyboard input
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
		if (gameState == STATE.GAME) {
			hud.tick();
		} else if (gameState == STATE.MENU)
			menu.tick();
		else if (gameState == STATE.HELP)
			menu.tick();
		else if (gameState == STATE.GAMEOVER)
			menu.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		if (gameState == STATE.GAME) {
			hud.render(g);

		} else if (gameState == STATE.MENU) {
			menu.render(g);
		}

		else if (gameState == STATE.HELP) {
			menu.render(g);
		}

		else if (gameState == STATE.GAMEOVER) {
			menu.render(g);
		}

		g.dispose();
		bs.show();
	}

	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;

		else if (var <= min)
			return var = min;

		else
			return var;
	}

	public static void main(String[] args) {
		new Game();
	}

}
