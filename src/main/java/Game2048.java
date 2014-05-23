package main.java;

public class Game2048 {
	
	private static Controller controller;

	public static void main(String[] args) {
		controller = new Controller();
		controller.startGame();
	}

}