package process.game;



import java.io.File;
import java.io.IOException;
import java.net.URL;

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
import data.stucture_base.Farm;
import data.time.Clock;
import gui.Farm.MainGuiTest;
import gui.Farm.Musique;
import gui.gestionnaire.GameOver;
import process.action.TaskManager;
import process.time.TimeManager;
import process.transaction.FinanceManager;

public class Jeu implements Runnable{

	int i = 0;

	private MainGuiTest frame ; 
	private TimeManager timeManager;
	private TaskManager taskManager; 
	private FinanceManager financeManager = FinanceManager.getInstance();
	private boolean gameOver = false;
	private  static Clip clip ;
	private Farm farm;

	public Jeu(Farm farm,String title) {
		this.farm = farm;
		financeManager.setJeu(this);
		timeManager = TimeManager.getInstance();	
		TimeManager.getInstance().setClock(farm.getClock());
		TimeManager.getInstance().start();
		taskManager = TaskManager.getInstance();
		frame = new MainGuiTest(title, farm , taskManager);
		playMusique();
		Thread thread = new Thread(frame);
		thread.start();
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public void GameOver() {
		gameOver = true;
		timeManager.gameOver(true);
		timeManager.reset();
		new GameOver(this);
	}
	
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
		//TimeManager.getInstance().start();
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
				TimeManager.getInstance().setTimeSpeed(1);
				frame.getFarm().getEvolutionManager().UpdateEvolution();
				taskManager.managingTask();				
			}
			else {
				nightMode();	
			}
		}
	}
	
	public boolean nightNotif() {
		Clock clock =Clock.getInstance(); 
		return clock.getHour().getValue()==10 && clock.getMinute().getValue()==50 && clock.getSecond().getValue()==00;
	}
	
	public boolean isNight() {
		return TimeManager.getInstance().getClock().getHour().getValue()>=20 
				|| TimeManager.getInstance().getClock().getHour().getValue()<6 ;
	}
	
	public void nightMode() {
		if(isNight()) {
			frame.getFarm().setJourMode(false);
			TimeManager.getInstance().setTimeSpeed(50);
		}				
	}
	
	// partie des catastrophes 
	public boolean catastrophTime() {
		return (frame.getFarm().getLastCatastroph()-Clock.getInstance().getMinute().getValue()) == GameConfiguration.FREQUENCE_CATASTROPHE;
	}
	
	public void catastropheIntervention() {
		if(catastrophTime()) {
			Catastrophe catastrophe = new Catastrophe(i, i, gameOver, null);
		}
	}
	
	public void playMusique() {
		  try {
	           URL url= Musique.class.getClassLoader().getResource("src/ressources/musique/m.wav");
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
	
	
	
}
