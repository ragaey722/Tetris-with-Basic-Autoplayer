package tetris.view;

import java.util.Random;

import tetris.autoplay.AutoPlayer;
import tetris.autoplay.AutoPlayer.Move;
import tetris.game.MyTetrisFactory;
import tetris.game.TetrisGame;
import tetris.game.TetrisGameView;

/**
 * This class provides a view for the {@link AutoPlayer}.
 */
@SuppressWarnings("serial")
public class AutoplayerView extends TaskedTetrisComponent {

	private static final long SEED = 2372;

	private final AutoPlayer autoPlayer;

	public AutoplayerView(TetrisGame game, String msg) {
		super(game, msg, 20, 0, 20);
		this.autoPlayer = MyTetrisFactory.createAutoPlayer(new TetrisGameView(game));
	}

	private Move getNextMove() {
		Move nextMove = null;
		try {
			nextMove = autoPlayer.getMove();
		} catch (Exception e) {
			System.err.println("Caught exception in strategy");
			e.printStackTrace();
		}
		return nextMove;
	}

	@Override
	public void execute() {
		Move nextMove = getNextMove();
		if (nextMove == null) {
			getGame().setGameOver();
		} else {
			performMove(nextMove, true);
		}
	}

	public static void main(String[] args) {
		final TetrisGame game = MyTetrisFactory.createTetrisGame(new Random(SEED));
		Views.createAutoplayerView(game, "");
	}
}
