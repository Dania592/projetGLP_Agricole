package gui.Farm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import data.notion.basic.Element;
import process.game.ElementManager;

public class KeyControls implements KeyListener,Serializable {

	private ElementManager manager ;
	private Element selected ;
	private Board board;
	
	private boolean enterTouchPressed = false;

	public KeyControls(ElementManager manager , Element selected, Board board ) {
		this.manager=manager;
		this.selected = selected;
		this.board = board;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char code = e.getKeyChar();
		if(selected!=null && !selected.isStatique()) {
			switch(code) {
			case KeyEvent.VK_UP:
				manager.moveUp(selected);
				break;
			case KeyEvent.VK_DOWN :
				manager.moveDown(selected);			
				break;

			case KeyEvent.VK_LEFT:
				manager.moveLeft(selected);
				break;
			case KeyEvent.VK_RIGHT:
				manager.moveRight(selected);
				break;
			default:
				break;
			}		
		}
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(selected!=null && !selected.isStatique()) {
			switch(code) {
			case KeyEvent.VK_UP:
				// ajouter un gestionnaire de mouvement des element dans le element manager
				manager.moveUp(selected);
				break;
			case KeyEvent.VK_DOWN :
				manager.moveDown(selected);			
				break;

			case KeyEvent.VK_LEFT:
				manager.moveLeft(selected);
				break;
			case KeyEvent.VK_RIGHT:
				manager.moveRight(selected);
				break;
			default:
				break;
			}		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//manager.stop(e.getKeyCode());		
	}


	public Element getSelected() {
		return selected;
	}


	public void setSelected(Element selected) {
		this.selected = selected;
	}



}
