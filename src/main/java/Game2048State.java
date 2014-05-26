package main.java;

import java.util.Random;

public class Game2048State implements AdversarySearchState{
	
	private static final int size = 4;// Size of the board.
	
	private int[][] board;
	
	private boolean max;

	private Random random;
	
	private String ruleApplied;
	
	public Game2048State(){
		board = new int[size][size];
		init();
	}
	
	public Game2048State(Game2048State other) {
		if (other==null) throw new IllegalArgumentException();
		board = new int[size][size];
		
		for(int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				board[i][j] = other.getBoard()[i][j];
			}
		}
		max = other.isMax();
	}
	
	public void init(){
		if(board == null) throw new IllegalStateException("board null");
		this.addNewValue();
		this.addNewValue();
	}
	
	/**
	 * Set a new board (array of integer).
	 * @param b, new board.
	 */
	public void setBoard(int[][] b){
		if(b == null) throw new IllegalArgumentException("Board null");
		board = b;
	}
	
	/**
	 * Get the array board.
	 * @return the array board.
	 */
	public int[][] getBoard(){
		if(board == null) throw new IllegalStateException("board null");
		return board;
	}
	
	public int get(int i, int j){
		if(board == null) throw new IllegalStateException("board null");
		return board[i][j];
	}
	
	public void put(int i, int j, int value) {
		if(board == null) throw new IllegalStateException("board null");
		board[i][j] = value;		
	}
	
	public void setMax(boolean b){
		max = b;
	}
	
	public boolean isMax() {
		return max;
	}
	
	public String toString(){
		if(board == null) throw new IllegalStateException("board null");
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
		if(s == null) throw new IllegalArgumentException("State null");
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(board[i][j] != s.get(i,j))
					return false;
			}
		}
		return true;
	}
	
	public Object ruleApplied() {
		if(ruleApplied == null) throw new IllegalStateException("rule Applied null");
		return ruleApplied;
	}

	/**
	 * boardMove returns the board after a movement.
	 * @param direction, value 0 to left, 1 right, 2 down and 3 up.
	 * @return a board after a movement.
	 */
	public int[][] boardMove(int direction){
		if(direction < 0 || direction > 3) throw new IllegalStateException("The direction value is not valid!");
		int[] vector = vectorOfMovements(direction);		
		int[][] resBoard = new int[size][size];
		boolean plus = false;
		if (direction == 0 || direction == 1){// Left and Right
			for(int i = 0; i != size; i++){
				Integer last = null;// The last element ordered in the row.
				plus = false;
				for(int j = vector[1]; j != vector[3]; j+=vector[5]){
					if(board[i][j] != 0){
						if(last == null){//If not exist an element in the row.
							resBoard[i][vector[1]] = board[i][j];
							last = vector[1];
						}else{// Exist at least one element in resBoard[i].
							if(resBoard[i][last] == board[i][j] && !plus){
									resBoard[i][last] += board[i][j];
									plus = true;
							}else{
								resBoard[i][last+vector[5]] = board[i][j];
								last += vector[5];
								plus = false;
							}
						}
					}
				}
			}
		}else{// if (direction == 2 || direction == 3), Up and down.
			for(int j = 0; j != size; j++){
				Integer last = null;// The last element ordered in the row.
				plus = false;
				for(int i = vector[1]; i != vector[3]; i+=vector[5]){
					if(board[i][j] != 0){
						if(last == null){// If not exist an element in the row.
							resBoard[vector[1]][j] = board[i][j];
							last = vector[1];
						}else{// Exist at least one element in the row.
							if(resBoard[last][j] == board[i][j] && !plus){
								resBoard[last][j] += board[i][j];
								plus = true;
							}else{
								resBoard[last+vector[5]][j] = board[i][j];
								last += vector[5];
								plus = false;
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
	public int[] vectorOfMovements(int direction){
		if(direction < 0 || direction > 3) throw new IllegalStateException("The direction value is not valid!");
		int[] vector;
		//Vector [0:begin of 'i', 1:begin of 'j',2:end of 'i', 3:end of 'j',4:increment of 'i', 5:increment of 'j']
		if(direction == 0 || direction == 3){ // Left and up.
			vector = new int[] {0,0,(size),size,1,1};
		}else{ // Right and down.
			vector = new int[] {0,(size-1),size,-1,1,-1};
		}
		return vector;
	}
	
	public void moveLeft(){
		if(board == null) throw new IllegalStateException("board null");
		if(isMovePossibleLeft()){
			board = boardMove(0);
			ruleApplied = "Left";
			max=true;
		}			
	}
	
	public void moveRight(){
		if(board == null) throw new IllegalStateException("board null");
		if(isMovePossibleRight()){
			board = boardMove(1);
			ruleApplied = "Right";
			max=true;
		}			
	}
	
	public void moveDown(){
		if(board == null) throw new IllegalStateException("board null");
		if(isMovePossibleDown()){
			board = boardMove(2);
			ruleApplied = "Down";
			max=true;
		}			
	}
	
	public void moveUp(){
		if(board == null) throw new IllegalStateException("board null");
		if(isMovePossibleUp()){
			board = boardMove(3);
			ruleApplied = "Up";
			max=true;
		}			
	}
	
	/**
	 * Method that returns true if game is end 
	 * @return true if the game over.
	 */
	public boolean isGameOver() {
		if(board == null) throw new IllegalStateException("board null");
		return isBoardFull() && !isMovePossible() && !found2048();
	}
	
	private boolean found2048() {
		if(board == null) throw new IllegalStateException("board null");
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(board[i][j]==2048) return true;
			}
		}
		return false;
	}

	/**
	 * Method that returns true if the board is full.
	 * @return true if board is full.
	 */
	private boolean isBoardFull(){
		if(board == null) throw new IllegalStateException("board null");
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(board[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	
	private boolean isMovePossible() {
		if(board == null) throw new IllegalStateException("board null");
		return isMovePossibleLeft() || isMovePossibleRight() || isMovePossibleUp() || isMovePossibleDown();
	}
	
	public boolean isMovePossibleRight() {
		if(board == null) throw new IllegalStateException("board null");
		for(int i = 0; i < size; i++){
			Integer first = null;
			for(int j = 0; j < size; j++){
				if( j < size-1 && board[i][j] != 0){
					if (first == null) first = j;
					int aux = j+1;
					if(board[i][j] == board[i][aux]) return true;
				}else if(board[i][j] == 0){
					if (first != null) return true;
				}
			}
		}
		return false;
	}
	
	public boolean isMovePossibleLeft() {
		if(board == null) throw new IllegalStateException("board null");
		for(int i = 0; i < size; i++){
			Integer first = null;
			for(int j = size-1; j > -1; j--){
				if(j > 0 && board[i][j] != 0){
					if (first == null) first = j;
					int aux = j-1;
					if(board[i][j] == board[i][aux]) return true;
				}else if(board[i][j] == 0){
					if (first != null) return true;
				}
			}
		}
		return false;
	}
	
	public boolean isMovePossibleDown() {
		if(board == null) throw new IllegalStateException("board null");
		for(int j = 0; j < size; j++){
			Integer first = null;
			for(int i = 0; i < size; i++){
				if( i < size-1 && board[i][j] != 0){
					if (first == null) first = i;
					int aux = i+1;
					if(board[i][j] == board[aux][j]) return true;
				}else if(board[i][j] == 0){
					if (first != null) return true;
				}
			}
		}
		return false;
	}
	
	public boolean isMovePossibleUp() {
		if(board == null) throw new IllegalStateException("board null");
		for(int j = 0; j < size; j++){
			Integer first = null;
			for(int i = size-1; i > -1; i--){
				if( i > 0 && board[i][j] != 0){
					if (first == null) first = i;
					int aux = i-1;
					if(board[i][j] == board[aux][j]) return true;
				}else if(board[i][j] == 0){
					if (first != null) return true;
				}
			}
		}
		return false;
	}
	
	public void addNewValue(){
		if(board == null) throw new IllegalStateException("board null");
		random = new Random();
		boolean cellSet = false;
		int value = (random.nextInt(10) < 9) ?  2 : 4;
		while(!cellSet){
			int x = random.nextInt(size);
			int y = random.nextInt(size);
			if (board[x][y]==0) {
				put(x,y,value);
		        cellSet = true;
			}
		}
		ruleApplied = "Add new value";
	} 
	
}
	

