package gui.gestionnaire;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

public class Next implements MouseListener{
	
	private CardLayout layout;
	private JTabbedPane container;
	private ArrayList<Container> containers;
	
	Next(CardLayout layout,JTabbedPane container,ArrayList<Container> containers){
		this.layout = layout;
		this.container = container;
		this.containers = containers;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		layout.next(containers.get(container.getSelectedIndex()));
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
