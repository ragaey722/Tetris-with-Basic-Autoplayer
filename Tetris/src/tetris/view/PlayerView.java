package tetris.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import tetris.autoplay.AutoPlayer.Move;
import tetris.game.MyTetrisFactory;
import tetris.game.TetrisGame;

@SuppressWarnings("serial")
public class PlayerView extends TaskedTetrisComponent implements KeyListener {

	private static final long SEED = 17;

	/**
	 * Time in milliseconds until a step (move down) is initially forced. If set
	 * to a negative number no steps will be enforced, thus the user has
	 * unlimited time.
	 */
	private final static double INITIAL_STEP_TIME = 500;

	/**
	 * Used to adjust the time between steps after one was performed.
	 */
	private final static double STEP_TIME_ADJUSTMENT = 0.5;

	/**
	 * Minimal time between steps.
	 */
	private final static double MINIMAL_STEP_TIME = 100;

	public PlayerView(TetrisGame game, String msg) {
		super(game, msg, INITIAL_STEP_TIME, STEP_TIME_ADJUSTMENT, MINIMAL_STEP_TIME);

		addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Move move = null;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			move = Move.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			move = Move.RIGHT;
			break;
		case KeyEvent.VK_DOWN:
			move = Move.DOWN;
			break;
		case KeyEvent.VK_Q:
			move = Move.ROTATE_CCW;
			break;
		case KeyEvent.VK_W:
			move = Move.ROTATE_CW;
			break;
		default:
			return;
		}

		if (move == Move.DOWN)
			adjustDelayAndResetTimer();
		performMove(move, false);
	}

	@Override
	public void execute() {
		performMove(Move.DOWN, false);
	}

	public static void main(String[] args) {
		final TetrisGame game = MyTetrisFactory.createTetrisGame(new Random(SEED));
		Views.createPlayerView(game, "");
	}
}
