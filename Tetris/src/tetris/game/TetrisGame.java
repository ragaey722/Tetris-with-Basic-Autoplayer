package tetris.game;

import tetris.game.pieces.Piece;

/**
 * Class TetrisGame represents the tetris game. It manages the {@link Board} the
 * current and the next piece and the number of completed rows and reached
 * points.
 *
 */
public interface TetrisGame extends GameObservable {

	/**
	 * Returns the current piece of the game.
	 *
	 * @return the current piece of the game
	 */
	public Piece getCurrentPiece();

	/**
	 * Returns the current board of this game.
	 *
	 * @return the current board of this game
	 */
	public Board getBoard();

	/**
	 * Returns the piece that comes next.
	 *
	 * @return the piece that comes next
	 */
	public Piece getNextPiece();

	/**
	 * Return the number of completed rows.
	 *
	 * @return the number of completed rows
	 */
	public int getNumberOfCompletedRows();

	/**
	 * Returns the column of the current piece.
	 *
	 * @return the column of the current piece
	 */
	public int getPieceColumn();

	/**
	 * Returns the current row of the piece.
	 *
	 * @return the current row of the piece
	 */
	public int getPieceRow();

	/**
	 * Returns the number of points reached in the game.
	 *
	 * @return the number of points reached in the game
	 *
	 */
	public long getPoints();

	/**
	 * Returns true, if the game is over.
	 *
	 * @return true, if the game is over
	 */
	public boolean isGameOver();

	/**
	 * Moves the current piece one step down.
	 *
	 * @return true if the piece can be moved down.
	 */
	public boolean moveDown();

	/**
	 * Moves the current piece to the left.
	 *
	 * @return true if the piece can be moved to the left.
	 */
	public boolean moveLeft();

	/**
	 * Moves the current piece to the right.
	 *
	 * @return true if the piece can be moved to the right.
	 */
	public boolean moveRight();

	/**
	 * Brings a new Piece into the game, informs the observers, and deletes
	 * complete rows.
	 *
	 * @return true, if the next piece can be added.
	 */
	public boolean newPiece();

	/**
	 * Rotates the current piece in clockwise rotation.
	 *
	 * @return true if the piece can be rotated
	 */
	public boolean rotatePieceClockwise();

	/**
	 * Rotates the current piece in counter clockwise rotation.
	 *
	 * @return true if the piece can be rotated
	 */
	public boolean rotatePieceCounterClockwise();

	/**
	 * Sets the state of the game to game over.
	 */
	public void setGameOver();

	/**
	 * Carries out one step in the game. The current piece is moved down, if the
	 * piece can be moved down. If not, a new piece is added. If no piece can be
	 * added anymore the game is over.
	 *
	 */
	public void step();

}
