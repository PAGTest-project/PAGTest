
package net.datafaker.providers.base;

import net.datafaker.providers.base.Compass.CompassPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Compass_azimuthTest {

    private Compass compass;

    @BeforeEach
    public void setUp() {
        compass = new Compass(new BaseProviders() {});
    }

    @Test
    public void testAzimuthWithNullCompassPoint() {
        assertEquals("compass.azimuth", compass.azimuth());
    }

    @Test
    public void testAzimuthWithCompassPoint() {
        compass.compassPoint(CompassPoint.CARDINAL);
        assertEquals("compass.cardinal.azimuth", compass.azimuth());
    }
}
