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
		 //Find a way to make this process more efficient
		 addPawns(6, 7, Team.WHITE, PieceType.PAWN);
		 addPawns(1, 2, Team.BLACK, PieceType.PAWN);
		 addRooks(0, 0, Team.BLACK, PieceType.ROOK);
		 addRooks(0, 7, Team.BLACK, PieceType.ROOK);
		 addRooks(7, 0, Team.WHITE, PieceType.ROOK);
		 addRooks(7, 7, Team.WHITE, PieceType.ROOK);
		 addKnights(0, 1, Team.BLACK, PieceType.KNIGHT);
		 addKnights(0, 6, Team.BLACK, PieceType.KNIGHT);
		 addKnights(7, 1, Team.WHITE, PieceType.KNIGHT);
		 addKnights(7, 6, Team.WHITE, PieceType.KNIGHT);
		 addBishops(0, 2, Team.BLACK, PieceType.BISHOP);
		 addBishops(0, 5, Team.BLACK, PieceType.BISHOP);
		 addBishops(7, 2, Team.WHITE, PieceType.BISHOP);
		 addBishops(7, 5, Team.WHITE, PieceType.BISHOP);
		 addKings(0, 3, Team.BLACK, PieceType.KING);
		 addKings(7, 4, Team.WHITE, PieceType.KING);
		 addQueens(0, 4, Team.BLACK, PieceType.QUEEN);
		 addQueens(7, 3, Team.WHITE, PieceType.QUEEN);
	 }
	
	 private void addPawns(int row, int col, Team t, PieceType p){
		 for(int x = row; x < col; x++){
			 for(int y = 0; y < 8; y++){
				 Piece pawn = new Pawn(x, y, t, p.toString());
				 Point pos = new Point(x, y);
				 pieces.put(pos, pawn);
				 addToPlayer(pawn, t);
			 }
		 }
	 }
	 
	 private void addRooks(int posX, int posY, Team t, PieceType p){
		 Piece rook = new Rook(posX, posY, t, p.toString());
		 Point pos = new Point(posX, posY);
		 pieces.put(pos, rook);
		 addToPlayer(rook, t);
	 }
	 
	 private void addKnights(int posX, int posY, Team t, PieceType p){
		 Piece knight = new Knight(posX, posY, t, p.toString());
		 Point pos = new Point(posX, posY);
		 pieces.put(pos, knight);
		 addToPlayer(knight, t);
	 }
	 
	 private void addBishops(int posX, int posY, Team t, PieceType p){
		 Piece bishop = new Bishop(posX, posY, t, p.toString());
		 Point pos = new Point(posX, posY);
		 pieces.put(pos, bishop);
		 addToPlayer(bishop, t);
	 }
	 
	 private void addKings(int posX, int posY, Team t, PieceType p){
		 Piece king = new King(posX, posY, t, p.toString());
		 Point pos = new Point(posX, posY);
		 pieces.put(pos, king);
		 addToPlayer(king, t);
	 }
	 
	 private void addQueens(int posX, int posY, Team t, PieceType p){
		 Piece queen = new Queen(posX, posY, t, p.toString());
		 Point pos = new Point(posX, posY);
		 pieces.put(pos, queen);
		 addToPlayer(queen, t);
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
