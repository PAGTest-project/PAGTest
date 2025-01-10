
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
        BaseProviders faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                return "resolvedValue"; // Placeholder implementation for testing purposes
            }
        };
        compass = new Compass(faker);
    }

    @Test
    void azimuthWithoutCompassPoint() {
        assertThat(compass.azimuth()).isNotEmpty();
    }

    @ParameterizedTest
    @EnumSource(CompassPoint.class)
    void azimuthWithCompassPoint(CompassPoint compassPoint) {
        compass.compassPoint(compassPoint);
        assertThat(compass.azimuth()).isNotEmpty();
    }
}
