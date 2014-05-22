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
public class Game2048Problem implements AdversarySearchProblem {
	Game2048State state; 
	Random random;
	/**
	 * 
	 */
	public Game2048Problem() {
		random = null;
	}

	@Override
	public AdversarySearchState initialState() {
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

	public List getSuccessors(AdversarySearchState state) {
		List <Game2048State> successors = new LinkedList <Game2048State>();
		if(state.isMax()){
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(state.get(i, j)==0){
						Game2048State stateAux = new Game2048State();
						stateAux = (Game2048State)state;
						stateAux.put(i,j,2);
						successors.add(0,stateAux);
						stateAux.put(i,j,4);
						successors.add(0,stateAux);
					}
				}
			}
		}else{
			
			
		}
		return successors;
	}

	@Override
	public boolean end(AdversarySearchState state) {
		return getSuccessors(state).isEmpty();
	}

	@Override
	public int value(AdversarySearchState state) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int minValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int maxValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
