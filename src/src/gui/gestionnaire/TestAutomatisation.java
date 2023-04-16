package gui.gestionnaire;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JProgressBar;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestAutomatisation extends JFrame{
	public TestAutomatisation() {
		getContentPane().setLayout(null);
		
		JPanel Panel1 = new JPanel();
		Panel1.setBackground(new Color(128, 255, 255));
		Panel1.setBounds(10, 10, 1038, 686);
		getContentPane().add(Panel1);
		Panel1.setLayout(null);
		
		JLayeredPane panelTab1 = new JLayeredPane();
		JLayeredPane panelTab2 = new JLayeredPane();
		
		JPanel containerPanel = new JPanel();
		CardLayout cardLayout = new CardLayout();
		
		
		panelTab1.setLayout(cardLayout);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 41, 744, 635);
		tabbedPane.setBackground(new Color(0, 255, 255));
		tabbedPane.addTab("Tab 1", panelTab1);
		tabbedPane.getTabComponentAt(0);
		
		
		JPanel inPanel1 = new JPanel();
		inPanel1.setBackground(new Color(0, 64, 128));
		panelTab1.add(inPanel1, "Card1");
		
		JPanel inPanel2 = new JPanel();
		inPanel2.setBackground(new Color(0, 0, 255));
		panelTab1.add(inPanel2, "Card2");
		
		
		tabbedPane.addTab("Tab 2", panelTab2);
		Panel1.add(tabbedPane);
		
		JButton btn1 = new JButton("Next");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(panelTab1);
			}
		});
		btn1.setBounds(10, 10, 85, 21);
		Panel1.add(btn1);
		
		JButton btn2 = new JButton("Previous");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.previous(panelTab1);
			}
		});
		btn2.setBounds(119, 10, 85, 21);
		Panel1.add(btn2);
		
		JPanel panier = new JPanel();
		panier.setBounds(764, 43, 264, 633);
		Panel1.add(panier);
		panier.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 318, 244, 42);
		panier.add(progressBar);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		TestAutomatisation testAutomatisation = new TestAutomatisation();
	}
}
