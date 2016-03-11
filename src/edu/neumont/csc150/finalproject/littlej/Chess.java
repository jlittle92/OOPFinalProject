package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.util.HashMap;

public class Chess {

	public static void main(String[] args) {
		new Chess();
	}
	
	private Board board;
	private HashMap<Point, Piece> pieces;
	private Checkable[] kings = new King[2];

	public Chess(){
		pieces = new HashMap<Point, Piece>(32);
		addPieces();
		board = new Board(pieces, kings);
	}
	
	 private void addPieces(){
		 //ROOKS
		 addPiece(new Rook(0, 0, Team.BLACK, PieceType.ROOK));
		 addPiece(new Rook(0, 7, Team.BLACK, PieceType.ROOK));
		 addPiece(new Rook(7, 0, Team.WHITE, PieceType.ROOK));
		 addPiece(new Rook(7, 7, Team.WHITE, PieceType.ROOK));
		 //KNIGHTS
		 addPiece(new Knight(0, 1, Team.BLACK, PieceType.KNIGHT));
		 addPiece(new Knight(0, 6, Team.BLACK, PieceType.KNIGHT));
		 addPiece(new Knight(7, 1, Team.WHITE, PieceType.KNIGHT));
		 addPiece(new Knight(7, 6, Team.WHITE, PieceType.KNIGHT));
		 //BISHOPS
		 addPiece(new Bishop(0, 2, Team.BLACK, PieceType.BISHOP));
		 addPiece(new Bishop(0, 5, Team.BLACK, PieceType.BISHOP));
		 addPiece(new Bishop(7, 2, Team.WHITE, PieceType.BISHOP));
		 addPiece(new Bishop(7, 5, Team.WHITE, PieceType.BISHOP));
		 //KINGS
		 addPiece(new King(0, 4, Team.BLACK, PieceType.KING));
		 addPiece(new King(7, 4, Team.WHITE, PieceType.KING));
		 //QUEENS
		 addPiece(new Queen(0, 3, Team.BLACK, PieceType.QUEEN));
		 addPiece(new Queen(7, 3, Team.WHITE, PieceType.QUEEN));
		 //PAWNS
		 for(int x = 1; x < 2; x++){
			 for(int y = 0; y < 8; y++){
				 addPiece(new Pawn(x, y, Team.BLACK, PieceType.PAWN));
			 }
		 }
		 for(int x = 6; x < 7; x++){
			 for(int y = 0; y < 8; y++){
				 addPiece(new Pawn(x, y, Team.WHITE, PieceType.PAWN));
			 }
		 }
		 kings[0] = (Checkable) pieces.get(new Point(7, 4));
		 kings[1] = (Checkable) pieces.get(new Point(0, 4));
	 }
	 
	 private void addPiece(Piece p){
		 addToHashMaps(p);
	 }
	 
	 private void addToHashMaps(Piece p){
		 Point pos = new Point(p.getPosition());
		 pieces.put(pos, p);
	 }
	 
}
