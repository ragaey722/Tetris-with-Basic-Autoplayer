package tetris.game;

import tetris.game.GameObserver;

/**
 * A GameObservable can have one or more observers. An observer may be any
 * object that implements interface {@link GameObserver}. Whenever a
 * GameObservable instance changes, the corresponding methods of the
 * GameObserver are called.
 *
 * The order in which the observers are called is unspecified.
 */
public interface GameObservable {

	/**
	 * Adds the given GameObserver to this observable.
	 *
	 * @param observer
	 *            the GameObserver to add
	 */
	void addObserver(GameObserver observer);

	/**
	 * Removes the given GameObserver from this observable.
	 *
	 * @param observer
	 *            the GameObserver to remove
	 */
	void removeObserver(GameObserver observer);
}
