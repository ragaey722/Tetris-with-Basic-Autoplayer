package tetris.game;

import tetris.game.pieces.Piece;
import tetris.game.pieces.Piece.PieceType;

public class BoardImp implements Board {

	int rows, columns ;
	PieceType[][] board;

	public BoardImp(int rows, int columns){
		this.rows=rows;
		this.columns=columns;
		this.board= new PieceType[rows][columns];
		for (int q=0;q<rows;q++){
			for (int i =0;i<columns;i++){
				this.board[q][i]=null;
			}
		}
	}

	public BoardImp(int rows, int columns, PieceType[][] board ){
		this.rows=rows;
		this.columns=columns;
		this.board = board;
	}

	@Override
	public PieceType[][] getBoard() {
		return this.board;
	}

	@Override
	public int getNumberOfRows() {
		return this.rows;
	}

	@Override
	public int getNumberOfColumns() {
		
		return this.columns;
	}

	@Override
	public void addPiece(Piece piece, int row, int column) {
		if (piece == null)
		throw new IllegalArgumentException();
		
		 if(canAddPiece(piece, row, column))
			{
				for(int q=0;q<piece.getHeight();q++){
					for(int i =0;i<piece.getWidth();i++){
						if(piece.getBody()[q][i])
						this.board[row-piece.getRotationPoint().getRow()+q][column-piece.getRotationPoint().getColumn()+i] = piece.getPieceType(); 
					}
				}
				return;
			}
		

		throw new IllegalArgumentException();

	}

	@Override
	public boolean canAddPiece(Piece piece, int row, int column) {
		if (piece == null)
		throw new IllegalArgumentException();
		if( row - piece.getRotationPoint().getRow() >= 0  &&
		 row + piece.getHeight()-1-piece.getRotationPoint().getRow() < this.rows &&
		 column- piece.getRotationPoint().getColumn()>=0 &&
		 column + piece.getWidth()-1-piece.getRotationPoint().getColumn() <this.columns)
		 { 
			for(int q =0; q<piece.getHeight();q++)
			{ for (int i =0;i<piece.getWidth();i++)
				{ if(piece.getBody()[q][i] && 
				this.board[row-piece.getRotationPoint().getRow()+q][column-piece.getRotationPoint().getColumn()+i]!= null)
				return false;
					
				}

			}

			return true;
		 }

		return false;
	}

	@Override
	public void removePiece(Piece piece, int row, int column) {
		if (piece == null)
		throw new IllegalArgumentException();
		
		 if(canRemovePiece(piece, row, column))
			{
				for(int q=0;q<piece.getHeight();q++){
					for(int i =0;i<piece.getWidth();i++){
						if(piece.getBody()[q][i])
						this.board[row-piece.getRotationPoint().getRow()+q][column-piece.getRotationPoint().getColumn()+i] = null; 
					}
				}
				return;
			}
		

		throw new IllegalArgumentException();

	}

	@Override
	public boolean canRemovePiece(Piece piece, int row, int column) {
		if (piece == null)
		throw new IllegalArgumentException();
		if( row - piece.getRotationPoint().getRow() >= 0  &&
		 row + piece.getHeight()-1-piece.getRotationPoint().getRow() < this.rows &&
		 column- piece.getRotationPoint().getColumn()>=0 &&
		 column + piece.getWidth()-1-piece.getRotationPoint().getColumn() <this.columns)
		 { 
			for(int q =0; q<piece.getHeight();q++)
			{ for (int i =0;i<piece.getWidth();i++)
				{ if(piece.getBody()[q][i] && 
				this.board[row-piece.getRotationPoint().getRow()+q][column-piece.getRotationPoint().getColumn()+i]!= piece.getPieceType())
				return false;
					
				}

			}

			return true;
		 }

		return false;
	}

	@Override
	public int deleteCompleteRows() {
		int res=0;
		for (int q=rows-1;q>=0;q--){
			boolean flag = true;
			for (int i=0;i<this.columns;i++)
			{ if(this.board[q][i]==null)
				flag=false;
			}
			if (flag)
			{  res++;
				for( int i =0 ;i<columns;i++)
				board[q][i]=null;
				for (int t = q;t>0;t--)
				{
					for (int i =0;i<this.columns;i++)
					{ board[t][i] = board[t-1][i];
					  board[t-1][i]=null;
					}
				}
				q++;

			}

		}


		return res;
	}

	@Override
	public Board clone() {
		PieceType[][] cboard = new PieceType[rows][columns];
		for(int q=0;q<rows;q++)
		for(int i=0;i<columns;i++)
		cboard[q][i]=board[q][i]; 
		Board result = new BoardImp(this.rows, this.columns, cboard);
		return result;
	}

}
