package main.java;

import java.util.Random;

public class Game2048State implements AdversarySearchState{
	
	private static final int size = 4;// Size of the board.
	
	private static int[][] board;
	
	private static boolean max;

	private static Random random;
	
	
	public Game2048State(){
		board = new int[size][size];
	}
	
	/**
	 * Set a new board (array of integer).
	 * @param b, new board.
	 */
	public static void setBoard(int[][] b){
		board = b;
	}
	
	/**
	 * Get the array board.
	 * @return the array board.
	 */
	public static int[][] getBoard(){
		return board;
	}
	
	public int get(int i, int j){
		return board[i][j];
	}
	
	public void set(int i, int j, int value){
		board[i][j] = value;
	}
	
	public boolean isMax() {
		return max;
	}
	
	public String toString(){
		String s = "";
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				s += board[i][j] + " ";
			}
			s += "\n";
		}
		return s;
	}
	
	public boolean equals(AdversarySearchState s) {
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(board[i][j] != s.get(i,j))
					return false;
			}
		}
		return true;
	}
	
	public Object ruleApplied() {
		return null;// Implement
	}

	/**
	 * boardMove returns the board after a movement.
	 * @param direction, value 0 to left, 1 right, 2 down and 3 up.
	 * @return a board after a movement.
	 */
	public static int[][] boardMove(int direction){
		if(direction < 0 || direction > 3) throw new IllegalStateException("The direction value is not valid!");
		int[] vector = vectorOfMovements(direction);		
		int[][] resBoard = new int[size][size];
		if (direction == 0 || direction == 1){// Left and Right
			for(int i = 0; i != size; i++){
				Integer last = null;// The last element ordered in the row.
				for(int j = vector[1]; j != vector[3]; j+=vector[5]){
					if(board[i][j] != 0){
						if(last == null){//If not exist an element in the row.
							resBoard[i][vector[1]] = board[i][j];
							last = vector[1];
						}else{// Exist at least one element in resBoard[i].
							if(resBoard[i][last] == board[i][j]){
								resBoard[i][last] += board[i][j];
							}else{
								resBoard[i][last+vector[5]] = board[i][j];
								last += vector[5];
							}
						}
					}
				}
			}
		}else{// if (direction == 2 || direction == 3), Up and down.
			for(int j = 0; j != size; j++){
				Integer last = null;// The last element ordered in the row.
				for(int i = vector[1]; i != vector[3]; i+=vector[5]){
					if(board[i][j] != 0){
						if(last == null){// If not exist an element in the row.
							resBoard[vector[1]][j] = board[i][j];
							last = vector[1];
						}else{// Exist at least one element in the row.
							if(resBoard[last][j] == board[i][j]){
								resBoard[last][j] += board[i][j];
							}else{
								resBoard[last+vector[5]][j] = board[i][j];
								last += vector[5];
							}
						}
					}
				}
			}
		}
		return resBoard;
	}
	
	/**
	 * The method returns the vector that defines the begin, end and increments 
	 * of variables 'i' and 'j' to realize a movement.
	 * @param direction of the movement, 0 left, 1 right, 2 down and 3 up.
	 * @return the vector that defines the begin, end and increments of variables 'i' and 'j'.
	 */
	public static int[] vectorOfMovements(int direction){
		int[] vector;
		//Vector [0:begin of 'i', 1:begin of 'j',2:end of 'i', 3:end of 'j',4:increment of 'i', 5:increment of 'j']
		if(direction == 0 || direction == 3){ // Left and up.
			vector = new int[] {0,0,(size),size,1,1};
		}else{ // Right and down.
			vector = new int[] {0,(size-1),size,-1,1,-1};
		}
		return vector;
	}
	
	public static void moveLeft(){
		if(isMovePossibleLeft()){
			board = boardMove(0);
			max=true;
		}			
	}
	
	public static void moveRight(){
		if(isMovePossibleRight()){
			board = boardMove(1);
			max=true;
		}			
	}
	
	public static void moveDown(){
		if(isMovePossibleDown()){
			board = boardMove(2);
			max=true;
		}			
	}
	
	public static void moveUp(){
		if(isMovePossibleUp()){
			board = boardMove(3);
			max=true;
		}			
	}
	
	/**
	 * Method that returns true if game is end 
	 * @return true if the game over.
	 */
	public boolean isGameOver() {
		return isBoardFull() && !isMovePossible();
	}
	
	/**
	 * Method that returns true if the board is full.
	 * @return true if board is full.
	 */
	private static boolean isBoardFull(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(board[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	
	private static boolean isMovePossible() {
		return isMovePossibleLeft() || isMovePossibleRight() || isMovePossibleUp() || isMovePossibleDown();
	}
	
	public static boolean isMovePossibleLeft() {
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size-1; j++){
				int aux = j+1;
				if(board[i][j] == board[i][aux] || board[i][j] == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isMovePossibleRight() {
		for(int i = 0; i < size; i++){
			for(int j = size-1; j > 0; j--){
				int aux = j-1;
				if(board[i][j] == board[i][aux] || board[i][j] == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isMovePossibleUp() {
		for(int j = 0; j < size; j++){
			for(int i = 0; i < size-1; i++){
				int aux = i+1;
				if(board[i][j]==board[aux][j] || board[i][j] == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isMovePossibleDown() {
		for(int j = 0; j < size; j++){
			for(int i = size-1; i > 0; i--){
				int aux = i-1;
				if(board[i][j]==board[aux][j] || board[i][j] == 0){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * This method agree new value in the board, search a pair of values ​​are equal and 
	 * that among them there is a 0, then insert the new value
	 * 
	 * @pre board != null 	  
	 * @return board - agree new value in the board, 
	 */
	public static int[][] addNewValue(){
		int [][] boardAux = board;
		random = null;
		int value = (random.nextInt(10) < 9) ?  2 : 4; //set value Random  - for test use 2  
		boolean cellSet = false;  // cellSet (flag) identifies if any cell not set 
		while(!cellSet){
			if(!isBoardFull() && !cellSet){  // agree new value in cell if board is not full and any cell set yet.
				for(int i=0;i<boardAux.length;i++){  
					for(int j=2; j<boardAux.length;j++){     // Search in the column 1 and 2 in the board
						if(( (boardAux[i][0] == boardAux[i][j]) || (boardAux[i][1]==boardAux[i][j]) ) && boardAux[i][j-1] == 0){  
							boardAux[i][j-1] = value;
							cellSet = true;
							break;												
						}
					}
				}
				for(int i=2;i<boardAux.length;i++){
					for(int j=0; j<boardAux.length;j++){ // Search in the row 1 and 2 in the board
						if(( (boardAux[0][j] == boardAux[i][j]) || (boardAux[1][j] == boardAux[i][j]))  && boardAux[i-1][j]==0){
							boardAux[i-1][j] = value;
							cellSet = true;
							break;
						}
					}
				}
				// if not set cell form intelligent, do Random in the index where boardAux[random][random] == 0
				while(!cellSet){
					int x = random.nextInt(boardAux.length);
					int y = random.nextInt(boardAux.length);
					if (boardAux[x][y]==0) {
		                boardAux[x][y] = value;
		                cellSet = true;
					}
				}
			}
		} // end while
		return boardAux;	
	} //end Method addNewCell()

	public void put(int i, int j, int value) {
		board[i][j] = value;		
	}
	
}
	

