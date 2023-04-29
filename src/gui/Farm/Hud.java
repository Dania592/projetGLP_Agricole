package gui.Farm;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import data.configuration.GameConfiguration;
import data.notification.Messagerie;
import data.time.Clock;
import data.time.CyclicCounter;
import gui.Farm.actions.ActionsPane;
import gui.Farm.choix.ChoixPanel;
import gui.Farm.farmer.FermierGui;
import gui.Farm.messagerie.MessageriePanel;
import gui.gestionnaire.Home;
import gui.gestionnaire.MarketGUI;
import gui.gestionnaire.PaintKeys;
import gui.statistique.TestStat;
import process.action.task.Task;
import process.game.SaveFarm;
import process.time.TimeManager;

public class Hud implements Serializable {

	private static final long serialVersionUID = 1L;

	private Board component;
	
	private JLabel adding = new JLabel();
	private JLabel home = new JLabel();
	
	private JLabel validate;
	private JLabel cancel;
	private JLabel farmer; 
	private JLabel save;
	private static JLabel message;
	private JLabel statistique;
	private ChoixPanel choixScroll;
	private ActionsPane actions;
	private MessageriePanel messagerie; 
	
		
	private JLabel time = new JLabel();
		
	
	public  Hud(Board component) {
		this.component=component;
	}
	
	public void remove_panels() {
		removeActionPane();
		removeChoix();
		removeMessagerie();
	}
	
	public void build() {	
		if(Arrays.asList(component.getComponents()).contains(validate)){
			component.remove(validate);
			component.remove(cancel);
			component.add(adding, JLayeredPane.DRAG_LAYER);
			component.add(home, JLayeredPane.DRAG_LAYER);
			component.add(message , JLayeredPane.DRAG_LAYER);
			component.add(statistique, JLayeredPane.DRAG_LAYER);
			
		}
		else {
			add_ADD();
			
			addHome();
		
			addMessage();
			
			addStat();
			
			profile();
		}
		time();

	}
	
	public void add_ADD() {
		adding.setBounds( GameConfiguration.X_ADD_LABEL, GameConfiguration.y_ADD_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		ImageIcon addIcon= new ImageIcon("src"+File.separator+"ressources"+File.separator+"add.png");
		adding.setIcon(addIcon);
		adding.addMouseListener(new MouseHud());
		component.add(adding, JLayeredPane.DRAG_LAYER);
	}
	
	public void addHome() {
		home.setBounds( GameConfiguration.X_HOME_LABEL, GameConfiguration.Y_HOME_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		ImageIcon homeIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"home.png");
		home.setIcon(homeIcone);	
		home.addMouseListener(new MouseHud());
		component.add(home, JLayeredPane.DRAG_LAYER);	
	}
	
	public void addMessage() {
		String path = GameConfiguration.IMAGE_PATH+"message.png";
		if(Messagerie.getInstance().getMessages().size()>0) {
			path = GameConfiguration.IMAGE_PATH+"nv_message.png";
		}
		message = new JLabel(new ImageIcon(path));
		message.setBounds(50, GameConfiguration.Y_HOME_LABEL, GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		message.addMouseListener(new MouseHudMessage());
		component.add(message , JLayeredPane.DRAG_LAYER);
	}
	
	public static void notification() {
		message.setIcon(new ImageIcon(GameConfiguration.IMAGE_PATH+"nv_message.png"));
	}
	
	public void addStat() {
		statistique = new JLabel(new ImageIcon(GameConfiguration.IMAGE_PATH+"stat.png"));
		statistique.setBounds(50, GameConfiguration.y_ADD_LABEL, GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		component.add(statistique , JLayeredPane.DRAG_LAYER);
		statistique.addMouseListener(new MouseHud());
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
		
		component.remove(statistique);
		component.remove(message);
	
	}
	
	public void profile() {
		// ajouter le mouse listener lors de la création des frames du jouer et fermier 
		
		save = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"save.png"));
		save.setBounds(GameConfiguration.WINDOW_WIDTH-100 , 10, GameConfiguration.WIDHT_LABEL ,  GameConfiguration.HEIGHT_LABEL);
		save.setToolTipText("Sauvegarder l'état de la ferme");
		save.addMouseListener(new MouseHud());
		component.add(save , JLayeredPane.DRAG_LAYER);
		
		
		farmer = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"farmer.png"));
		farmer.setBounds(10 , 10, GameConfiguration.WIDHT_LABEL ,  GameConfiguration.HEIGHT_LABEL);
		farmer.setToolTipText("Acceder au profil du fermier");
		farmer.addMouseListener(new MouseHud());
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
		
		time.setText(hour.toString()+" : "+minute.toString() /*+""+TimeManager.getInstance().getDay()); */+" : "+second.toString());
		
	}
	
	public void addingChoix() {
		component.getChoix().init();
		choixScroll = new ChoixPanel(component);
		choixScroll.setBounds(150, GameConfiguration.WINDOW_HEIGHT-200, GameConfiguration.WINDOW_WIDTH-300, 160);
		component.add(choixScroll, JLayeredPane.DRAG_LAYER);
	}
	
	public void removeChoix() {
		if(Arrays.asList(component.getComponents()).contains(choixScroll)) {
			component.remove(choixScroll);				
		}
	}
	
	public void changeState() {
		if (Arrays.asList(component.getComponents()).contains(choixScroll)) {
			removeChoix();
		} else {
			addingChoix();
		}
	}
	// passer en parametre la structure pour recuperer
	public void add_Actions(int x , int y , ArrayList<Task<?>> taches ) {
		if(!Arrays.asList(component.getComponents()).contains(actions)) {
			actions = new ActionsPane(taches, x , y , this);
			component.add(actions);		
		}
		else {
			component.remove(actions);
		}
	}
	
	public void removeActionPane() {
		if(Arrays.asList(component.getComponents()).contains(actions)) {
			component.remove(actions);			
		}
	}
	
	
	private class MouseHud implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(adding)) {
				changeState();
				if(Arrays.asList(component.getComponents()).contains(messagerie)) {
					component.remove(messagerie);
					addStat();
				}
			}
		
			if(e.getSource().equals(home)) {
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Hud.this.component);
					removeChoix();
					new Home(frame);
					frame.dispose();
				}
				
					if(e.getSource().equals(validate)) {
						component.getChoix().removeElement(component.getSelected());
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
					
						if(e.getSource().equals(save)) {
								SaveFarm save = new SaveFarm();
								save.serializationSave(GameConfiguration.FILE_NAME_SAVE, component.getFarm());
								new PopupSave(component);
							}
							else {
								if(e.getSource().equals(farmer)) {
									JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Hud.this.component);
									frame.dispose();
									new FermierGui(frame , component.getFarm().getFermier());
								}
								else {
									if(e.getSource().equals(statistique)) {
										JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Hud.this.component);
										frame.setVisible(false);
										new TestStat(frame);
										
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
	
	public  void removeMessagerie() {
		if (Arrays.asList(component.getComponents()).contains(messagerie)) {
			addStat();
			component.remove(messagerie);
			component.remove(message);
			addMessage();
		}
	}
	
	private class MouseHudMessage implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
				if (Arrays.asList(component.getComponents()).contains(messagerie)) {
					addStat();
					component.remove(messagerie);
				} else {
					messagerie = new MessageriePanel(Hud.this);
					component.remove(statistique);
					removeChoix();
					component.add(messagerie , JLayeredPane.DEFAULT_LAYER);
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
