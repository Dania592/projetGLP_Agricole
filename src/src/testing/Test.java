package testing;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class Test extends JFrame{
	
	private static final long serialVersionUID = 1L;


	public Test(String title) {
		super(title);
		init();
		
	}
	
	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20,20,200,200);
		contentPane.add(panel);
		panel.setLayout(null);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(10,10,100,100);	
		panel.add(scroll);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JPanel newpanel = new JPanel();
		newpanel.setSize(200,200);
		scroll.setViewportView(newpanel);
		newpanel.add(new JLabel("hlooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(500,500);
	}
	
	
	public static void main(String[] args) {
		new Test("test scoll");
	}

}
