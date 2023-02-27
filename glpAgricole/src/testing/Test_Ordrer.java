package testing;

public class Test_Ordrer {

	
	public static void main(String... args)
	{
		// Nouvelle fenetre (1)
		javax.swing.JFrame frame = new javax.swing.JFrame();
		// Panneau principal (2)
		javax.swing.JPanel contentPane = new javax.swing.JPanel();
		// Panneau contenant tabbed + spinner (3)
		javax.swing.JPanel composantPane = new javax.swing.JPanel();
		composantPane.setLayout(new java.awt.BorderLayout());
 
		// TabbedPane (4)
		javax.swing.JTabbedPane tabbedPane = new javax.swing.JTabbedPane();
		tabbedPane.addTab("Tab 1", new javax.swing.JPanel());
		tabbedPane.addTab("Tab 2", new javax.swing.JPanel());
		tabbedPane.addTab("Tab 3", new javax.swing.JPanel());
 
		// Spinner (5)
		javax.swing.JSpinner spinner = new javax.swing.JSpinner();
 
		// Ajout des composants dans (3)
		composantPane.add(spinner, java.awt.BorderLayout.NORTH);
		composantPane.add(tabbedPane, java.awt.BorderLayout.CENTER);
 
		// Création d'un panneau superposé au (2)
		javax.swing.JPanel glassPane = new javax.swing.JPanel();
		glassPane.setOpaque(true);
		glassPane.setBackground(java.awt.Color.RED);
		glassPane.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GREEN));
 
		// Ajout des composants au (2)
		contentPane.setLayout(new java.awt.GridBagLayout());
		contentPane.setPreferredSize(new java.awt.Dimension(300,300));
		java.awt.GridBagConstraints c = new java.awt.GridBagConstraints();
		c.gridx=0; c.gridy=0; c.weighty=1; c.weightx=1;
		c.fill = java.awt.GridBagConstraints.BOTH;
		contentPane.add(composantPane, c);
		contentPane.add(glassPane, c);
 
		// Ordre des composants affichés pour (2)
		contentPane.setComponentZOrder(glassPane, 0);
		contentPane.setComponentZOrder(composantPane, 1);
 
		// Gestion de la fenêtre (1)
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setVisible(true);
	}
}
