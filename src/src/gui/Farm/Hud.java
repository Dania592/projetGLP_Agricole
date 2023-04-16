package gui.Farm;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import data.configuration.GameConfiguration;
import data.time.Clock;
import data.time.CyclicCounter;
import process.game.SaveFarm;

public class Hud implements Serializable {
	
	private Board component;
	
	private JLabel adding = new JLabel();
	private JLabel home = new JLabel();
	
	private JLabel validate ;
	private JLabel cancel ;
	private JLabel farmer ; 
	private JLabel player ;
	private ChoixPanel choixScroll;
	
	
	private JLabel time = new JLabel();
		
	
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
		time();

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
		// ajouter le mouse listener lors de la création des frames du jouer et fermier 
		
		player = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"save.png"));
		player.setBounds(GameConfiguration.WINDOW_WIDTH-100 , 10, GameConfiguration.WIDHT_LABEL ,  GameConfiguration.HEIGHT_LABEL);
		player.setToolTipText("Enregisterz l'état de votre ferme");
		component.add(player , JLayeredPane.DRAG_LAYER);
		player.addMouseListener(new MouseHud());
		
		
		farmer = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"farmer.png"));
		farmer.setBounds(10 , 10, GameConfiguration.WIDHT_LABEL ,  GameConfiguration.HEIGHT_LABEL);
		farmer.setToolTipText("Acceder au profil du fermier");
		component.add(farmer , JLayeredPane.DRAG_LAYER);
	}

	public void time() {
		if(!Arrays.asList(component.getComponents()).contains(time)) {
			time.setBounds((GameConfiguration.WINDOW_WIDTH-120)/2, 10, 120, 20);
			time.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
			component.add(time);			
		}
		Clock clock = component.getFarm().getClock();
		CyclicCounter hour = clock.getHour();
		CyclicCounter minute = clock.getMinute();
		CyclicCounter second = clock.getSecond();
		
		time.setText(hour.toString()+" : "+minute.toString()+" : "+second.toString());
		
	}
	
	public void addingChoix() {
		choixScroll = new ChoixPanel(component);
		choixScroll.setBounds(50, GameConfiguration.WINDOW_HEIGHT-200, GameConfiguration.WINDOW_WIDTH-170, 160);
		component.add(choixScroll, JLayeredPane.DRAG_LAYER);
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
						else {
							if(e.getSource().equals(player)) {
								//System.out.println(component.getFarm().getTimeManager().getClock().getMinute().getValue());
								SaveFarm save = new SaveFarm();
								save.serializationSave(GameConfiguration.FILE_NAME_SAVE ,component.getFarm() );
								new PopupSave(component);
								//save.saveGui("choix.ser", component.getChoix());
								
								
							}
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
