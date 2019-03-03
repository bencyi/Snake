package main.pkg;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.*;

public class Window extends Canvas {

	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;

	private static final long serialVersionUID = 4023838095302213005L;

	public Window(int width, int height, String title, Game game) {

		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the game thread end when closing out
		frame.setResizable(false); // disallows the game window to be resized
		frame.setLocationRelativeTo(null); // starts the window in the middle of the screen rather than top left
		frame.add(game); // adds the game to the window
		frame.setVisible(true); // sets the frame to visible

		game.start(); // start method in our game

	}

}
