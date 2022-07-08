package prog2.tests.tetris.pub;

import java.util.concurrent.TimeUnit;

import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

public class TestUtil {

	/**
	 * Timeouts for the JUnit Tests, wrapped for easier debugging in Eclipse.
	 */
	public static TestRule timeoutSeconds(long seconds) {
		return new DisableOnDebug(new Timeout(seconds, TimeUnit.SECONDS));
	}

}
