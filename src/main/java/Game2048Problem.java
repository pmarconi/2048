/**
 * 
 */
package main.java;

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
		int x = random.nextInt(4); 		// for test use 0
		int y = random.nextInt(4);   //for test use 0
		int value = (random.nextInt(10) < 9) ?  2 : 4; //for test use 1
		int [][] boardAux = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
							};
		boardAux[x][y] = value;
		state.setBoard(boardAux);
		return state; 
	}

	@Override
	public List getSuccessors(AdversarySearchState state) {
		// TODO Auto-generated method stub
		return null;
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
