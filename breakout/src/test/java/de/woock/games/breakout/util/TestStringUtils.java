package de.woock.games.breakout.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class TestStringUtils {

	@Test
	public void extractedRightString() {
		String logLine = "2013-03-05 10:07:19,113 [thread applet-de.woock.games.breakout.Breakout.class] INFO  (    Tile.java)     - create token: Token [character=N, points=40]";
		String extracted = StringUtils.extractTimeStringFromLogline(logLine);
		assertEquals("2013-03-05 10:07:19,113", extracted);
	}

	@Test
	public void stringWithNoDateThrowsException() {
		String logLine = "[thread applet-de.woock.games.breakout.Breakout.class] INFO  (    Tile.java)     - create token: Token [character=N, points=40]";
		assertThrows(IllegalArgumentException.class, () -> StringUtils.extractTimeStringFromLogline(logLine));
		
	}

}
