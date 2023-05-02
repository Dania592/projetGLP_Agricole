package gui.Farm;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import data.notion.basic.Element;
import process.game.ElementManager;

public class MouseHandler implements MouseListener,Serializable{
	
	private ElementManager elementManager;
	private FarmPaintStrategy farmPaintStrategy = new FarmPaintStrategy();
	private Board board;
	
	public MouseHandler(ElementManager elementManager, Board board) {
		this.elementManager = elementManager;
		this.board = board;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Element element = elementManager.search(e.getX(), e.getY());

		board.setClicked(element);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	//TODO rendre graphique
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {}

}