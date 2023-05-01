package gui.Farm;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import data.configuration.GameConfiguration;
import data.finance.Banque;
import data.notification.Messagerie;
import data.time.Clock;
import data.time.CyclicCounter;
import gui.Farm.actions.ActionsPane;
import gui.Farm.choix.ChoixPanel;
import gui.Farm.farmer.FermierGui;
import gui.Farm.messagerie.AlertPane;
import gui.Farm.messagerie.MessageriePanel;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.Home;
import gui.statistique.TestStat;
import process.action.task.Task;
import process.game.Game;
import process.game.GameBuilder;
import process.game.MapManager;
import process.game.SaveFarm;

public class Hud implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Board component;
	
	private JLabel adding = new JLabel();
	private JLabel home = new JLabel();
	private JLabel extend = new JLabel();
	private JLabel validate;
	private JLabel cancel;
	private JLabel farmer; 
	private JLabel save;
	private JLabel soldeBg;
	private JLabel music ;
	private static JLabel message;
	private static JLabel statistique;
	private static ChoixPanel choixScroll;
	private ActionsPane actions;
	private static MessageriePanel messagerie; 
	private static AlertPane alert = new AlertPane();
	
		
	private JLabel solde = new JLabel();
	private JLabel time = new JLabel();


	public  Hud(Board component) {
		this.component=component;
	}

	public void remove_panels() {
		removeActionPane();
		removeChoix();
		removeMessagerie();
		removeAlert();
		if(!Arrays.asList(component.getComponents()).contains(statistique)) {
			component.add(statistique ,JLayeredPane.DRAG_LAYER);
		}
	}

	public void build() {	
		if(Arrays.asList(component.getComponents()).contains(validate)){
			component.remove(validate);
			component.remove(cancel);
			component.add(adding, JLayeredPane.DRAG_LAYER);
			component.add(home, JLayeredPane.DRAG_LAYER);
			component.add(message , JLayeredPane.DRAG_LAYER);
			component.add(statistique, JLayeredPane.DRAG_LAYER);
			component.add(soldeBg, JLayeredPane.DRAG_LAYER);
		}
		else {
			add_ADD();

			addHome();

			addMessage();

			addStat();

			profile();

			addExtend();

			save();
		}
		time();
		solde();

	}

	public void add_ADD() {
		adding.setBounds( GameConfiguration.X_ADD_LABEL, GameConfiguration.y_ADD_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		ImageIcon addIcon= new ImageIcon("src"+File.separator+"ressources"+File.separator+"ajouter.png");
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
		
	
	public void addExtend() {
		extend.setBounds(GameConfiguration.X_EXTEND_LABEL, GameConfiguration.Y_EXTEND_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		ImageIcon homeIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"extend.png");
		extend.setIcon(homeIcone);	
		extend.addMouseListener(new MouseHud());
		component.add(extend, JLayeredPane.DRAG_LAYER);	
	}

	public void addMessage() {
		String path = GameConfiguration.IMAGE_PATH+"messagerie.png";
		if(Messagerie.getInstance().getMessages().size()>0) {
			path = GameConfiguration.IMAGE_PATH+"notif.png";
		}
		message = new JLabel(new ImageIcon(path));
		message.setBounds(10, GameConfiguration.Y_HOME_LABEL, GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		message.addMouseListener(new MouseHudMessage());
		component.add(message , JLayeredPane.DRAG_LAYER);
	}

	public static void notification() {
		message.setIcon(new ImageIcon(GameConfiguration.IMAGE_PATH+"notif.png"));
		alertMessage();
	}
	
	public void removeAlert() {
		if(Arrays.asList(component.getComponents()).contains(alert)) {
			component.remove(alert);
		}
	}
	
	public static void alertMessage() {
		component.remove(statistique);
		removeChoix();
		if(Arrays.asList(component.getComponents()).contains(messagerie)) {
			component.remove(messagerie);
		}
		alert.setBounds(15, GameConfiguration.Y_HOME_LABEL-50,200,50);
		component.add(alert , JLayeredPane.DRAG_LAYER);
	}
	
	public  void addStat() {
		statistique = new JLabel(new ImageIcon(GameConfiguration.IMAGE_PATH+"statistiques.png"));
		statistique.setBounds(10, GameConfiguration.y_ADD_LABEL, GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		statistique.addMouseListener(new MouseHud());
		component.add(statistique , JLayeredPane.DRAG_LAYER);
	}

	public void addValidation() {

		component.remove(adding);
		validate = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"valider.png"));
		validate.setBounds( GameConfiguration.X_ADD_LABEL, GameConfiguration.y_ADD_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		validate.addMouseListener(new MouseHud());
		validate.setToolTipText("Valider la position");
		component.add(validate, JLayeredPane.DRAG_LAYER);
	
		
		component.remove(home);
		cancel = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"annuler.png"));
		cancel.setBounds( GameConfiguration.X_HOME_LABEL, GameConfiguration.Y_HOME_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		cancel.addMouseListener(new MouseHud());
		cancel.setToolTipText("cancel l'operation");
		component.add(cancel, JLayeredPane.DRAG_LAYER);

		component.remove(statistique);
		component.remove(message);

	}

	public void save() {
		save = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"sauvegarder.png"));
		save.setBounds(GameConfiguration.WINDOW_WIDTH-70 , 10, GameConfiguration.WIDHT_LABEL ,  GameConfiguration.HEIGHT_LABEL);
		save.setToolTipText("Sauvegarder l'état de la ferme");
		save.addMouseListener(new MouseHud());
		component.add(save , JLayeredPane.DRAG_LAYER);
	}

	public void profile() {
		// ajouter le mouse listener lors de la création des frames du jouer et fermier 
		farmer = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"farmer.png"));
		farmer.setBounds(10 , 10, GameConfiguration.WIDHT_LABEL ,  GameConfiguration.HEIGHT_LABEL);
		farmer.setToolTipText("Acceder au profil du fermier");
		farmer.addMouseListener(new MouseHud());
		component.add(farmer , JLayeredPane.DRAG_LAYER);

		soldeBg = new GeneralPaintStrategy().printImageLabel("", 20 + GameConfiguration.WIDHT_LABEL , 25, 3*GameConfiguration.WIDHT_LABEL,  GameConfiguration.HEIGHT_LABEL - 27, GameConfiguration.IMAGE_PATH+"soldeHud.png",null);
		component.add(soldeBg);

	}

	public void solde() {
		if(!Arrays.asList(component.getComponents()).contains(solde)) {
			solde.setBounds(10 + 2*GameConfiguration.WIDHT_LABEL , 25, 2*GameConfiguration.WIDHT_LABEL,  GameConfiguration.HEIGHT_LABEL - 30);
			solde.setFont(new Font(Font.SANS_SERIF,  Font.BOLD , 14));
			solde.setHorizontalTextPosition(SwingConstants.CENTER);
			component.add(solde, JLayeredPane.DRAG_LAYER);		
		}
		solde.setText(String.valueOf(Banque.getInstance().getCompte().getSolde()));
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
		if(component.getFarm().getJourMode()) {
			component.getChoix().init();
			choixScroll = new ChoixPanel(component);
			choixScroll.setBounds(150, GameConfiguration.WINDOW_HEIGHT-200, GameConfiguration.WINDOW_WIDTH-300, 160);
			component.add(choixScroll, JLayeredPane.DRAG_LAYER);
			removeAlert();
			if(!Arrays.asList(component.getComponents()).contains(statistique)) {
				component.add(statistique , JLayeredPane.DRAG_LAYER);
			}
			
		}
	}
	
	public static void removeChoix() {
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
			}else if(e.getSource().equals(home)) {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Hud.this.component);
				removeChoix();
				new Home(frame);
				frame.dispose();
			} else if(e.getSource().equals(validate)) {
				component.getChoix().removeElement(component.getSelected());
				component.getHud().build();
				component.getSelected().setStatique(true);
				component.setSelected(component.getFarm().getFermier());
			} else if(e.getSource().equals(cancel)) {
				component.getFarm().getManager().remove(component.getSelected());
				component.setSelected(component.getFarm().getFermier());
				component.getHud().build();
			} else if(e.getSource().equals(save)) {
				SaveFarm save = new SaveFarm();
				save.serializationSave(GameConfiguration.FILE_NAME_SAVE, component.getFarm());
				new PopupSave(component);
			} else if(e.getSource().equals(farmer)) {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Hud.this.component);
				frame.dispose();
				new FermierGui(frame , component.getFarm().getFermier());
			} else if(e.getSource().equals(statistique)) {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Hud.this.component);
				frame.setVisible(false);
				new TestStat(frame);
			} else {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Hud.this.component);

				new ExtendPopup(frame, component.getFarm());
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
			//removeAlert();
			addMessage();
		}
	}
	
	public void addMessagerie() {
		messagerie = new MessageriePanel(Hud.this);
		removeChoix();
		removeAlert();
		component.add(messagerie , JLayeredPane.DRAG_LAYER);
		component.remove(statistique);
	}
	
	private class MouseHudMessage implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
				if (Arrays.asList(component.getComponents()).contains(messagerie)) {
					addStat();
					component.remove(messagerie);
					removeAlert();
				} else {
					addMessagerie();
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
