/**
 * 
 */
package main.java;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * @author matias
 *
 */
public class Game2048ProblemTest {

	@Test
	public void getSuccesorsTestWithMax() {
		Game2048Problem p = new Game2048Problem();
		Game2048State state = new Game2048State();
		int[][] b1 = {
				{8,4,0,4},
				{2,0,4,0},
				{0,2,0,4},
				{0,0,2,2},
		};
		state.setBoard(b1);
		state.setMax(false);
		List <Game2048State> list = p.getSuccessors(state);
		assertTrue(list.size()==4);		
	}
	
	@Test
	public void getSuccesorsTestWithMin() {	
		int[][] b2 = {
				{8,8,0,0},
				{2,4,0,0},   
				{2,4,0,0},
				{4,0,0,0},
		};
		Game2048State state = new Game2048State();
		Game2048Problem p = new Game2048Problem();
		state.setBoard(b2);
		state.setMax(true);
		List <Game2048State> list = p.getSuccessors(state);
		assertTrue(list.size()==18);		
	}
	
}
