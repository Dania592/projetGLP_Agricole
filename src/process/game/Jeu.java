package process.game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import data.configuration.GameConfiguration;
import data.evenement.Catastrophe;
import data.finance.Banque;
import data.gestion.RessourcesManager;
import data.map.Map;
import data.notification.Message;
import data.notification.Messagerie;
import data.notion.basic.Farm;
import data.time.Clock;
import gui.Farm.MainGuiTest;
import gui.gestionnaire.GameOver;
import process.action.task.coordinator.TaskManager;
import process.gestion.transaction.FinanceManager;
import process.time.TimeManager;

/**
 * thread principal du jeu reponsable de l'évolution et du moteur du jeu 
 * 
 *
 */
public class Jeu implements Runnable{


	private MainGuiTest frame ; 
	private TimeManager timeManager;
	private TaskManager taskManager; 
	private FinanceManager financeManager = FinanceManager.getInstance();
	private boolean gameOver = false;
	private static Clip clip ;
	public static boolean music ; 
	//private int i = 0;
	
	/**
	 * contructeur du jeu 
	 * @param farm
	 * @param title
	 */
	public Jeu(Farm farm,String title) {
		financeManager.setJeu(this);
		music= true ;
		timeManager = TimeManager.getInstance();	
		TimeManager.getInstance().setClock(farm.getClock());
		TimeManager.getInstance().setFarm(farm);
		TimeManager.getInstance().start();
		taskManager = TaskManager.getInstance();
		frame = new MainGuiTest(title, farm , taskManager);
		playMusique();
		Thread thread = new Thread(frame);
		thread.start();
	}

	/**
	 * verifie si le jeu tourne encore ou est arreté en gameOver
	 * @return
	 */
	public boolean getGameOver() {
		return gameOver;
	}
	
	/**
	 * reponsable du game over  
	 */
	public void GameOver() {
		gameOver = true;
		timeManager.gameOver(true);
		timeManager.reset();
		new GameOver(this);
	}
	
	/**
	 * relancement d'une nouvelle partie 
	 */
	public void restart() {
		RessourcesManager.getInstance().reset();
		FinanceManager.getInstance().reset();
		Banque.getInstance().reset();
		Map.getInstance().reset();
		Farm ferme = GameBuilder.buildinFarm();
		frame.dispose();
		Jeu jeu = new Jeu(ferme, "Nouvelle partie");
		Thread gameThread = new Thread(jeu);
		TimeManager.getInstance().reset();
		TimeManager.getInstance().gameOver(false);
		gameThread.start();
	}

	@Override
	public void run() {
		while(!gameOver) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(nightNotif()) {
				Message message = new Message("La nuit arrive !", Clock.getInstance().getHour().getValue() , Clock.getInstance().getMinute().getValue());
				Messagerie.getInstance().addMessage(message);
			}
			if(!isNight()) {
				frame.getFarm().setJourMode(true);
				TimeManager.getInstance().setTimeSpeed(2);
				frame.getFarm().getEvolutionManager().UpdateEvolution();
				taskManager.managingTask();				
			}
			else {
				nightMode();	
			}
		}
	}
	/**
	 * vérifie si c'est le moment d'envoyer la notification pour la nuit 
	 * @return
	 */
	public boolean nightNotif() {
		Clock clock =Clock.getInstance(); 
		return clock.getHour().getValue()==20 && clock.getMinute().getValue()==50 && clock.getSecond().getValue()==00;
	}
	/**
	 * vérifie si c'est la nuit 
	 * @return
	 */
	public boolean isNight() {
		return TimeManager.getInstance().getClock().getHour().getValue()>=21 
				|| TimeManager.getInstance().getClock().getHour().getValue()<6 ;
	}
	
	/**
	 * change le jour en nuit pour la ferme 
	 */
	public void nightMode() {
		if(isNight()) {
			frame.getFarm().setJourMode(false);
			TimeManager.getInstance().setTimeSpeed(50);
		}				
	}
	
	/**
	 * responsable du lancement de la musique dans le jeu 
	 */
	public static void playMusique() {
		  try {
	           File file = new File("src/ressources/musique/EZ02.wav");	           
	           AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
	           clip = AudioSystem.getClip();
	           clip.open(audioIn);
	           clip.start();
	           clip.loop(clip.LOOP_CONTINUOUSLY);
	       }
		catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			throw new RuntimeException(e);
		}
			    
	}
	
	/**
	 * arrête la musique dans le jeu 
	 */
	public static void stopMusique() {
		music = false ;
		clip.stop();
	}
	
	
	
}
