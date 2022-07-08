package tetris.game;

import tetris.game.pieces.Piece;
import tetris.game.pieces.Piece.PieceType;

/**
 * The Board class represents the board where tetris can be played on. Pieces
 * can be added and removed from the board.
 */
public interface Board {

	/**
	 * Returns the underlying board array.
	 *
	 * @return The the underlying board array.
	 */
	public PieceType[][] getBoard();

	/**
	 * Returns the number of rows of the board.
	 *
	 * @return the number of rows of the board
	 */
	public int getNumberOfRows();

	/**
	 * Returns the number of columns of the board.
	 *
	 * @return the number of columns of the board
	 */
	public int getNumberOfColumns();

	/**
	 * Adds a piece at the given row and column.
	 *
	 *
	 * @param piece
	 *            the piece to add
	 * @param row
	 *            the row where to add the piece
	 * @param column
	 *            the column where to add the piece
	 *
	 * @throws IllegalArgumentException
	 *             if the piece cannot be added, e.g. canAddPiece returns false,
	 *             or the given piece is null.
	 */
	public void addPiece(Piece piece, int row, int column);

	/**
	 * Checks if the piece can be added, e.g. the coordinates are on the board,
	 * and the corresponding positions are not occupied.
	 *
	 * @param piece
	 *            the piece to add
	 * @param row
	 *            the row where to add the piece
	 * @param column
	 *            the column where to add the piece
	 *
	 * @return true, if the piece can be added.
	 *
	 *
	 * @throws IllegalArgumentException
	 *             if the given piece is null.
	 */
	public boolean canAddPiece(Piece piece, int row, int column);

	/**
	 * Removes a piece at the given row and column.
	 *
	 * @param piece
	 *            the piece to to remove
	 * @param row
	 *            the row where to remove the piece
	 * @param column
	 *            the column where to remove the piece
	 *
	 * @throws IllegalArgumentException
	 *             if the piece cannot be removed, e.g. canRemovePiece returns
	 *             false, or if the given piece is null.
	 */
	public void removePiece(Piece piece, int row, int column);

	/**
	 * Checks if the piece can be removed, e.g. the coordinates are on the
	 * board, and the corresponding positions have the same {@link PieceType} as
	 * the given piece.
	 *
	 * @param piece
	 *            the piece to remove
	 * @param row
	 *            the row where to remove the piece
	 * @param column
	 *            the column where to remove the piece
	 *
	 * @return true, if the piece can be removed.
	 *
	 * @throws IllegalArgumentException
	 *             if the given piece is null.
	 */
	public boolean canRemovePiece(Piece piece, int row, int column);

	/**
	 * Checks if the board has complete rows and deletes them.
	 *
	 * @return the number of complete rows.
	 */
	public int deleteCompleteRows();

	/**
	 * Produces a deep copy of the board.
	 *
	 * @return a deep copy of this board.
	 */
	public Board clone();
}
