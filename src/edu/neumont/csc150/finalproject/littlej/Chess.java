package edu.neumont.csc150.finalproject.littlej;

import java.util.HashMap;

public class Chess {

	public static void main(String[] args) {
		new Chess();
	}
	
	private Player playerOne, playerTwo;
	private Board board;
	private HashMap<String, Piece> pieces;
	private HashMap<String, Piece> whitePieces;
	private HashMap<String, Piece> blackPieces;

	public Chess(){
		pieces = new HashMap<String, Piece>(32);
		blackPieces = new HashMap<String, Piece>(16);
		whitePieces = new HashMap<String, Piece>(16);
		addPieces();
		addPlayers();
		board = new Board();
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
		 addKings(0, 4, Team.BLACK, PieceType.KING);
		 addKings(7, 3, Team.WHITE, PieceType.KING);
		 addQueens(0, 3, Team.BLACK, PieceType.QUEEN);
		 addQueens(7, 4, Team.WHITE, PieceType.QUEEN);
	 }
	
	 private void addPawns(int row, int col, Team t, PieceType p){
		 for(int x = row; x < col; x++){
			 for(int y = 0; y < 8; y++){
				 Piece pawn = new Pawn(x, y, t, p.toString());
				 pieces.put(p.toString(), pawn);
				 addToPlayer(pawn, t);
			 }
		 }
	 }
	 
	 private void addRooks(int posX, int posY, Team t, PieceType p){
		 Piece rook = new Rook(posX, posY, t, p.toString());
		 pieces.put(p.toString(), rook);
	 }
	 
	 private void addKnights(int posX, int posY, Team t, PieceType p){
		 Piece knight = new Knight(posX, posY, t, p.toString());
		 pieces.put(p.toString(), knight);
	 }
	 
	 private void addBishops(int posX, int posY, Team t, PieceType p){
		 Piece bishop = new Bishop(posX, posY, t, p.toString());
		 pieces.put(p.toString(), bishop);
	 }
	 
	 private void addKings(int posX, int posY, Team t, PieceType p){
		 Piece king = new King(posX, posY, t, p.toString());
		 pieces.put(p.toString(), king);
	 }
	 
	 private void addQueens(int posX, int posY, Team t, PieceType p){
		 Piece queen = new Queen(posX, posY, t, p.toString());
		 pieces.put(p.toString(), queen);
	 }
	 
	 private void addToPlayer(Piece p, Team t){
		if(t.toString().equalsIgnoreCase(Team.WHITE.toString())){
			whitePieces.put(p.toString(), p); 
		}
		else{
			blackPieces.put(p.toString(), p);
		}
		
	 }
	
	 private void addPlayers(){
		 playerOne = new PlayerOne(whitePieces);
		 playerTwo = new PlayerTwo(blackPieces);
	 }
	 
}
