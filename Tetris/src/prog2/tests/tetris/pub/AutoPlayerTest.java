package prog2.tests.tetris.pub;

import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import prog2.tests.PublicTest;
import prog2.tests.tetris.AutoPlayerExercise;
import tetris.autoplay.AutoPlayer;
import tetris.autoplay.AutoPlayer.Move;
import tetris.game.MyTetrisFactory;
import tetris.game.TetrisGame;
import tetris.game.TetrisGameView;

public class AutoPlayerTest implements PublicTest, AutoPlayerExercise {

    @Rule
    public TestRule timeout = TestUtil.timeoutSeconds(1500);

    @Test
    public void validMovesOnly0() {
        playGame(6646813, 100);
    }

    @Test
    public void validMovesOnly1() {
        playGame(9315, 100);
    }

    private void performMove(TetrisGame game, Move nextMove) {
        boolean valid = true;
        switch (nextMove) {
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

        if (!valid)
            throw new IllegalArgumentException("AutoPlayer provided invalid move.");
    }

    private void playGame(int seed, int maxSteps) {
        TetrisGame game = MyTetrisFactory.createTetrisGame(new Random(seed));
        AutoPlayer auto = MyTetrisFactory.createAutoPlayer(new TetrisGameView(game));
        game.step();
        int steps = 0;
        while (!game.isGameOver() && steps < maxSteps) {
            Move nextMove = auto.getMove();
            if (nextMove == null) {
                game.setGameOver();
            } else {
                performMove(game, nextMove);
                ++steps;
            }
        }
    }
}
