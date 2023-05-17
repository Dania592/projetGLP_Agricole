package gui.gestionnaire.contolleurs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class WindowDispose extends WindowAdapter {
	
	private JFrame frame;
	private JFrame parent;
 
    public WindowDispose(JFrame frame, JFrame parent) {
    	this.frame = frame;
    	this.parent = parent;
    }
    
    public void windowClosing(WindowEvent e){
    	if (parent!=null) {
    		parent.setVisible(true);
    		parent.setEnabled(true);
    		parent.revalidate();
    		parent.repaint();
    	}
    	frame.dispose();
    }

}
