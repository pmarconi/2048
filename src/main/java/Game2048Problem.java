/**
 * 
 */
package main.java;

import java.util.List;

/**
 * @author matias
 *
 */
public class Game2048Problem implements AdversarySearchProblem {
	Game2048State state; 
	/**
	 * 
	 */
	public Game2048Problem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public AdversarySearchState initialState() {
		return null;
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
