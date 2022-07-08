package tetris.game.pieces;

/**
 * @author Ragaey
 *
 */
public class PieceImp implements Piece {

	    int width , height ;
		PieceType type ;
		boolean[][] body;
		Point rot;
		
	public PieceImp(int width, int height, PieceType type, Point rot, boolean[][] body){
		this.width=width; 
		this.height=height;
		this.rot = rot;
		this.type=type;
		this.body=body;
	}	

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public boolean[][] getBody() {
		return this.body;
	}

	@Override
	public Piece getClockwiseRotation() {
	
		boolean[][] rbody= new boolean[width][height];
		for (int q=height-1 ;q>=0; q--){
			for (int i= 0 ; i<width;i++ ){
				rbody[i][height-q-1]= this.body[q][i];
			}

		}
		Point rrot = new Point( this.rot.getColumn() , (this.height - 1 - this.rot.getRow()) ); 

		Piece result = new PieceImp(this.height, this.width, this.type , rrot,rbody);
		return result;
	}

	@Override
	public Piece getCounterClockwiseRotation() {
		boolean[][] rbody= new boolean[width][height];
		for (int q=width-1 ;q>=0; q--){
			for (int i= 0 ; i<height;i++ ){
				rbody[width-q-1][i]= this.body[i][q];
			}

		}
		Point rrot = new Point( (this.width - 1 - this.rot.getColumn()) , this.rot.getRow() ); 

		Piece result = new PieceImp(this.height, this.width, this.type , rrot,rbody);
		return result;
	}

	@Override
	public Point getRotationPoint() {
		return this.rot;
	}

	@Override
	public PieceType getPieceType() {
		return this.type;
	}

	@Override
	public Piece clone() {
		boolean[][] cbody= new boolean[this.height][this.width];
		for (int q=0;q<height;q++)
		{ for (int i =0;i<width ;i++){
			cbody[q][i]= this.body[q][i];
		}
		}

		Point crot = new Point(this.rot.getRow() , this.rot.getColumn());

		Piece result = new PieceImp(this.width, this.height, this.type , crot , cbody);
		return result;
	}

	@Override
    public boolean equals(Object obj){
		if(this == obj)
		return true;
		if (!(obj instanceof Piece))
		return false;
		PieceImp bla = (PieceImp) obj;
		boolean flag = this.width == bla.width
		&& this.height==bla.height
		&& this.rot.getRow()== bla.rot.getRow()
		&&this.rot.getColumn()== bla.rot.getColumn()
		&& this.type == bla.type;
		if(!flag)
		return flag;

		for (int q=0;q<this.height ;q++)
		{ for (int i =0;i<this.width ;i++)
			{ flag= flag && this.body[q][i] == bla.body[q][i];

			}
		}
		return  flag ;
	}
	public int hashCode() {
		return 42;
	  }

}
