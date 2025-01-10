
package net.datafaker.providers.base;

import net.datafaker.providers.base.Compass.CompassPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Compass_azimuthTest {

    private Compass compass;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {};
        compass = new Compass(faker);
    }

    @Test
    void testAzimuthWithNullCompassPoint() {
        assertThat(compass.azimuth()).isEqualTo("compass.azimuth");
    }

    @ParameterizedTest
    @EnumSource(CompassPoint.class)
    void testAzimuthWithCompassPoint(CompassPoint compassPointOfDirection) {
        compass.compassPoint(compassPointOfDirection);
        assertThat(compass.azimuth()).isEqualTo("compass." + compassPointOfDirection.yamlKey + ".azimuth");
    }
}
