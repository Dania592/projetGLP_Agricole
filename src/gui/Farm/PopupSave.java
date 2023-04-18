package gui.Farm;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class PopupSave extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board parentFrame ;
	
	public PopupSave (Board frame) {
		parentFrame = frame ;
		//super(frame, "save");	
		setVisible(true);
		setSize(270,150);
		setLocationRelativeTo(null);
		init();
	}
	
	
	public void init() {
		
	Container content = getContentPane();
	content.setLayout(null);
	
	content.setBackground(new Color(255, 231, 171));
	JButton okButton = new JButton("Ok");
	okButton.setBounds(100,50 , 50 , 30);
	//okButton.setBackground(new Color(103, 148, 76));
	
	okButton.addActionListener(new Ok());
	content.add(okButton);
	
	JLabel label = new JLabel("Vous avez enregistez l'état de la ferme!!");
	
	label.setBounds(10,10,300,50);
	content.add(label);
	
	
	}
	
	private class Ok implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (parentFrame != null) {
				parentFrame.setFocusable(true);
				parentFrame.setEnabled(true);
			}
			dispose();
		}

	}
	
	
	public static void main (String[]args) {
		new PopupSave(null);
	}
}
