package main.pkg;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private int size = 0;
	private int level = 1;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Size: " + size, 15, 40);
		g.drawString("Difficulty Level: " + level, 15, 64);
		//g.drawString("High Score: " + Game.highScore, 15, 88);
	}
	
	
	public int getSize() {
		return size;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
}
