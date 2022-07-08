package tetris.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import tetris.game.MyTetrisFactory;
import tetris.game.pieces.Piece;
import tetris.game.pieces.PieceFactory;
import tetris.game.pieces.Point;

/**
 * Piece view can be used to graphically check the pieces. It displays all
 * pieces and the possible rotations, and shows the rotation point in a
 * different color.
 */
@SuppressWarnings("serial")
class PieceComponent extends BoardComponent {

	private final PieceFactory pieceFactory = MyTetrisFactory.createPieceFactory(new Random());

	public PieceComponent() {
		super(MyTetrisFactory.createBoard(29, 15), "");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = ViewHelper.H_OFFSET + ViewHelper.BLOCK_SIZE;
		int y = ViewHelper.V_OFFSET + ViewHelper.BLOCK_SIZE;
		Piece[] pieces = new Piece[] { pieceFactory.getIPiece(), pieceFactory.getJPiece(), pieceFactory.getLPiece(),
				pieceFactory.getOPiece(), pieceFactory.getSPiece(), pieceFactory.getTPiece(),
				pieceFactory.getZPiece() };
		for (Piece piece : pieces) {
			drawPieces(g, piece, x, y);
			y += ViewHelper.BLOCK_SIZE * (Math.max(piece.getHeight(), piece.getWidth()) + 1);
		}
	}

	/**
	 * Draw all possible rotations of a piece.
	 *
	 * @param g
	 *            the graphics object to use.
	 * @param startPiece
	 *            the piece to draw the rotation for
	 * @param x
	 *            the x position where to start drawing
	 * @param y
	 *            the y position where to start drawing
	 *
	 */
	private void drawPieces(Graphics g, Piece startPiece, int x, int y) {
		Piece rotationPiece = startPiece;
		int i = 0;
		do {
			drawPiece(g, rotationPiece, x, y);
			x += (rotationPiece.getWidth() + 1) * ViewHelper.BLOCK_SIZE;
			rotationPiece = rotationPiece.getClockwiseRotation();
			i++;
		} while (!startPiece.equals(rotationPiece) && i < 5);
	}

	/**
	 * Draw one rotation of a piece.
	 *
	 * @param g
	 *            the graphics object to use.
	 * @param piece
	 *            the piece to draw
	 * @param x
	 *            the x position where to start drawing
	 * @param y
	 *            the y position where to start drawing
	 *
	 */
	public static void drawPiece(Graphics g, Piece piece, int x, int y) {
		if (piece == null) {
			g.drawString("Could not display piece because it is null", x, y);
		} else {
			Color c = ViewHelper.getColorForPiece(piece);
			boolean[][] body = piece.getBody();
			Point rotationPoint = piece.getRotationPoint();
			for (int i = 0; i < body.length; i++) {
				for (int j = 0; j < body[i].length; j++) {
					if (!body[i][j])
						continue;
					g.setColor(c.brighter());
					g.fillRect(x + j * ViewHelper.BLOCK_SIZE, y + i * ViewHelper.BLOCK_SIZE, ViewHelper.BLOCK_SIZE - 2,
							ViewHelper.BLOCK_SIZE - 2);
					g.setColor(c.darker());
					g.fillRect(x + j * ViewHelper.BLOCK_SIZE + 2, y + i * ViewHelper.BLOCK_SIZE + 2,
							ViewHelper.BLOCK_SIZE - 2, ViewHelper.BLOCK_SIZE - 2);
					g.setColor(c);
					g.fillRect(x + j * ViewHelper.BLOCK_SIZE + 1, y + i * ViewHelper.BLOCK_SIZE + 1,
							ViewHelper.BLOCK_SIZE - 2, ViewHelper.BLOCK_SIZE - 2);
					if (rotationPoint.getRow() == i && rotationPoint.getColumn() == j) {
						g.setColor(ViewHelper.FG_COLOR);
						int smaller = 10;
						g.fillOval(smaller / 2 + x + j * ViewHelper.BLOCK_SIZE,
								smaller / 2 + y + i * ViewHelper.BLOCK_SIZE, ViewHelper.BLOCK_SIZE - smaller,
								ViewHelper.BLOCK_SIZE - smaller);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Views.createPieceView();
	}
}
