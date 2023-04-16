package gui.gestionnaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundedPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color backgroundColor;
	private int cornerRadius = 15;

	public RoundedPanel(int x, int y, int w, int h, LayoutManager layout, int radius, Color color) {
		super(layout);
		setBounds(x, y, w, h);
		cornerRadius = radius;
		backgroundColor = color;
	}
	
	public RoundedPanel(LayoutManager layout, int radius) {
		super(layout);
		cornerRadius = radius;
	}

	public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
		super(layout);
		cornerRadius = radius;
		backgroundColor = bgColor;
	}

	public RoundedPanel(int radius) {
		super();
		cornerRadius = radius;
	}

	public RoundedPanel(int radius, Color bgColor) {
		super();
		cornerRadius = radius;
		backgroundColor = bgColor;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension arcs = new Dimension(cornerRadius, cornerRadius);
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//Draws the rounded panel with borders.
		if (backgroundColor != null) {
			graphics.setColor(backgroundColor);
		} else {
			graphics.setColor(getBackground());
		}
		graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
		graphics.setColor(getForeground());
		graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
	}


	
//	public void paintComponent(Graphics g, Terrain terrain, Map map) {
//		super.paintComponent(g);
//		Dimension arcs = new Dimension(cornerRadius, cornerRadius);
//		int width = getWidth();
//		int height = getHeight();
//		int x = terrain.getPosition().getColonne_init()*GameConfiguration.CASE_DIMENSION +  map.getX() + 50;
//		int y = terrain.getPosition().getLigne_init()*GameConfiguration.CASE_DIMENSION + map.getY() + 50;
//		Graphics2D graphics = (Graphics2D) g;
//		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//		//Draws the rounded panel with borders.
//		if (backgroundColor != null) {
//			graphics.setColor(backgroundColor);
//		} else {
//			graphics.setColor(getBackground());
//		}
//		graphics.fillRoundRect(x, y, width-1, height-1, arcs.width, arcs.height); //paint background
//		graphics.setColor(getForeground());
//		graphics.drawRoundRect(x, y, width-1, height-1, arcs.width, arcs.height); //paint border
//	}
}
