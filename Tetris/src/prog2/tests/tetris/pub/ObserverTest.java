package prog2.tests.tetris.pub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import prog2.tests.PublicTest;
import prog2.tests.tetris.GameExercise;
import tetris.game.GameObserver;
import tetris.game.MyTetrisFactory;
import tetris.game.TetrisGame;

public class ObserverTest implements PublicTest, GameExercise {

	@Rule
	public TestRule timeout = TestUtil.timeoutSeconds(5);

	private final static long SEED = 343681;

	private static class MyObserver implements GameObserver {
		int gameOver;
		int pieceLanded;
		int piecePositionChanged;

		@Override
		public void gameOver() {
			gameOver++;
		}

		@Override
		public void pieceLanded() {
			pieceLanded++;
		}

		@Override
		public void piecePositionChanged() {
			piecePositionChanged++;
		}

		@Override
		public void rowsCompleted() {
		}
	}

	private TetrisGame game;
	private MyObserver observer;

	@Before
	public void setUp() {
		game = MyTetrisFactory.createTetrisGame(new Random(SEED));
		observer = new MyObserver();
		game.addObserver(observer);
	}

	@Test
	public void testGameOver() {
		for (int i = 0; i < 100; i++) {
			game.step();
		}
		assertTrue("Game over on the observers was not called", observer.gameOver > 0);
	}

	@Test
	public void testPieceLanded() {
		game.step();
		while (game.moveDown()) {
		}
		game.step();
		while (game.moveDown()) {
		}
		game.step();
		assertEquals("PieceLanded was not called on the observer the right number of times", 2, observer.pieceLanded);
	}

	@Test
	public void testPositionChanged() {
		assertEquals("PositionChanged was not called on the observer the right number of times", 0,
				observer.piecePositionChanged);
		game.step();
		int i = 0;
		while (game.moveDown()) {
			i++;
			if (game.moveLeft())
				i++;
		}
		assertEquals("PositionChanged was not called on the observer the right number of times", i,
				observer.piecePositionChanged);
	}

}
