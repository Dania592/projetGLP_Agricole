package process.game;



import data.configuration.GameConfiguration;
import data.finance.Banque;
import data.gestion.RessourcesManager;
import data.map.Map;
import data.stucture_base.Farm;
import gui.Farm.MainGuiTest;
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
	private Farm farm;

	public Jeu(Farm farm,String title) {
		this.farm = farm;
		financeManager.setJeu(this);
		timeManager = TimeManager.getInstance();
		TimeManager.getInstance().setClock(farm.getClock());
		taskManager = TaskManager.getInstance();
		frame = new MainGuiTest(title, farm , taskManager);

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
			frame.getFarm().getEvolutionManager().UpdateEvolution();
			taskManager.managingTask();
		}
	}
}
