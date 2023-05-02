package gui.Farm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import process.game.ElementManager;
import data.stucture_base.Element;

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
		if(!selected.isStatique()) {
			switch(code) {
			case 'z':
				// ajouter un gestionnaire de mouvement des element dans le element manager 
				manager.moveUp(selected);
				break;
			case 's':
				manager.moveDown(selected);			
				break;

			case 'q':
				manager.moveLeft(selected);
				break;
			case 'd':
				manager.moveRight(selected);
				break;
			default:
				break;
			}		
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
//		int code = e.getKeyCode();
//		if (board.getGameState() == board.optionState) {
//			optionsState(code);
//		}
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
//
//	public void setGameState(int code) {
//		if (code == KeyEvent.VK_ESCAPE) {
//			board.setGameState(board.optionState);
//		} else if (code == KeyEvent.VK_P) {
//			board.setGameState(board.pauseState);
//		}
//	}
//
//	public void optionsState(int code) {
//		if (code == KeyEvent.VK_ESCAPE) {
//			board.setGameState(board.playState);
//		} else if (code == KeyEvent.VK_ENTER) {
//			enterTouchPressed = true;
//		}
//	}


}
