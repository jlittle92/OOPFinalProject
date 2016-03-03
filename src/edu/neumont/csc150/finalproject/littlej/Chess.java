package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.util.HashMap;

public class Chess {

	public static void main(String[] args) {
		new Chess();
	}
	
	private Player playerOne, playerTwo;
	private Board board;
	private HashMap<Point, Piece> pieces;
	private HashMap<Point, Piece> whitePieces;
	private HashMap<Point, Piece> blackPieces;

	public Chess(){
		pieces = new HashMap<Point, Piece>(32);
		blackPieces = new HashMap<Point, Piece>(16);
		whitePieces = new HashMap<Point, Piece>(16);
		addPlayers();
		addPieces();
		board = new Board(pieces);
		/*gameLoop();*/
	}
	
	/*
	 * private void gameLoop(){
	 * 		
	 * }
	 */
	
	 private void addPieces(){
		 //ROOKS
		 addPiece(new Rook(0, 0, Team.BLACK, PieceType.ROOK.toString()));
		 addPiece(new Rook(0, 7, Team.BLACK, PieceType.ROOK.toString()));
		 addPiece(new Rook(7, 0, Team.WHITE, PieceType.ROOK.toString()));
		 addPiece(new Rook(7, 7, Team.WHITE, PieceType.ROOK.toString()));
		 //KNIGHTS
		 addPiece(new Knight(0, 1, Team.BLACK, PieceType.KNIGHT.toString()));
		 addPiece(new Knight(0, 6, Team.BLACK, PieceType.KNIGHT.toString()));
		 addPiece(new Knight(7, 1, Team.WHITE, PieceType.KNIGHT.toString()));
		 addPiece(new Knight(7, 6, Team.WHITE, PieceType.KNIGHT.toString()));
		 //BISHOPS
		 addPiece(new Bishop(0, 2, Team.BLACK, PieceType.BISHOP.toString()));
		 addPiece(new Bishop(0, 5, Team.BLACK, PieceType.BISHOP.toString()));
		 addPiece(new Bishop(7, 2, Team.WHITE, PieceType.BISHOP.toString()));
		 addPiece(new Bishop(7, 5, Team.WHITE, PieceType.BISHOP.toString()));
		 //KINGS
		 addPiece(new King(0, 3, Team.BLACK, PieceType.KING.toString()));
		 addPiece(new King(7, 4, Team.WHITE, PieceType.KING.toString()));
		 //QUEENS
		 addPiece(new Queen(0, 4, Team.BLACK, PieceType.QUEEN.toString()));
		 addPiece(new Queen(7, 3, Team.WHITE, PieceType.QUEEN.toString()));
		 //PAWNS
		 for(int x = 1; x < 2; x++){
			 for(int y = 0; y < 8; y++){
				 addPiece(new Pawn(x, y, Team.BLACK, PieceType.PAWN.toString()));
			 }
		 }
		 for(int x = 6; x < 7; x++){
			 for(int y = 0; y < 8; y++){
				 addPiece(new Pawn(x, y, Team.WHITE, PieceType.PAWN.toString()));
			 }
		 }	 
	 }
	 
	 private void addPiece(Piece p){
		 addToHashMaps(p);
	 }
	 
	 private void addToHashMaps(Piece p){
		 Point pos = new Point(p.getPosition());
		 pieces.put(pos, p);
		 addToPlayer(p, p.getTeam());
	 }
	 
	 private void addToPlayer(Piece p, Team t){
		if(t.toString().equalsIgnoreCase(Team.WHITE.toString())){
			whitePieces.put(p.getLocation(), p); 
		}
		else{
			blackPieces.put(p.getLocation(), p);
		}
		
	 }
	
	 private void addPlayers(){
		 playerOne = new PlayerOne(whitePieces);
		 playerTwo = new PlayerTwo(blackPieces);
	 }
	 
}
