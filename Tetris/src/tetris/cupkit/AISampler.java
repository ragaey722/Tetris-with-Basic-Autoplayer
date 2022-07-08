package tetris.cupkit;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import tetris.autoplay.AutoPlayer;
import tetris.autoplay.AutoPlayer.Move;
import tetris.game.MyTetrisFactory;
import tetris.game.TetrisGame;
import tetris.game.TetrisGameView;

public class AISampler {

	private final ExecutorService executorService = Executors.newSingleThreadExecutor();

	public long playout(long seed) throws IllegalArgumentException, ExecutionException {
		TetrisGame game = MyTetrisFactory.createTetrisGame(new Random(seed));
		game.step();
		AutoPlayer player = MyTetrisFactory.createAutoPlayer(new TetrisGameView(game));
		long points = playout(game, player);
		System.out.println("Seed                " + seed);
		return points;
	}

	public long playout(TetrisGame game, AutoPlayer player) throws IllegalArgumentException, ExecutionException {

		// Tournament limitations
		final long stepLimit = 5000;
		final long totalThinkTimeMS = 50 * 1000;

		// ms to ns
		final long totalThinkTimeNS = totalThinkTimeMS * 1000000;

		// tracking
		long elapsedThinkTimeNS = 0;

		long startTimeNS = System.nanoTime();
		long steps;
		for (steps = 0; steps < stepLimit && !game.isGameOver();) {

			long startThinkTimeNS = System.nanoTime();

			// Query next AI decision,
			// make sure non-termination is handled properly
			Future<Move> task = executorService.submit(() -> player.getMove());

			Move move;
			try {
				move = task.get(totalThinkTimeMS, TimeUnit.MILLISECONDS);
			} catch (TimeoutException | InterruptedException e) {
				System.out.println(
						"A single move request exceeded the total timeout, the AI is either diverging or way too slow.");
				break;
			}
			long endThinkTimeNS = System.nanoTime();
			elapsedThinkTimeNS += endThinkTimeNS - startThinkTimeNS;

			// Enforce think time limit
			if (elapsedThinkTimeNS >= totalThinkTimeNS) {
				break;
			}

			// Execute the selected move
			boolean valid = true;
			switch (move) {
			case DOWN:
				game.step();
				steps++;
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

			if (!valid)
				throw new IllegalArgumentException("AI attempted invalid move");
		}
		long endTimeNS = System.nanoTime();

		long elapsedTimeNS = endTimeNS - startTimeNS;
		double elapsedMS = elapsedTimeNS / 1000000.0;
		double elapsedThinkMS = elapsedThinkTimeNS / 1000000.0;

		System.out.println("---------------------------------------");
		System.out.println("Points              " + game.getPoints());
		System.out.println("Steps               " + steps);
		System.out.println("Elapsed time        " + elapsedMS + " ms");
		System.out.println("Elapsed think time  " + elapsedThinkMS + " ms");
		System.out.println("AvgTime per step    " + (elapsedMS / steps) + " ms");
		return game.getPoints();
	}

	public static void main(String[] args) throws IllegalArgumentException, ExecutionException  {
		Random seeder = new Random();
		for (long i = 0; i < 5; ++i) {
			new AISampler().playout(seeder.nextInt());
		}
	}
}
