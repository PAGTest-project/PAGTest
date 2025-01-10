
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
        BaseProviders faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                // Mock implementation for testing purposes
                return "mocked";
            }

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation to satisfy the abstract method
            }
        };
        compass = new Compass(faker);
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
