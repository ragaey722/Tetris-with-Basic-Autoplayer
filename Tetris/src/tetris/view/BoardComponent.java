package tetris.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import tetris.game.Board;
import tetris.game.MyTetrisFactory;
import tetris.game.pieces.Piece.PieceType;

@SuppressWarnings("serial")
class BoardComponent extends JPanel {

	private final String msg;
	private final Board board;

	/**
	 * Creates a view for the board.
	 *
	 * @param board
	 *            The board to create the view for.
	 * @param msg
	 *            An additional message that should be shown.
	 */
	public BoardComponent(Board board, String msg) {
		super();
		setLayout(new GridLayout());
		this.board = board;
		this.msg = msg;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getImageWidth(), getImageHeight());
	}

	protected int getMsgOffset() {
		return this.msg == null || this.msg.isEmpty() ? 0 : ViewHelper.V_OFFSET;
	}

	protected int getImageWidth() {
		return ViewHelper.BLOCK_SIZE * board.getNumberOfColumns() + ViewHelper.H_OFFSET * 2;
	}

	protected int getImageHeight() {
		return ViewHelper.BLOCK_SIZE * board.getNumberOfRows() + ViewHelper.V_OFFSET * 2 + getMsgOffset();
	}

	public void paintBoard(Graphics g, Board board) {
		PieceType[][] b = board.getBoard();

		int imageHeight = getImageHeight();
		int imageWidth = getImageWidth();
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D img = (Graphics2D) image.getGraphics();

		img.setColor(ViewHelper.BG_COLOR);
		img.fillRect(0, 0, imageWidth, imageHeight);

		if (msg != null) {
			img.setColor(ViewHelper.FG_COLOR);
			img.drawString(msg, ViewHelper.H_OFFSET, ViewHelper.V_OFFSET);
		}

		for (int row = 0; row < b.length; row++) {
			for (int column = 0; column < b[row].length; column++) {
				if (column % 2 == 1)
					img.setColor(ViewHelper.BG_COLOR_ODD);
				else
					img.setColor(ViewHelper.BG_COLOR_EVEN);

				img.fillRect(getColumnPos(column), getRowPos(row), ViewHelper.BLOCK_SIZE, ViewHelper.BLOCK_SIZE);

				if (b[row][column] == null)
					continue;

				img.setColor(ViewHelper.getColorForPieceType(b[row][column]).brighter());
				img.fillRect(getColumnPos(column), getRowPos(row), ViewHelper.BLOCK_SIZE - 2,
						ViewHelper.BLOCK_SIZE - 2);
				img.setColor(ViewHelper.getColorForPieceType(b[row][column]).darker());
				img.fillRect(getColumnPos(column) + 2, getRowPos(row) + 2, ViewHelper.BLOCK_SIZE - 2,
						ViewHelper.BLOCK_SIZE - 2);
				img.setColor(ViewHelper.getColorForPieceType(b[row][column]));
				img.fillRect(getColumnPos(column) + 1, getRowPos(row) + 1, ViewHelper.BLOCK_SIZE - 2,
						ViewHelper.BLOCK_SIZE - 2);

			}
		}
		g.drawImage(image, 0, 0, imageWidth, imageHeight, this);
		img.dispose();
	}

	protected int getRowPos(int row) {
		return row * ViewHelper.BLOCK_SIZE + ViewHelper.V_OFFSET + getMsgOffset();
	}

	protected int getColumnPos(int column) {
		return column * ViewHelper.BLOCK_SIZE + ViewHelper.H_OFFSET;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintBoard(g, board);
	}

	public static void main(String[] args) {
		Board board = MyTetrisFactory.createBoard(MyTetrisFactory.DEFAULT_ROWS, MyTetrisFactory.DEFAULT_COLUMNS);
		Views.createBoardView(board, "");
	}
}
