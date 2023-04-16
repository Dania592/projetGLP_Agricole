package gui.Farm;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JPanel;

import data.flore.terrains.Terrain;
import data.structure.Refuge;
import data.structure.Structure;
import data.stucture_base.Element;
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
		if (element instanceof Terrain) {
			Terrain terrain = (Terrain)element;
			JPanel choixTerrain = farmPaintStrategy.paint(terrain, terrain.getActions(), board.getFarm().getManager().getMapManager().getMap());
			board.setChoixTerrain(choixTerrain);
		}
		System.out.println("element séléctionnée "+ element);
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