package tetris.game.pieces;

import tetris.game.pieces.Piece;

/**
 * A piece factory is used to produce the different pieces during a game.
 */
public interface PieceFactory {

	/**
	 * Returns the I piece that has this shape:
	 *  +
	 *  +
	 *  +
	 *  +
	 *
	 * @return an I piece
	 */
	Piece getIPiece();


	/**
	 * Returns the J piece that has this shape:
	 *   +
	 *   +
	 *  ++
	 *
	 * @return a J piece
	 */
	Piece getJPiece();


	/**
	 * Returns the L piece that has this shape:
	 *  +
	 *  +
	 *  ++
	 *
	 * @return a L piece
	 */
	Piece getLPiece();

	/**
	 * Returns the O piece that has this shape:
	 *  ++
	 *  ++
	 *
	 * @return an O piece
	 */
	Piece getOPiece();

	/**
	 * Returns the S piece that has this shape:
	 *   ++
	 *  ++
	 *
	 * @return an S piece
	 */
	Piece getSPiece();

	/**
	 * Returns the Z piece that has this shape:
	 * ++
	 *  ++
	 *
	 * @return a Z piece
	 */
	Piece getZPiece();

	/**
	 * Returns the T piece that has this shape:
	 *  +++
	 *   +
	 * @return an T piece
	 */
	Piece getTPiece();

	/**
	 * Returns a randomly chosen tetris piece.
	 *
	 * @return a randomly chosen tetris piece
	 */
	Piece getNextRandomPiece();

}
