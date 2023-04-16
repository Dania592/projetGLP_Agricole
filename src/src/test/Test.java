package test;

import data.stucture_base.Farm;
import gui.Farm.MainGuiTest;
import process.game.GameBuilder;

public class Test {
	
	public static void main(String[] args) {
		Farm farm = GameBuilder.buildinFarm();
		MainGuiTest gameTest = new MainGuiTest("test" , farm);
		Thread gameThread = new Thread(gameTest);
		gameThread.start();
	}

}
