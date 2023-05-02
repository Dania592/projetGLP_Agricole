package gui.gestionnaire.contolleurs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.Farm.Board;
import gui.gestionnaire.gestionnairesGUI.UIGraph;

public class KeyHandler implements KeyListener{

	private Board board;
	private UIGraph ui;
	
	public KeyHandler(Board board) {
		this.board = board;
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		int code = e.getKeyCode();
		setGameState(code);
		if (board.getGameState() == board.optionState) {
			optionsState(code);
		} else if (board.getGameState() == board.pauseState) {
			//pauseState(code);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		int code = e.getKeyCode();
//		if (board.getGameState() == board.optionState) {
//			optionsState(code);
//		} else if (board.getGameState() == board.pauseState) {
//			//pauseState(code);
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	
	public void setGameState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			board.setGameState(board.optionState);
		} else if (code == KeyEvent.VK_P) {
			board.setGameState(board.pauseState);
		}
	}
	
	public void optionsState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			board.setGameState(board.playState);
		}
	}

}
