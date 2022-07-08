package prog2.tests.tetris.pub;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import prog2.tests.PublicTest;
import prog2.tests.tetris.GameExercise;
import tetris.game.MyTetrisFactory;
import tetris.game.TetrisGame;
import tetris.game.pieces.Piece;
import tetris.game.pieces.PieceFactory;

public class GameTest implements PublicTest, GameExercise {

	@Rule
	public TestRule timeout = TestUtil.timeoutSeconds(5);

	private final static long SEED = 343681;

	PieceFactory f;
	TetrisGame g;

	@Before
	public void setUp() throws Exception {
		g = MyTetrisFactory.createTetrisGame(new Random(SEED));
		f = MyTetrisFactory.createPieceFactory(new Random(SEED));
	}

	@Test
	public void testMoveLeft() {
		g.newPiece();
		int leftestBefore = getLeftestBlock();
		g.moveLeft();
		int leftestAfter = getLeftestBlock();
		Assert.assertEquals("Piece should have moved left 1 unit in moveLeft()!", leftestAfter, leftestBefore - 1);
	}

	@Test
	public void testMoveLeftAtLimit() {
		g.newPiece();

		for (int i = 0; i < g.getBoard().getNumberOfColumns(); i++) {
			g.moveLeft();
		}

		Assert.assertFalse("Should not be able to move piece left", g.moveLeft());
	}

	@Test
	public void testNewPiece() {
		g.newPiece();
		boolean found = false;
		for (int i = 0; i < g.getBoard().getNumberOfRows(); i++) {
			for (int j = 0; j < g.getBoard().getNumberOfColumns(); j++) {
				if (g.getBoard().getBoard()[i][j] != null) {
					found = true;
				}
			}
		}
		if (!found) {
			Assert.fail("newPiece() did not add a new Piece.");
		}
	}

	@Test
	public void testNextPieceReallyUsed() {
		g.newPiece();
		Piece nextPiece = g.getNextPiece();
		for (int i = 0; i < g.getBoard().getNumberOfRows() + 5; i++) {
			g.step();
		}
		Assert.assertEquals("getNextPiece() did not predict really used piece", nextPiece, g.getCurrentPiece());
	}

	@Test
	public void testScoreWith2Rows() {
		for (int i = 0; i < g.getBoard().getNumberOfColumns(); i += 2) {
			g.getBoard().addPiece(f.getOPiece(), g.getBoard().getNumberOfRows() - 1, i + 1);
		}
		Assert.assertEquals("Not starting with 0 points", 0, g.getPoints());
		g.step();
		while (g.moveDown()) {
		}
		g.step();
		Assert.assertEquals("Clearing 2 rows at once didn't give 300 points", 300, g.getPoints());
	}

	private int getLeftestBlock() {
		int leftestAfter = g.getBoard().getNumberOfColumns();
		for (int i = 0; i < g.getBoard().getNumberOfRows(); i++) {
			for (int j = 0; j < g.getBoard().getNumberOfColumns(); j++) {
				if (g.getBoard().getBoard()[i][j] != null) {
					if (leftestAfter > j) {
						leftestAfter = j;
					}
				}
			}
		}
		return leftestAfter;
	}

}
