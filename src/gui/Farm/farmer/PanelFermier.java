package gui.Farm.farmer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import data.acteur.Fermier;
import data.configuration.GameConfiguration;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.RoundedPanel;
import gui.gestionnaire.WindowDispose;

public class PanelFermier extends JPanel{

	
	private static final long serialVersionUID = 1L;

	private RoundedPanel fermier_actuelle_panel ;
	private JLabel[] personnages = new JLabel[6];
	private JLabel actuelle ;
	private Fermier fermier;
	private JFrame frame ;
	private FermierGui fermierGui ;
	 
	public PanelFermier(Fermier fermier , JFrame frame , FermierGui fermierGui) {
		super();
		this.frame=frame;
		this.fermierGui=fermierGui;
		this.fermier= fermier ;
		init();
	}
	
	public void init() {	
		setBounds(0, 0, WIDTH, HEIGHT);
		setLayout(null);
		setFocusable(true);
		
		fermier_actuelle_panel = new PanelPersonnage(fermier.getPackageImage());
		actuelle = new JLabel(new ImageIcon(fermier.getPackageImage()+File.separator+"stand.png"));
		actuelle.setBounds(25,20,100,150);
		fermier_actuelle_panel.add(actuelle);
		fermier_actuelle_panel.setBounds(50, 30, 150, 200);
		add(fermier_actuelle_panel);
		
		JLabel choisir = paintLabel("Choisir son avatar", 50, 300, 200, 40, "choisir_avatar.png");
		choisir.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 18));
		choisir.setForeground(Color.white);
		add(choisir);
		
//		JLabel retour = paintLabel("Retour au jeu", 380, 600,200, 40, "choisir_avatar.png");
//		retour.addMouseListener(new Retour());
//		retour.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 18));
//		retour.setForeground(Color.white);
//		add(retour);
		
		addPersonnage();
		addNom();
		addAge();
		addPlanning();
		addSolde();
	}
	
	public JLabel[] getPersonnages() {
		return personnages;
	}
	
	public void addNom() {
		JLabel nom = paintLabel(fermier.getName(), 450, 50, 200, 40, "choisir_avatar.png");
		nom.setFont(new Font(Font.SANS_SERIF , Font.BOLD , 18));
		nom.setForeground(Color.white);
		add(nom);
	}
	
	public void addAge() {
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		JLabel age = paintLabel(formater.format(fermier.getDateNaissance()), 600, 150, 250, 55, "age.png");
		age.setFont(new Font(Font.SANS_SERIF , Font.BOLD , 18));
		age.setForeground(Color.white);
		add(age);
	}
	public void addPlanning() {
		JLabel nom = paintLabel("Planning",250, 150, 250, 50, "planning.png");
		nom.setFont(new Font(Font.SANS_SERIF , Font.BOLD , 18));
		nom.setForeground(Color.white);
		add(nom);
	}
	
	public void addSolde() {
		JLabel nom = paintLabel(fermier.getBankAccount().getSolde()+" $",420, 250, 250, 55, "solde.png");
		nom.setFont(new Font(Font.SANS_SERIF , Font.BOLD , 18));
		nom.setForeground(Color.white);
		add(nom);
	}
	
	public void addPersonnage() {
		int dx = 23;
		for(int i = 0 ; i<personnages.length ; i++) {
			PanelPersonnage personne =  new PanelPersonnage(GameConfiguration.IMAGE_PATH+"Fermier"+File.separator+"personnage"+i);
			personne.setBounds(dx, 380, 130, 200);
			personnages[i] = new JLabel(new ImageIcon(GameConfiguration.IMAGE_PATH+"Fermier"+File.separator+"personnage"+i+File.separator+"stand.png"));
			personnages[i].addMouseListener(new ChangeAvatarListner(GameConfiguration.IMAGE_PATH+"Fermier"+File.separator+"personnage"+i ));
			personnages[i].setBounds(25,20 ,80,150);
			personne.add(personnages[i] );
			add(personne);
			dx+= 145 ;
		}
	}
	
	public JLabel paintLabel(String text, int x, int y, int w, int h , String image ) {
	    ImageIcon icon = new ImageIcon(GameConfiguration.IMAGE_PATH+image);

	    JLabel label= new JLabel() {
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
		        g.drawImage(icon.getImage(), 2, 2, null);
		        super.paintComponent(g);
		    }
	    };

		label.setBounds(x, y, w, h);
		label.setHorizontalAlignment(JLabel.CENTER);
	    label.setOpaque(false);
	    label.setText(text);
	    return label;
	}
	

	public  void paintComponent(Graphics g) {
		
		Graphics2D graphic = (Graphics2D)g;
		graphic.setColor(GeneralPaintStrategy.DARK_BROWN);
		graphic.setStroke(new BasicStroke(3f));
		graphic.drawLine(260, 320, 880, 320);
		
	}
	
	private class ChangeAvatarListner implements MouseListener{

		private String path ;
		
		ChangeAvatarListner(String path ) {
			this.path=path;
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			fermier.changeAvatar(path);
			fermier.setImage(path+File.separator+"stand.png");			
			actuelle.setIcon(new ImageIcon(path+File.separator+"stand.png"));
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class Retour implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			new WindowDispose(PanelFermier.this.fermierGui, frame);
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
