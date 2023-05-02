package gui.gestionnaire.gestionnairesGUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;

import gui.Farm.Board;

public class UIGraph {
	
	private Board board;
	private Graphics g2;
	
	public UIGraph(Board board) {
		this.board = board;
	}
	
	public void draw(Graphics g) {
		this.g2 = g;
		if (board.getGameState() == board.optionState) {
			drawOptionScreen();
		} else if (board.getGameState() == board.pauseState) {
			drawPauseScreen();
		}
	}
	
	public void drawOptionScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// sous fenêtre
		int frameX = 50;
		int frameY = 50;
		int frameWidth = 200;
		int frameHeight = 200;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
	}
	
	public void drawPauseScreen() {
		
		// sous fenêtre
		int frameX = 50;
		int frameY = 50;
		int frameWidth = 200;
		int frameHeight = 200;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		g2.setColor(Color.blue);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		String text = "Pause";
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = board.getWidth()/2 - length/2;
		int y = board.getHeight()/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawSubWindow(int x, int y, int w, int h) {
				
		Color black = new Color(0,0,0, 210);
		g2.setColor(black);
		g2.fillRoundRect(x, y, w, h, 35, 35);
	}
	
}
