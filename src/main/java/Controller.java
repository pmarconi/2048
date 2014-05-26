package main.java;

public class Controller {
	
	private Game2048State model;
	
	private MainWindow window;
	
	private MinMaxABEngine<Game2048State,Game2048Problem> engine;
	
	private Game2048Problem problem;
	
	private boolean AIInPlay;

	public Controller() {
		model = new Game2048State();
		window = new MainWindow();
	}
	
	public void startGame(){
		window.setModel(model);
		window.setController(this);
		problem = new Game2048Problem(model);
		engine = new MinMaxABEngine<Game2048State,Game2048Problem>(problem,7);
	}
	
	public void newGame(){
		model = new Game2048State();
		startGame();
	}
	
	public void cpuMove(){
		model.addNewValue();
		model.setMax(false);
	}

	public void playAI() {
		AIInPlay = true;
		while (!model.isGameOver() && AIInPlay){
			model = engine.computeSuccessor(model);
			System.out.println("computer:"+model.ruleApplied()+" \n"+model.toString());
			window.setModel(model);
			window.paintContentPanel();
			
			cpuMove();
			System.out.println("computer: "+model.ruleApplied()+" \n"+model.toString());
			window.setModel(model);
			window.paintContentPanel();
		}
		AIInPlay = false;
		engine.report();
	}
	
}
