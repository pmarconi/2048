package main.java;

public class Controller {
	
	private Game2048State model;
	
	private MainWindow window;
	
	private MinMaxABEngine<Game2048State,Game2048Problem> engine;
	
	private Game2048Problem problem;

	public Controller() {
		model = new Game2048State();
		window = new MainWindow();
	}
	
	public void startGame(){
		window.setModel(model);
		window.setController(this);
		problem = new Game2048Problem(model);
		engine = new MinMaxABEngine<Game2048State,Game2048Problem>(problem);
	}
	
	public void newGame(){
		model = new Game2048State();
		startGame();
	}
	
	public void cpuMove(){
		model.addNewValue();
		model.setMax(false);
	}
	
}
