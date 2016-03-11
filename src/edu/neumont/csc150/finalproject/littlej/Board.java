package edu.neumont.csc150.finalproject.littlej;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Board extends JFrame{

	private static final int ROWS = 8, COLUMNS = 8, CHESS_SIZE = 800, WHITE = 0, BLACK = 1;
	private static final Color OFF_WHITE = Color.decode("#F2F2F2"), BLUE_ISH = Color.decode("#74C3F6");
	private JButton [][] board;
	private HashMap<Point, Piece> pieces;
	private HashMap<Point, Square> squares;
	private Checkable[] kings;
	private int activeKing, inactiveKing;
	private boolean activeSquare, whitePrivilege;
	private Piece toMove;
	private Rook toCastle;
	private Point destinationLocation, rookDestination;
	private Square sourceSquare, passantVictim;
	
	public Board(HashMap<Point, Piece> pieces, Checkable[] kings){
		setWhitePrivilege(true);
		setPieces(pieces);
		setSquares(new HashMap<Point, Square>(64));
		setKings(kings);
		setActiveKing(WHITE);
		setInactiveKing(BLACK);
		
		GridLayout grid = new GridLayout(ROWS, COLUMNS);
		
		JFrame frame = new JFrame();
		frame.setSize(CHESS_SIZE, CHESS_SIZE);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setTitle("Chess");
		frame.setLayout(grid);
		
		board = new Square[ROWS][COLUMNS];
		
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				final int finalI = i;
				final int finalJ = j;
				board[i][j] = new Square(i, j);
				board[i][j].setName(i + "" + j);
				String[] loc = board[i][j].getName().split("");
				Point p = new Point(Integer.parseInt(loc[0]), Integer.parseInt(loc[1]));
				squares.put(p, (Square) board[i][j]);
				board[i][j].addActionListener(new ActionListener() {          
				    public void actionPerformed(ActionEvent e) {
				    	Square square = (Square) board[finalI][finalJ];
				    	Piece piece = square.getPiece();
				    	if(square.getIcon() != null && !isActiveSquare()){
					    	if((piece.getTeam() == Team.WHITE && isWhitePrivilege()) || (piece.getTeam() == Team.BLACK && !isWhitePrivilege())){
					    		setActiveSquare(true);
						    	setToMove(piece);
							    sourceClick(square);
							    square.setIcon(null);
							    System.out.println(getActiveKing());
					    	}
					    	else{
					    		System.out.println("It's not your turn!");
					    	}
					    }
					    else if(square.getIcon() != null && isActiveSquare()){
					    	setDestinationLocation(square.getLocation());
					    	if(!getKings()[getActiveKing()].isChecked(((King) getKings()[getActiveKing()]).getPosition(), squares) || !getKings()[getInactiveKing()].isChecked(getDestinationLocation(), squares) || !getKings()[getActiveKing()].isChecked(getDestinationLocation(), squares) || block(getToMove(), square)){
					    		if(!isTeammate(e)){
						    		capture(getToMove(), e, square);
						    	}
						    	setActiveSquare(false);
						    	switchKing(getActiveKing());
						        if(isWhitePrivilege()){
						        	setWhitePrivilege(false);
							    }
							    else{
							    	setWhitePrivilege(true);
							    }
						        for(Checkable c: getKings()){
						        	if(c.isChecked(((King) c).getPosition(), getSquares())){
						        		c.isMate(((King) c).getPosition(), getSquares());
						        	}
						        }
					    	}
					    	else{
					    		System.out.println("That puts you in check!");
					    		replaceImage();
					    	}
					    }
					    else if(square.getIcon() == null && isActiveSquare()){
					    	setDestinationLocation(square.getLocation());
					    	if(!getKings()[getActiveKing()].isChecked(((King) getKings()[getActiveKing()]).getPosition(), squares) || !getKings()[getInactiveKing()].isChecked(getDestinationLocation(), squares) || !getKings()[getActiveKing()].isChecked(getDestinationLocation(), squares) || block(getToMove(), square)){
					    		if(getToMove().getType() == PieceType.KING && !getToMove().move(getToMove().getPosition(), square.getLocation())){
						    		King k = (King) getToMove();
						    		if(k.castle(k.getPosition(), square.getLocation())){
						    			if(rookEligible(square.getLocation(), k)){
						    				castle(k, getToCastle(), square.getLocation());
						    			}
						    		}
						    		for(Checkable c: getKings()){
							        	if(c.isChecked(((King) c).getPosition(), getSquares())){
							        		c.isMate(((King) c).getPosition(), getSquares());
							        	}
							        }
						    	}
						    	else if(getToMove().getType() == PieceType.PAWN && ((Pawn) getToMove()).enPassant(square.getLocation()) && validPassant(getToMove().getTeam(), square.getLocation())){
						    		enPassant(getToMove(), square);
						    		for(Checkable c: getKings()){
							        	if(c.isChecked(((King) c).getPosition(), getSquares())){
							        		c.isMate(((King) c).getPosition(), getSquares());
							        	}
							        }
						    	}
						    	else{
						    		destinationClick(getToMove(), e, square);
							        setActiveSquare(false);
							        switchKing(getActiveKing());
							        if(isWhitePrivilege()){
							        	setWhitePrivilege(false);
								    }
								    else{
								    	setWhitePrivilege(true);
								    }
							        for(Checkable c: getKings()){
							        	if(c.isChecked(((King) c).getPosition(), getSquares())){
							        		c.isMate(((King) c).getPosition(), getSquares());
							        	}
							        }
						    	}
					    	}
					    	else{
					    		System.out.println("That puts you in check!");
					    		replaceImage();
					    	}
					    }	
				    }
				});
				placePiece(i, j); 
				if((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)){
					board[i][j].setBackground(OFF_WHITE);
				}
				else{
					board[i][j].setBackground(BLUE_ISH);
				}
				frame.add(board[i][j]);
			}
			
		}
		
		frame.setVisible(true);
	}
	
	private void placePiece(int i, int j){
		Point pos = new Point(i, j);
		if(pieces.containsKey(pos)){
			((Square) board[i][j]).setPiece(pieces.get(pos));
			Image img;
			try {
				img = ImageIO.read(getClass().getResource(pieces.get(pos).getSource().toString()));
				board[i][j].setIcon((new ImageIcon(img)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public boolean isActiveSquare() {
		return activeSquare;
	}

	public void setActiveSquare(boolean activeSquare) {
		this.activeSquare = activeSquare;
	}
	
	public void sourceClick(Square square) {
		setSourceLocation(square);
	}
	
	public void destinationClick(Piece piece, ActionEvent event, Square square) {
		if(piece.move(getSourceLocation().getLocation(), square.getLocation())){
			if(piece.getType() == PieceType.PAWN && square.promote(piece, square.getLocation())){
				Piece p = new Queen(square.getLocation().x, square.getLocation().y, piece.getTeam(), PieceType.QUEEN);
				square.setPiece(p);
				pieces.remove(getSourceLocation(), piece);
				pieces.put(square.getLocation(), p);
				getSourceLocation().setIcon(null);
				Image img;
				try {
				img = ImageIO.read(getClass().getResource(p.getSource().toString()));
				square.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				square.setPiece(piece);
				updateReference(getSourceLocation().getLocation(), getDestinationLocation(), piece);
				getSourceLocation().setIcon(null);
				Image img;
				try {
				img = ImageIO.read(getClass().getResource(piece.getSource().toString()));
				square.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean isTeammate(ActionEvent event){
		String[] loc = ((Component) event.getSource()).getName().split("");
		Point p = new Point(Integer.parseInt(loc[0]), Integer.parseInt(loc[1]));
		Square destination = squares.get(p);
		Piece occupyingPiece = destination.getPiece();
		if(occupyingPiece.getTeam() == getToMove().getTeam()){
			return true;
		}
		return false;
	}
	
	public void capture(Piece piece, ActionEvent event, Square square){
		if(piece.getType() == PieceType.PAWN){
			if(piece.legalPawnCap(getSourceLocation().getPiece(), square.getPiece())){
				if(!square.promote(piece, square.getLocation())){
					square.setPiece(piece);
					updateReference(getSourceLocation().getLocation(), getDestinationLocation(), piece);
					getSourceLocation().setIcon(null);
					Image img;
					try {
					img = ImageIO.read(getClass().getResource(piece.getSource().toString()));
					square.setIcon(new ImageIcon(img));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else{
					Piece p = new Queen(square.getLocation().x, square.getLocation().y, piece.getTeam(), PieceType.QUEEN);
					square.setPiece(p);
					pieces.remove(getSourceLocation(), piece);
					pieces.put(square.getLocation(), p);
					getSourceLocation().setIcon(null);
					Image img;
					try {
					img = ImageIO.read(getClass().getResource(p.getSource().toString()));
					square.setIcon(new ImageIcon(img));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		else if(piece.move(getSourceLocation().getLocation(), square.getLocation())){
			square.setPiece(piece);
			updateReference(getSourceLocation().getLocation(), getDestinationLocation(), piece);
			getSourceLocation().setIcon(null);
			Image img;
			try {
			img = ImageIO.read(getClass().getResource(piece.getSource().toString()));
			square.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void castle(King k, Rook r, Point kingDestination){
		Square rDest = squares.get(getRookDestination());
		Square rSrc = squares.get(getToCastle().getPosition());
		Square kDest = squares.get(kingDestination);
		Square kSrc = squares.get(k.getPosition());
		rDest.setPiece(getToCastle());
		rSrc.setIcon(null);
		kSrc.setIcon(null);
		kDest.setPiece(k);
		updateReference(getToMove().getPosition(), getRookDestination(), getToCastle());
		updateReference(k.getPosition(), kingDestination, k);
		Image rImg;
		Image kImg;
		try{
			rImg = ImageIO.read(getClass().getResource(getToCastle().getSource().toString()));
			kImg = ImageIO.read(getClass().getResource(k.getSource().toString()));
			rDest.setIcon(new ImageIcon(rImg));
			kDest.setIcon(new ImageIcon(kImg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean rookEligible(Point kingDestination, King k){
		if(k.getTeam() == Team.WHITE){
			switch(kingDestination.y){
			case 2: 
				Point p = new Point(7, 0);
				Rook r = (Rook) pieces.get(p);
				if(r.isHasMoved() == false){
					setRookDestination(new Point(7, 3));
					setToCastle(r);
					return true;
				}
					break;
			case 6:
				Point p2 = new Point(7, 7);
				Rook r2 = (Rook) pieces.get(p2);
				if(r2.isHasMoved() == false){
					setRookDestination(new Point(7, 5));
					setToCastle(r2);
					return true;
				}
					break;
			default: return false;
			}
		}
		else{
			switch(kingDestination.y){
			case 2: 
				Point p = new Point(0, 0);
				Rook r = (Rook) pieces.get(p);
				if(r.isHasMoved() == false){
					setRookDestination(new Point(0, 3));
					setToCastle(r);
					return true;
				}
					break;
			case 6:
				Point p2 = new Point(0, 7);
				Rook r2 = (Rook) pieces.get(p2);
				if(r2.isHasMoved() == false){
					setRookDestination(new Point(0, 5));
					setToCastle(r2);
					return true;
				}
					break;
			default: return false;
			}
		}
		return false;
	}
	
	public boolean validPassant(Team t, Point destination){
		if(t == Team.WHITE){
			Square s = squares.get(new Point((destination.x + 1), destination.y));
			if(s.getPiece() != null && ((Pawn) s.getPiece()).isJumpWasLastMove() == true){
				setPassantVictim(s);
				return true;
			}
		}
		else{
			Square s = squares.get(new Point((destination.x - 1), destination.y));
			if(s.getPiece() != null && ((Pawn) s.getPiece()).isJumpWasLastMove() == true){
				setPassantVictim(s);
				return true;
			}
		}
		return false;
	}
	
	public void enPassant(Piece p, Square destination){
		Square passantSrc = squares.get(p.getPosition());
		Square passantDest = destination;
		Square passantVictim = getPassantVictim();
		passantDest.setPiece(p);
		passantSrc.setIcon(null);
		passantVictim.setIcon(null);
		updateReference(p.getPosition(), destination.getLocation(), p);
		Image pImg;
		try{
			pImg = ImageIO.read(getClass().getResource(p.getSource().toString()));
			passantDest.setIcon(new ImageIcon(pImg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateReference(Point source, Point destination, Piece piece){
		pieces.remove(source, piece);
		pieces.put(destination, piece);
	}

	public Square getSourceLocation() {
		return sourceSquare;
	}

	public void setSourceLocation(Square square) {
		this.sourceSquare = square;
	}

	public Point getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(Point destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public Piece getToMove() {
		return toMove;
	}

	public void setToMove(Piece toMove) {
		this.toMove = toMove;
	}

	public boolean isWhitePrivilege() {
		return whitePrivilege;
	}

	public void setWhitePrivilege(boolean whitePrivilege) {
		this.whitePrivilege = whitePrivilege;
	}

	public Point getRookDestination() {
		return rookDestination;
	}

	public void setRookDestination(Point rookDestination) {
		this.rookDestination = rookDestination;
	}

	public Rook getToCastle() {
		return toCastle;
	}

	public void setToCastle(Rook toCastle) {
		this.toCastle = toCastle;
	}

	public Square getPassantVictim() {
		return passantVictim;
	}

	public void setPassantVictim(Square passantVictim) {
		this.passantVictim = passantVictim;
	}

	public Checkable[] getKings() {
		return kings;
	}

	public void setKings(Checkable[] kings) {
		this.kings = kings;
	}

	public HashMap<Point, Square> getSquares() {
		return squares;
	}

	public void setSquares(HashMap<Point, Square> squares) {
		this.squares = squares;
	}

	public HashMap<Point, Piece> getPieces() {
		return pieces;
	}

	public void setPieces(HashMap<Point, Piece> pieces) {
		this.pieces = pieces;
	}

	public int getActiveKing() {
		return activeKing;
	}

	public void setActiveKing(int activeKing) {
		this.activeKing = activeKing;
	}
	
	public int getInactiveKing() {
		return inactiveKing;
	}

	public void setInactiveKing(int inactiveKing) {
		this.inactiveKing = inactiveKing;
	}

	public void switchKing(int activeKing){
		switch(getActiveKing()){
		case WHITE:
			setActiveKing(BLACK);
			setInactiveKing(WHITE);
				break;
		case BLACK:
			setActiveKing(WHITE);
			setInactiveKing(BLACK);
				break;
		}
	}
	
	public void replaceImage(){
		Image img;
		try{
			img = ImageIO.read(getClass().getResource(getToMove().getSource().toString()));
			squares.get(getToMove().getPosition()).setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean block(Piece toMove, Square dest){
		squares.get(toMove.getPosition()).setIcon(null);
		dest.setPiece(toMove);
		Image img;
		try{
			img = ImageIO.read(getClass().getResource(toMove.getSource().toString()));
			dest.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!getKings()[getActiveKing()].isChecked(((King) getKings()[getActiveKing()]).getPosition(), squares)){
			return true;
		}
		else{
			try{
				img = ImageIO.read(getClass().getResource(toMove.getSource().toString()));
				dest.setIcon(null);
				dest.setPiece(null);
				squares.get(toMove.getPosition()).setIcon(new ImageIcon(img));
				squares.get(toMove.getPosition()).setPiece(toMove);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		
	}
}
