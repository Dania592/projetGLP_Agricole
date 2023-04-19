package gui.gestionnaire;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class TestAutomatisation extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	public static int pos = 0;
	
	public TestAutomatisation() {
		
		getContentPane().setLayout(null);
		
		JPanel container = new JPanel();
		container.setBounds(0,0,300,300);
		container.setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
		panel.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(panel);
		
		container.add(scrollPane, BorderLayout.CENTER);
		JButton validate = new JButton("ADD PANEL");
		validate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel paneel = new JPanel();
				paneel.setLocation(10, pos*(30) +10);
				paneel.setBackground(Color.BLACK);
				paneel.setPreferredSize(new Dimension(200,20));
				panel.add(paneel);
				System.out.println(pos + "\n");
				pos++;
				panel.revalidate();
				panel.repaint();
			}
		});
		container.add(validate, BorderLayout.NORTH);
		
		add(container);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		JPanel Panel1 = new JPanel();
//		Panel1.setBackground(new Color(128, 255, 255));
//		Panel1.setBounds(10, 10, 1038, 686);
//		getContentPane().add(Panel1);
//		Panel1.setLayout(null);
//		
//		JLayeredPane panelTab1 = new JLayeredPane();
//		JLayeredPane panelTab2 = new JLayeredPane();
//		
//		CardLayout cardLayout = new CardLayout();
//		
//		
//		panelTab1.setLayout(cardLayout);
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane.setBounds(10, 41, 744, 635);
//		tabbedPane.setBackground(new Color(0, 255, 255));
//		tabbedPane.addTab("Tab 1", panelTab1);
//		tabbedPane.getTabComponentAt(0);
//		
//		
//		JPanel inPanel1 = new JPanel();
//		inPanel1.setBackground(new Color(0, 64, 128));
//		panelTab1.add(inPanel1, "Card1");
//		inPanel1.setLayout(null);
//		
//		JPanel panel = new JPanel();
//		panel.setBounds(99, 66, 200, 260);
//		inPanel1.add(panel);
//		panel.setLayout(null);
//		
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Dual Computer\\Desktop\\agricole\\EssaiMarche\\src\\sources\\mouton.png"));
//		lblNewLabel.setBounds(41, 10, 113, 111);
//		panel.add(lblNewLabel);
//		
//		JLabel lblNewLabel_1 = new JLabel("Mouton");
//		lblNewLabel_1.setBounds(79, 139, 45, 13);
//		panel.add(lblNewLabel_1);
//		
//		JLabel lblNewLabel_1_1 = new JLabel("20");
//		lblNewLabel_1_1.setBounds(79, 171, 45, 23);
//		panel.add(lblNewLabel_1_1);
//		
//		JPanel inPanel2 = new JPanel();
//		inPanel2.setBackground(new Color(0, 0, 255));
//		panelTab1.add(inPanel2, "Card2");
//		
//		
//		tabbedPane.addTab("Tab 2", panelTab2);
//		Panel1.add(tabbedPane);
//		
//		JButton btn1 = new JButton("Next");
//		btn1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				cardLayout.next(panelTab1);
//			}
//		});
//		btn1.setBounds(10, 10, 85, 21);
//		Panel1.add(btn1);
//		
//		JButton btn2 = new JButton("Previous");
//		btn2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				cardLayout.previous(panelTab1);
//			}
//		});
//		btn2.setBounds(119, 10, 85, 21);
//		Panel1.add(btn2);
//		
//		JPanel panier = new JPanel();
//		panier.setBounds(764, 43, 264, 633);
//		Panel1.add(panier);
//		panier.setLayout(null);
//		
//		JProgressBar progressBar = new JProgressBar();
//		progressBar.setBounds(10, 318, 244, 42);
//		panier.add(progressBar);

	}
	
	public static void main(String[] args) {
		TestAutomatisation testAutomatisation = new TestAutomatisation();
	}
}
