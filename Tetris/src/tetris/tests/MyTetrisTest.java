package tetris.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tetris.game.MyTetrisFactory;
import tetris.game.TetrisGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.rules.TestRule;

import prog2.tests.PublicTest;
import prog2.tests.tetris.PieceExercise;
import tetris.game.pieces.Piece;
import tetris.game.pieces.Piece.PieceType;
import tetris.game.pieces.PieceFactory;

/**
 * Within this class and/or package you can implement your own tests.
 *
 * Note that no classes or interfaces will be available, except those initially
 * provided. Make use of {@link MyTetrisFactory} to get other factory instances.
 */
public class MyTetrisTest {

	@Test
	public void test() {
		assertNotNull(MyTetrisFactory.createBoard(MyTetrisFactory.DEFAULT_ROWS, MyTetrisFactory.DEFAULT_COLUMNS));
	}
	

	private final static long SEED = 343681;

	PieceFactory pf = MyTetrisFactory.createPieceFactory(new Random(SEED));
	PieceFactory pf2 = MyTetrisFactory.createPieceFactory(new Random(SEED));
	@Test
	public void repr() throws Exception {
		boolean flag = true;
		for (int q=0 ; q<1000000 ;q++){
			flag = flag && checkPieceEquals(pf.getNextRandomPiece(), pf2.getNextRandomPiece());	
		}
		assertEquals(flag,true);
	}

	public boolean checkPieceEquals(Piece p0, Piece p1) {
		if (p0.getPieceType() != p1.getPieceType())
			return false;
		if (p0.getWidth() != p1.getWidth())
			return false;
		if (p0.getHeight() != p1.getHeight())
			return false;
		for (int i = 0; i < p0.getHeight(); i++)
			for (int j = 0; j < p0.getWidth(); j++)
				if (p0.getBody()[i][j] != p1.getBody()[i][j])
					return false;
		return true;
	}

	public void assertRotationsEqual(Piece start, int rotations) throws Exception {
		Piece rotation = start;
		for (int i = 0; i < rotations; i++) {
			rotation = rotation.getClockwiseRotation();
		}
		assertTrue(start.getPieceType().toString() + " piece is not equal to the piece got after " + rotations
				+ " rotations ", checkPieceEquals(start, rotation));
		assertEquals(
				start.getPieceType().toString() + " piece type is not equal to the piece type got after rotations ",
				start.getPieceType(), rotation.getPieceType());
	}

	@Test
	public void testForRandomness1() {
		PieceFactory pf1 = MyTetrisFactory.createPieceFactory(new Random(SEED));
		PieceFactory pf2 = MyTetrisFactory.createPieceFactory(new Random(SEED));
		PieceType pt1 = pf1.getNextRandomPiece().getPieceType();
		PieceType pt2 = pf2.getNextRandomPiece().getPieceType();
		boolean allEqual = true;
		for (int i = 0; i < 10; i++) {
			if (!pt1.equals(pf1.getNextRandomPiece().getPieceType())) {
				allEqual = false;
			}
			if (!pt2.equals(pf2.getNextRandomPiece().getPieceType())) {
				allEqual = false;
			}
		}
		if (allEqual) {
			fail("Piece Factory produced no random sequence");
		}
	}

	@Test
	public void testLPiece() throws Exception {
		Piece p = pf.getLPiece();
		checkPiece(p, 2, 3, 1, 0);
	}

	@Test
	public void testJPiece() throws Exception {
		Piece p = pf.getJPiece();
		checkPiece(p, 2, 3, 1, 1);
	}

	@Test
	public void testOPiece() throws Exception {
		Piece p = pf.getOPiece();
		checkPiece(p, 2, 2, 1, 1);
	}

	@Test
	public void testZPiece() throws Exception {
		Piece p = pf.getZPiece();
		checkPiece(p, 3, 2, 1, 1);
	}

	@Test
	public void testLPieceRotations() throws Exception {
		assertRotationsEqual(pf.getLPiece(), 4);
	}

	@Test
	public void testTPiece() throws Exception {
		Piece p = pf.getTPiece();
		checkPiece(p, 3, 2, 0, 1);
	}

	@Test
	public void testTPieceRotations() throws Exception {
		assertRotationsEqual(pf.getTPiece(), 4);
	}

	@Test
	public void testJPieceRotations() throws Exception {
		assertRotationsEqual(pf.getJPiece(), 4);
	}

	@Test
	public void testOPieceRotations() throws Exception {
		assertRotationsEqual(pf.getOPiece(), 4);
	}


	private void checkPiece(Piece piece, int width, int height, int rotationX, int rotationY) throws Exception {
		assertEquals("Width of piece is wrong", width, piece.getWidth());
		assertEquals("Height of piece is wrong", height, piece.getHeight());
		assertEquals("X coordinate of rotation point is wrong", rotationX, piece.getRotationPoint().getRow());
		assertEquals("Y coordinate of rotation point is wrong", rotationY, piece.getRotationPoint().getColumn());
	}
	
	

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
		//Assert.assertTrue("Should not be able to move piece left", g.moveLeft());
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
		Assert.assertNotEquals(g.getCurrentPiece(), null);
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
