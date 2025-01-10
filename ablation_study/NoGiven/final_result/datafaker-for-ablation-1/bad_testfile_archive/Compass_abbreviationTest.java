
package net.datafaker.providers.base;

import net.datafaker.providers.base.Compass.CompassPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Compass_abbreviationTest {

    private Compass compass;

    @BeforeEach
    public void setUp() {
        compass = new Compass(new BaseProviders() {
            @Override
            public void addUrl(Locale locale, URL url) {
                // Implementation of the abstract method
            }
        });
    }

    @Test
    void abbreviationWithoutCompassPoint() {
        assertThat(compass.abbreviation()).isNotEmpty();
    }

    @ParameterizedTest
    @EnumSource(CompassPoint.class)
    void abbreviationWithCompassPoint(CompassPoint compassPoint) {
        compass.compassPoint(compassPoint);
        assertThat(compass.abbreviation()).isNotEmpty();
    }
}
