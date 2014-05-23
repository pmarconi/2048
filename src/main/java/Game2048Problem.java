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
		int [][] boardAux = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
							};
		for(int i=0;i<2;i++){
			int x = random.nextInt(4); 		// for test use 0
			int y = random.nextInt(4);   //for test use 0
			int value = (random.nextInt(10) < 9) ?  2 : 4; //for test use 1
			boardAux[x][y] = value;
			state.setBoard(boardAux);
		}
		return state; 
	}

	public List<Game2048State> getSuccessors(Game2048State state) {
		List <Game2048State> successors = new LinkedList <Game2048State>();
		if(state.isMax()){
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(state.get(i, j)==0){
						Game2048State stateAux = new Game2048State(state);
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
				successors.add(0,stateAux);
			}
			stateAux = new Game2048State(state);
			if(stateAux.isMovePossibleLeft()){
				stateAux.moveLeft();
				successors.add(0,stateAux);
			}
			stateAux = new Game2048State(state);
			if(stateAux.isMovePossibleUp()){
				stateAux.moveUp();
				successors.add(0,stateAux);
			}
			stateAux = new Game2048State(state);
			if(stateAux.isMovePossibleDown()){
				stateAux.moveDown();
				successors.add(0,stateAux);
			}
		}
		return successors;
	}

	@Override
	public boolean end(Game2048State state) {	
		return state.isGameOver();    
	}

	@Override
	public int value(Game2048State state) {
			int maxValue=0;
			for(int i=0;i<state.getBoard().length;i++){
				for(int j=0;j<state.getBoard().length;j++){
					if(state.get(i, j) > maxValue){
						maxValue = state.get(i, j);     //Search max value in the board
					}
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
			
			return (maxValue + countZero);
	}

	@Override
	public int minValue() {
		return 16;   // Count Zero: 14 + max value initial board: 2 
	}

	@Override
	public int maxValue() {
		return 2063;  // Count Zero: 15 + max value initial board 2048 
	}

}
