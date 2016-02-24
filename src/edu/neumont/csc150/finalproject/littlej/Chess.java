package edu.neumont.csc150.finalproject.littlej;

import java.util.HashMap;

public class Chess {

	public static void main(String[] args) {
		new Chess();
	}
	
	private final int NUM_OF_PIECES = 32, HALF = 2;
	private HashMap<String, Piece> blackPieces;
	private HashMap<String, Piece> whitePieces;
	private HashMap<String, Piece> pieces;
	private Player playerOne, playerTwo;
	private Board board;
	private MainFrame f;

	public Chess(){
		blackPieces = new HashMap<String, Piece>(NUM_OF_PIECES / HALF);
		whitePieces = new HashMap<String, Piece>(NUM_OF_PIECES / HALF);
		pieces = new HashMap<String, Piece>(NUM_OF_PIECES);
		/*addPieces();*/
		/*addPlayers();*/
		board = new Board(pieces);
		f = new MainFrame(board);
		/*gameLoop();*/
	}
	
	/*
	 * private void gameLoop(){
	 * 		
	 * }
	 */
	/*
	 * private void addPieces(){
	 * 
	 * }
	 * */
	/*
	 * private void addPlayers(){
	 * 		playerOne = new PlayerOne(whitePieces);
	 * 		playerTwo = new PlayerTwo(blackPieces);
	 * }
	 * */
}
