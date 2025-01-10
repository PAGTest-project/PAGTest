
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Compass_wordTest {

    @Test
    public void testWordWithNullCompassPoint() {
        Compass compass = new Compass(null);
        String expected = "randomDirection";
        Compass spyCompass = spy(compass);
        doReturn(expected).when(spyCompass).resolve("compass.direction");

        String result = spyCompass.word();

        assertEquals(expected, result);
        verify(spyCompass).resolve("compass.direction");
    }

    @Test
    public void testWordWithNonNullCompassPoint() {
        Compass compass = new Compass(null);
        compass.compassPoint(Compass.CompassPoint.CARDINAL);
        String expected = "cardinalDirection";
        Compass spyCompass = spy(compass);
        doReturn(expected).when(spyCompass).resolve("compass.cardinal.word");

        String result = spyCompass.word();

        assertEquals(expected, result);
        verify(spyCompass).resolve("compass.cardinal.word");
    }
}
