package test;

import data.stucture_base.Farm;
import process.game.GameBuilder;
import process.game.Jeu;
import process.time.TimeManager;

public class Test {
	
	public static void main(String[] args) {
		Farm farm = GameBuilder.buildinFarm();
		Jeu jeu = new Jeu(farm, "Test");
		//TimeManager.getInstance().start();
		Thread gameThread = new Thread(jeu);
		gameThread.start();
	}

}