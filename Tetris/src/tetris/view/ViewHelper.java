package tetris.view;

import java.awt.Color;

import tetris.game.pieces.Piece;

public class ViewHelper {

	public static final int V_OFFSET = 25;

	public static final int H_OFFSET = 25;

	public static final int BLOCK_SIZE = 25;

	public static final Color FG_COLOR = Color.WHITE;

	public static final Color BG_COLOR = Color.BLACK;

	public static final Color BG_COLOR_ODD = Color.LIGHT_GRAY.darker();

	public static final Color BG_COLOR_EVEN = Color.LIGHT_GRAY;

	public static Color getColorForPiece(Piece piece) {
		assert (piece != null);
		return getColorForPieceType(piece.getPieceType());
	}

	public static Color getColorForPieceType(Piece.PieceType pieceType) {
		switch (pieceType) {
		case I:
			return Color.CYAN;
		case J:
			return Color.BLUE;
		case L:
			return Color.ORANGE;
		case O:
			return Color.YELLOW;
		case S:
			return new Color(0x80FF00); // Lime
		case T:
			return new Color(0x800080); // Lime
		case Z:
			return Color.RED;
		}
		throw new IllegalArgumentException("Unknown piece type!");
	}
}
