
package net.datafaker.providers.base;

import net.datafaker.providers.base.Compass.CompassPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Compass_wordTest {

    private Compass compass;

    @BeforeEach
    public void setUp() {
        compass = new Compass(new BaseProviders());
    }

    @Test
    void testWordWithNullCompassPoint() {
        assertThat(compass.word()).isNotEmpty();
    }

    @ParameterizedTest
    @EnumSource(CompassPoint.class)
    void testWordWithCompassPoint(CompassPoint compassPointOfDirection) {
        assertThat(compass.compassPoint(compassPointOfDirection).word()).isNotEmpty();
    }
}
