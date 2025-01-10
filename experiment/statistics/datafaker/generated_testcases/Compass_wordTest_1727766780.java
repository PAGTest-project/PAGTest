
package net.datafaker.providers.base;

import net.datafaker.providers.base.Compass.CompassPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Compass_wordTest {

    private Compass compass;

    @BeforeEach
    public void setUp() {
        compass = new Compass(new BaseProviders());
    }

    @Test
    public void testWordWithoutCompassPoint() {
        String result = compass.word();
        assertEquals("compass.direction", result);
    }

    @Test
    public void testWordWithCompassPoint() {
        compass.compassPoint(CompassPoint.CARDINAL);
        String result = compass.word();
        assertEquals("compass.cardinal.word", result);
    }
}
