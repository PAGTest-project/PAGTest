
package net.datafaker.providers.base;

import net.datafaker.providers.base.Compass.CompassPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Compass_abbreviationTest {

    private Compass compass;

    @BeforeEach
    public void setUp() {
        compass = new Compass(new BaseProviders());
    }

    @Test
    public void testAbbreviationWithCompassPoint() {
        compass.compassPoint(CompassPoint.CARDINAL);
        assertEquals("compass.cardinal.abbreviation", compass.abbreviation());
    }

    @Test
    public void testAbbreviationWithoutCompassPoint() {
        assertEquals("compass.abbreviation", compass.abbreviation());
    }
}
