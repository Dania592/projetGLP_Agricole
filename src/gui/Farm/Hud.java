package gui.Farm;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

import data.configuration.GameConfiguration;

public class Hud {
	
	private Board component;
	
	private JLabel adding = new JLabel();
	private JLabel home = new JLabel();
	
	private JLabel validate ;
	private JLabel cancel ;
	private JLabel farmer ; 
	private JLabel player ;
	private JScrollPane choixScroll;
	
	public  Hud(Board component) {
		this.component=component;
	}
	
	public void build() {	
		if(Arrays.asList(component.getComponents()).contains(validate)){
			component.remove(validate);
			component.remove(cancel);
			component.add(adding, JLayeredPane.DRAG_LAYER);
			component.add(home, JLayeredPane.DRAG_LAYER);
		}
		else {
			adding.setBounds( GameConfiguration.X_ADD_LABEL, GameConfiguration.y_ADD_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
			ImageIcon addIcon= new ImageIcon("src"+File.separator+"ressources"+File.separator+"add.png");
			adding.setIcon(addIcon);
			adding.addMouseListener(new MouseHud());
			component.add(adding, JLayeredPane.DRAG_LAYER);
			
			
			home.setBounds( GameConfiguration.X_HOME_LABEL, GameConfiguration.Y_HOME_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
			ImageIcon homeIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"home.png");
			home.setIcon(homeIcone);	
			home.addMouseListener(new MouseHud());
			component.add(home, JLayeredPane.DRAG_LAYER);	
			
			profile();
		}

	}

	public void addValidation() {
		
		component.remove(adding);
		validate = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"valider.png"));
		validate.setBounds( GameConfiguration.X_ADD_LABEL, GameConfiguration.y_ADD_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		validate.addMouseListener(new MouseHud());
		validate.setToolTipText("Valider la position");
		component.add(validate);
	
		
		component.remove(home);
		cancel = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"annuler.png"));
		cancel.setBounds( GameConfiguration.X_HOME_LABEL, GameConfiguration.Y_HOME_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		cancel.addMouseListener(new MouseHud());
		cancel.setToolTipText("cancel l'operation");
		component.add(cancel, JLayeredPane.DRAG_LAYER);
	
	}
	
	public void profile() {
		// ajouter le mouse listener lors de la cr??ation des frames du jouer et fermier 
		
		player = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"player.png"));
		player.setBounds(GameConfiguration.WINDOW_WIDTH-100 , 10, GameConfiguration.WIDHT_LABEL ,  GameConfiguration.HEIGHT_LABEL);
		player.setToolTipText("Acceder au votre profil");
		component.add(player , JLayeredPane.DRAG_LAYER);
		
		
		farmer = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"farmer.png"));
		farmer.setBounds(10 , 10, GameConfiguration.WIDHT_LABEL ,  GameConfiguration.HEIGHT_LABEL);
		farmer.setToolTipText("Acceder au profil du fermier");
		component.add(farmer , JLayeredPane.DRAG_LAYER);
	}

	
	public void addingChoix() {
		choixScroll = new JScrollPane();
		ChoixPanel choix = new ChoixPanel( component.getFarm() , component);
		
		choixScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		choixScroll.setBounds(50, GameConfiguration.WINDOW_HEIGHT-200, GameConfiguration.WINDOW_WIDTH-170, 160);
		component.add(choixScroll, JLayeredPane.DRAG_LAYER);
		choixScroll.setViewportView(choix);
	}
	
	public void removeChoix() {
		component.remove(choixScroll);	
	}
	
	public void changeState() {
		if (Arrays.asList(component.getComponents()).contains(choixScroll)) {
			removeChoix();
		} else {
			addingChoix();
		}
	}
	
	private class MouseHud implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(adding)) {
				changeState();
			}
			else {
				if(e.getSource().equals(home)) {
					Hud.this.component.getParent().setVisible(false);
					//new Gestionnaire("gestionnaire", Hud.this.component.getParent());	
				}
				else {
					if(e.getSource().equals(validate)) {
						component.getHud().build();
						component.getSelected().setStatique(true);
						component.setSelected(component.getFarm().getFermier());
					}
					else {
						if(e.getSource().equals(cancel)) {
							component.getFarm().getManager().remove(component.getSelected());
							component.setSelected(component.getFarm().getFermier());
							component.getHud().build();
						}
					}
				}
			}
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
