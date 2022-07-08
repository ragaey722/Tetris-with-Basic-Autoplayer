package tetris.game.pieces;

import java.util.Random;

public class PieceFactoryImp implements PieceFactory {

	 Random r;
	
	public PieceFactoryImp(Random r){
		this.r=r;
	}
	

	@Override
	public Piece getIPiece() {
		int width = 1 , height = 4;
		Piece.PieceType type =  Piece.PieceType.I;
		boolean[][] body= {
			{true},
			{true},
			{true},
			{true}
		};
		Point rot = new Point(1, 0);
		Piece result= new PieceImp(width, height, type, rot, body);
		return result;
	}

	@Override
	public Piece getJPiece() {
		int width = 2 , height = 3;
		Piece.PieceType type =  Piece.PieceType.J;
		boolean[][] body= {
			{false,true},
			{false,true},
			{true,true}
		};
		Point rot = new Point(1, 1);
		Piece result= new PieceImp(width, height, type, rot, body);
		return result;
	}

	@Override
	public Piece getLPiece() {
		int width = 2 , height = 3;
		Piece.PieceType type =  Piece.PieceType.L;
		boolean[][] body= {
			{true,false},
			{true,false},
			{true,true}
		};
		Point rot = new Point(1, 0);
		Piece result= new PieceImp(width, height, type, rot, body);
		return result;
	}

	@Override
	public Piece getOPiece() {
		int width = 2 , height = 2;
		Piece.PieceType type =  Piece.PieceType.O;
		boolean[][] body= {
			{true,true},
			{true,true}
		};
		Point rot = new Point(1, 1);
		Piece result= new PieceImp(width, height, type, rot, body);
		return result;
	}

	@Override
	public Piece getSPiece() {
		int width = 3 , height = 2;
		Piece.PieceType type =  Piece.PieceType.S; 
		boolean[][] body= {
			{false,true,true},
			{true,true,false}
		};
		Point rot = new Point(1, 1);
		Piece result= new PieceImp(width, height, type, rot, body);
		return result;
	}

	@Override
	public Piece getZPiece() {
		int width = 3 , height = 2;
		Piece.PieceType type =  Piece.PieceType.Z;
		boolean[][] body= {
			{true,true,false},
			{false,true,true}
		};
		Point rot = new Point(1, 1);
		Piece result= new PieceImp(width, height, type, rot, body);
		return result;
	}

	@Override
	public Piece getTPiece() {
		int width = 3 , height = 2;
		Piece.PieceType type =  Piece.PieceType.T;
		boolean[][] body= {
			{true,true,true},
			{false,true,false}
		};
		Point rot = new Point(0, 1);
		Piece result= new PieceImp(width, height, type, rot, body);
		return result;
	}

	@Override
	public Piece getNextRandomPiece() {
		
		int rand= r.nextInt(7);
	Piece result = getIPiece() ;
	switch (rand){
		case 0 : result = getIPiece() ;
		break;
		case 1 : result = getJPiece()  ;
		break;
		case 2 : result =  getLPiece() ; 
		break;
		case 3 : result = getOPiece() ;
		break;
		case 4 : result = getSPiece() ;
		break;
		case 5 : result = getTPiece() ;
		break;
		case 6 : result = getZPiece() ;
		break;

	}		
	return result;	
	}

}
