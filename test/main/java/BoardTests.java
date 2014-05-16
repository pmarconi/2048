package main.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTests {

	@Test
	public void testMoveLeft() {
		Game2048State b = new Game2048State();
		int[][] b1 = {
				{4,4,4,4},
				{2,0,4,0},
				{0,2,0,4},
				{0,0,2,2},
		};
		b.setBoard(b1);
		int[][] expBoard = {
				{8,8,0,0},
				{2,4,0,0},
				{2,4,0,0},
				{4,0,0,0},
		};
		assertArrayEquals(b.boardMove(0),expBoard);
	}
	
	@Test
	public void testMoveRight() {
		Game2048State b = new Game2048State();
		int[][] b1 = {
				{0,4,0,4},
				{2,0,4,0},
				{0,2,0,4},
				{0,0,2,2},
		};
		b.setBoard(b1);
		int[][] expBoard = {
				{0,0,0,8},
				{0,0,2,4},
				{0,0,2,4},
				{0,0,0,4},
		};
		assertArrayEquals(b.boardMove(1),expBoard);
	}
	
	@Test
	public void testMoveUp() {
		Game2048State b = new Game2048State();
		int[][] b1 = {
				{0,4,0,4},
				{2,0,4,0},
				{0,4,0,4},
				{0,0,2,2},
		};
		b.setBoard(b1);
		int[][] expBoard = {
				{2,8,4,8},
				{0,0,2,2},
				{0,0,0,0},
				{0,0,0,0},
		};
		assertArrayEquals(b.boardMove(3),expBoard);
	}
	
	@Test
	public void testMoveDown() {
		Game2048State b = new Game2048State();
		int[][] b1 = {
				{0,4,0,4},
				{2,0,4,0},
				{0,4,0,4},
				{0,0,2,2},
		};
		b.setBoard(b1);
		int[][] expBoard = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,4,8},
				{2,8,2,2},
		};
		assertArrayEquals(b.boardMove(2),expBoard);
	}
	
	@Test
	public void testIsGameOver() {
		Game2048State b = new Game2048State();
		int[][] b1 = {
				{16,2,4,2},
				{2,4,16,4},
				{16,8,16,2},
				{8,4,2,4},
		};
		b.setBoard(b1);		
		assertFalse(b.isGameOver());
	}
	
	@Test
	public void testIsGameOver2() {
		Game2048State b = new Game2048State();
		int[][] b1 = {
				{16,2,4,2},
				{2,4,16,4},
				{16,8,4,2},
				{8,4,2,4},
		};
		b.setBoard(b1);		
		assertTrue(b.isGameOver());
	}
	
	@Test
	public void testIsGameOver3() {
		Game2048State b = new Game2048State();
		int[][] b1 = {
				{16,2,4,2},
				{2,4,16,4},
				{16,8,0,2},
				{8,4,2,4},
		};
		b.setBoard(b1);		
		assertFalse(b.isGameOver());
	}
}
