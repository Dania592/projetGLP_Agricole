package process.game;



import data.configuration.GameConfiguration;
import data.stucture_base.Farm;
import gui.Farm.MainGuiTest;
import process.action.TaskManager;
import process.time.TimeManager;
import process.transaction.FinanceManager;

public class Jeu implements Runnable{

	private MainGuiTest frame ; 
	private TimeManager timeManager;
	private TaskManager taskManager; 
	private FinanceManager financeManager;
	
	
	public Jeu(Farm farm) {
		
		timeManager = TimeManager.getInstance();
		TimeManager.getInstance().setClock(farm.getClock());
		timeManager.start();
				
		taskManager = TaskManager.getInstance();
		taskManager.start();
		
		frame = new MainGuiTest("test de la ferme", farm , taskManager);
		
		Thread thread = new Thread(frame);
		thread.start();
		
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			frame.getFarm().getEvolutionManager().UpdateEvolution();
			
		}
		
	}

}
