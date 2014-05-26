/**
 * 
 */
package main.java;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author matias
 *
 */
public class Game2048Problem implements AdversarySearchProblem<Game2048State> {
	Game2048State state; 
	Random random;
	
	private Game2048State initial;
	
	public Game2048Problem() {
		random = null;
	}
	
	public Game2048Problem(Game2048State initialState) {
		initial = initialState;
	}

	@Override
	public Game2048State initialState() {
		return initial; 
	}
	
	/**
	 * Method getSuccesors get all succesors for level (tree play)
	 * and calculate best son on the tree
	 * @param state
	 * @return  List<Game2048State> , list of succesors  
	 * 
	 */
	public List<Game2048State> getSuccessors(Game2048State state) {
		if(state == null) throw new IllegalArgumentException("State null");
		List <Game2048State> successors = new LinkedList <Game2048State>();
		if(state.isMax()){
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(state.get(i, j)==0){
						Game2048State stateAux = new Game2048State(state);
						stateAux.setMax(false);
						stateAux.put(i,j,2);
						successors.add(0,stateAux);
						stateAux.put(i,j,4);
						successors.add(0,stateAux);
					}
				}
			}
		}else{
			Game2048State stateAux = new Game2048State(state);
			if(stateAux.isMovePossibleRight()){
				stateAux.moveRight();
				stateAux.setMax(true);
				successors.add(0,stateAux);
			}
			stateAux = new Game2048State(state);
			if(stateAux.isMovePossibleLeft()){
				stateAux.moveLeft();
				stateAux.setMax(true);
				successors.add(0,stateAux);
			}
			stateAux = new Game2048State(state);
			if(stateAux.isMovePossibleUp()){
				stateAux.moveUp();
				stateAux.setMax(true);
				successors.add(0,stateAux);
			}
			stateAux = new Game2048State(state);
			if(stateAux.isMovePossibleDown()){
				stateAux.moveDown();
				stateAux.setMax(true);
				successors.add(0,stateAux);
			}
		}
		return successors;
	}

	public boolean end(Game2048State state) {
		if(state == null) throw new IllegalArgumentException("State null");
		return state.isGameOver();    
	}

	public int value(Game2048State state) {
		if(state == null) throw new IllegalArgumentException("State null");
		int maxValue=0;
		for(int i=0;i<state.getBoard().length;i++){
			for(int j=0;j<state.getBoard().length;j++){
				if(state.get(i, j) > maxValue){
					maxValue = state.get(i, j);     //Search max value in the board
				}
			}
		}
		for(int i=0;i<state.getBoard().length;i++){
			for(int j=0;j<state.getBoard().length;j++){
				if((i==0 && state.get(i, j)== maxValue ) || (j==0 && state.get(i, j)== maxValue)|| 
					(i==state.getBoard().length && state.get(i, j)== maxValue ) || 
					(j==state.getBoard().length && state.get(i, j)== maxValue) ){
						maxValue = maxValue * 2; // the max value in the board is allocated in extreme board  	
				}								//	and mult for 2, else return max value (and not mult 2)
			}
		}
		int countZero = 0;
		for(int i=0;i<state.getBoard().length;i++){
			for(int j=0;j<state.getBoard().length;j++){
				if(state.get(i, j) == 0){
					countZero++;
				}
			}
		}			
		return (maxValue * countZero);
	}

	public int minValue() {
		return Integer.MIN_VALUE; 
	}

	public int maxValue() {
		return Integer.MAX_VALUE;  
	}

}
