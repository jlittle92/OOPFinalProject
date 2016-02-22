package edu.neumont.csc150.finalproject.littlej;

public class Game {

	Board board;
	
	public Game(){
		board = new Board();
	}
	
	public void play(){
		board.resetBoard();
		board.printBoard();
	}
}
