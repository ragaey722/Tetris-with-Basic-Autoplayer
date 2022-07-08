package tetris.game;

import tetris.game.pieces.Piece;

/**
 * The class TetrisGameView allows to view a tetris game and register observers
 * at the game.
 */
public class TetrisGameView implements GameObservable {

	private TetrisGame game;

	public TetrisGameView(TetrisGame game) {
		this.game = game;
	}

	/**
	 * Returns a copy of the current board of this game.
	 *
	 * @return a copy of the current board of this game
	 */
	public Board getBoardCopy() {
		return game.getBoard().clone();
	}

	/**
	 * Returns a copy of the current piece of the game.
	 *
	 * @return a copy of the current piece of the game
	 */
	public Piece getCurrentPieceCopy() {
		return game.getCurrentPiece().clone();
	}

	/**
	 * Returns a copy of the piece that comes next.
	 *
	 * @return a copy of the piece that comes next
	 */
	public Piece getNextPieceCopy() {
		return game.getNextPiece().clone();
	}

	/**
	 * Returns the column of the current piece.
	 *
	 * @return the column of the current piece
	 */
	public int getPieceColumn() {
		return game.getPieceColumn();
	}

	/**
	 * Returns the current row of the piece.
	 *
	 * @return the current row of the piece
	 */
	public int getPieceRow() {
		return game.getPieceRow();
	}

	/**
	 * Returns the number of points reached in the game.
	 *
	 * @return the number of points reached in the game
	 *
	 */
	public long getPoints() {
		return game.getPoints();
	}

	/**
	 * Returns true, if the game is over.
	 *
	 * @return true, if the game is over
	 */
	public boolean isGameOver() {
		return game.isGameOver();
	}

	@Override
	public void addObserver(GameObserver observer) {
		game.addObserver(observer);
	}

	@Override
	public void removeObserver(GameObserver observer) {
		game.removeObserver(observer);
	}

}
