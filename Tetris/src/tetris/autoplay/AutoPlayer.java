package tetris.autoplay;

import tetris.game.GameObserver;

/**
 * An autoPlayer is able to play the tetris game. It should try to play the game
 * most effectively.
 *
 */
public interface AutoPlayer extends GameObserver {

	/**
	 * Represents a move by an auto player.
	 */
	public enum Move {
		RIGHT, LEFT, DOWN, ROTATE_CW, ROTATE_CCW,
	}

	/**
	 * Returns the current move the AutoPlayer wants to make.
	 *
	 * @return the current move the AutoPlayer wants to make
	 */
	Move getMove();
}