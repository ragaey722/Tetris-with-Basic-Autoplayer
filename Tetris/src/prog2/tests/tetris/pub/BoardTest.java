package prog2.tests.tetris.pub;

import static org.junit.Assert.assertFalse;

import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import prog2.tests.PublicTest;
import prog2.tests.tetris.BoardExercise;
import tetris.game.Board;
import tetris.game.MyTetrisFactory;
import tetris.game.pieces.Piece;
import tetris.game.pieces.PieceFactory;

public class BoardTest implements PublicTest, BoardExercise {

	@Rule
	public TestRule timeout = TestUtil.timeoutSeconds(5);

	Board board;
	PieceFactory pf = MyTetrisFactory.createPieceFactory(new Random());
	Piece p = pf.getJPiece();

	@Before
	public void setUp() {
		board = MyTetrisFactory.createBoard(15, 24);
	}

	@Test
	public void testAdd() throws Exception {
		board.addPiece(p, 10, 10);
		assertFalse(
				"Seems that it is not possible to add a piece to the board (canAddPiece returned true after adding)",
				board.canAddPiece(p, 10, 10));
	}

	@Test
	public void testCanAdd() throws Exception {
		assertFalse("should not be able to add piece at a wrong position", board.canAddPiece(p, -1, -1));
		assertFalse("should not be able to add piece at a wrong position", board.canAddPiece(p, 1000, 1000));
		assertFalse("should not be able to add piece at a wrong position", board.canAddPiece(p, 0, 0));
	}
}
