package tetris.game;

import tetris.game.GameObservable;

/**
 * GameObserver can register at a {@link GameObservable} to be notified when the
 * state of the observable changes.
 */
public interface GameObserver {

	/**
	 * This method is called by the GameObservable every time one or more rows
	 * are completed.
	 */
	void rowsCompleted();

	/**
	 * This method is called by the GameObservable every time the position of
	 * the current piece's position is changed.
	 */
	void piecePositionChanged();

	/**
	 * This method is called by the GameObservable every time when a piece
	 * lands, that is it cannot move down anymore.
	 */
	void pieceLanded();

	/**
	 * This method is called by the GameObservable when the game is over.
	 */
	void gameOver();
}