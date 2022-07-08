package tetris.view;

import java.awt.Container;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import tetris.game.Board;
import tetris.game.MyTetrisFactory;
import tetris.game.TetrisGame;

public class Views {

	static final long SEED = 4711;

	static final Random RND = new Random(SEED);

	static final String MSG = "Standalone Prog2 Tetris Game";

	public static void main(String[] args) {
		createPlayerView(MyTetrisFactory.createTetrisGame(RND), MSG);
	}

	private static JFrame createWindow(String title, JPanel content) {
		JFrame frame = new JFrame(title);
		Container cp = frame.getContentPane();
		cp.add(content);
		for (KeyListener l : content.getKeyListeners())
			frame.addKeyListener(l);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		return frame;
	}

	/**
	 * Board view provides a basic view for a board.
	 */
	public static void createBoardView(Board board, String msg) {
		BoardComponent view = new BoardComponent(board, msg);
		createWindow("P R O G 2 | B O A R D   V I E W", view);
	}

	/**
	 * Piece view provides a basic view for all pieces and their rotations.
	 */
	public static void createPieceView() {
		BoardComponent view = new PieceComponent();
		createWindow("P R O G 2 | P I E C E   V I E W", view);
	}

	/**
	 * AutoPlayer view provides a view for a given game.
	 */
	public static void createAutoplayerView(TetrisGame game, String msg) {
		TetrisComponent view = new AutoplayerView(game, msg);
		createWindow("P R O G 2 | A U T O P L A Y E R   V I E W", view);
	}

	/**
	 * Player view provides a view + control for a given game.
	 */
	public static void createPlayerView(TetrisGame game, String msg) {
		TetrisComponent view = new PlayerView(game, msg);
		createWindow("P R O G 2 | P L A Y E R   V I E W", view);
	}
}
