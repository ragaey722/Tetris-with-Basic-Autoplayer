package tetris.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import tetris.autoplay.AutoPlayer.Move;
import tetris.game.Board;
import tetris.game.GameObserver;
import tetris.game.TetrisGame;
import tetris.game.pieces.Piece;

@SuppressWarnings("serial")
public class TetrisComponent extends BoardComponent implements GameObserver {

	/**
	 * The game played.
	 */
	private final TetrisGame game;

	public TetrisGame getGame() {
		return game;
	}

	public TetrisComponent(TetrisGame game, String msg) {
		super(game.getBoard(), msg);
		this.game = game;
		game.addObserver(this);
		if (game.getCurrentPiece() == null)
			game.step();
	}

	@Override
	protected int getImageHeight() {
		return super.getImageHeight() + ViewHelper.BLOCK_SIZE * 3;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (game.isGameOver()) {
			paintGameOver(g, game.getBoard());
			return;
		}

		int x = ViewHelper.H_OFFSET;
		int y = super.getImageHeight() + ViewHelper.BLOCK_SIZE;
		g.setFont(new Font("", Font.BOLD, 15));
		g.setColor(ViewHelper.FG_COLOR);
		g.drawString("Rows: " + game.getNumberOfCompletedRows(), x, y);
		g.drawString("Points: " + game.getPoints(), x, y + ViewHelper.BLOCK_SIZE);

		Piece piece = game.getNextPiece();
		while (piece.getHeight() > piece.getWidth())
			piece = piece.getClockwiseRotation();

		x = getImageWidth() - 2 * ViewHelper.H_OFFSET - piece.getWidth() * ViewHelper.BLOCK_SIZE;
		PieceComponent.drawPiece(g, piece, x, y - ViewHelper.BLOCK_SIZE);
	}

	private void paintGameOver(Graphics g, Board board) {
		g.setFont(new Font("", Font.BOLD, 30));
		g.setColor(Color.RED);
		g.fillRect(0, 0, getImageWidth(), getImageHeight());
		g.setColor(Color.WHITE);
		int x = getImageWidth() / 2 - 100;
		int y = getImageHeight() / 2;
		g.drawString("GAME OVER", x, y);
		x += ViewHelper.BLOCK_SIZE;
		y += ViewHelper.BLOCK_SIZE;
		g.setFont(new Font("", Font.BOLD, 20));
		g.drawString("Rows: " + game.getNumberOfCompletedRows(), x, y);
		y += ViewHelper.BLOCK_SIZE;
		g.drawString("Points: " + game.getPoints(), x, y);
	}

	public void gameOver() {
		repaint();
	}

	public void pieceLanded() {
		repaint();
	}

	public void piecePositionChanged() {
		repaint();
	}

	public void rowsCompleted() {
		repaint();
	}

	protected void performMove(Move move, boolean validate) {
		synchronized (game) {
			if (game.isGameOver())
				return;

			boolean valid = true;
			switch (move) {
			case DOWN:
				game.step();
				break;
			case LEFT:
				valid = game.moveLeft();
				break;
			case RIGHT:
				valid = game.moveRight();
				break;
			case ROTATE_CCW:
				valid = game.rotatePieceCounterClockwise();
				break;
			case ROTATE_CW:
				valid = game.rotatePieceClockwise();
				break;
			default:
				throw new IllegalArgumentException("Unknown move kind");
			}

			if (validate && !valid)
				throw new IllegalArgumentException("Player performed invalid move (" + move + ").");
		}
	}

}
