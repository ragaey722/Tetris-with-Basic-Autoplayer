package prog2.tests.tetris.pub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import prog2.tests.PublicTest;
import prog2.tests.tetris.PieceExercise;
import tetris.game.MyTetrisFactory;
import tetris.game.pieces.Piece;
import tetris.game.pieces.Piece.PieceType;
import tetris.game.pieces.PieceFactory;

public class PieceTest implements PublicTest, PieceExercise {

	@Rule
	public TestRule timeout = TestUtil.timeoutSeconds(5);

	private final static long SEED = 343681;

	PieceFactory pf = MyTetrisFactory.createPieceFactory(new Random(SEED));

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

	private void checkPiece(Piece piece, int width, int height, int rotationX, int rotationY) throws Exception {
		assertEquals("Width of piece is wrong", width, piece.getWidth());
		assertEquals("Height of piece is wrong", height, piece.getHeight());
		assertEquals("X coordinate of rotation point is wrong", rotationX, piece.getRotationPoint().getRow());
		assertEquals("Y coordinate of rotation point is wrong", rotationY, piece.getRotationPoint().getColumn());
	}
}
