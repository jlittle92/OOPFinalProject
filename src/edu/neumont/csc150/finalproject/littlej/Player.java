package edu.neumont.csc150.finalproject.littlej;

import java.util.ArrayList;

public class Player {

	private enum Color {RED, BLACK};
	private final int MAX_PIECES = 12;
	private int pieceCount;
	private ArrayList<Piece> pieces;
	
	public Player(){
		setPieceCount(MAX_PIECES);
		pieces = new ArrayList<Piece>(MAX_PIECES);
	}
	
	public void move(){
		
	}
	
	private void checkValid(){
		
	}
	
	private void checkKing(){
		
	}
	
	private void checkWin(){
		
	}
	
	private void checkDraw(){
		
	}

	public int getPieceCount() {
		return pieceCount;
	}

	public void setPieceCount(int pieceCount) {
		this.pieceCount = pieceCount;
	}
	
}
