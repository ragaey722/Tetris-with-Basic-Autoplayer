package tetris.game.pieces;

/**
 * The Piece interface represents a piece in the tetris game.
 */
public interface Piece {

	/**
	 * PieceType is used to mark a pieces type (the base rotation it resulted
	 * from).
	 */
	enum PieceType {
		L, J, T, O, I, Z, S;
	}

	/**
	 * Returns the width of this tetris piece.
	 *
	 * @return the width of this tetris piece
	 */
	int getWidth();

	/**
	 * Returns the height of this tetris piece.
	 *
	 * @return the height of this tetris piece
	 */
	int getHeight();

	/**
	 * returns the body of this tetris piece.
	 *
	 * @return the body of this tetris piece.
	 */
	boolean[][] getBody();

	/**
	 * Returns a new piece that results from rotating this piece clockwise. This
	 * piece must not be modified by the operation.
	 *
	 * @return the piece that results from rotating this piece clockwise
	 */
	Piece getClockwiseRotation();

	/**
	 * Returns a new piece that results from rotating this piece
	 * counterclockwise. This piece must not be modified by the operation.
	 *
	 * @return the piece that results from rotating this piece counterclockwise
	 */
	Piece getCounterClockwiseRotation();

	/**
	 * Returns the rotation point of this piece.
	 *
	 * @return the rotation point of this piece
	 */
	Point getRotationPoint();

	/**
	 * Returns the type of this piece {@see PieceType}.
	 *
	 * @return the type of this piece
	 */
	PieceType getPieceType();

	/**
	 * Produces a deep copy of the piece.
	 *
	 * @return a deep copy of this piece.
	 */
	public Piece clone();
}
