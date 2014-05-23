package main.java;

import javax.swing.UIManager;

public class Game2048 {
	
	private static Controller controller;

	public static void main(String[] args) {
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {}
		
		controller = new Controller();
		controller.startGame();
	}

}