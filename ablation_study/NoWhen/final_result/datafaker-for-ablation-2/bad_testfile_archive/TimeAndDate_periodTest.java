
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Period;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TimeAndDate_periodTest {
    private TimeAndDate timeAndDate;

    @BeforeEach
    public void setUp() {
        timeAndDate = new TimeAndDate(new BaseProviders() {
            @Override
            public void setupProvider() {
                // Dummy implementation to allow instantiation
            }
        });
    }

    @Test
    void testPeriodValidRange() {
        Period min = Period.of(1, 2, 3);
        Period max = Period.of(4, 5, 6);

        Period result = timeAndDate.period(min, max);

        assertThat(result.getYears()).isBetween(min.getYears(), max.getYears());
        assertThat(result.getMonths()).isBetween(min.getMonths(), max.getMonths());
        assertThat(result.getDays()).isBetween(min.getDays(), max.getDays());
    }

    @Test
    void testPeriodInvalidRange() {
        Period min = Period.of(4, 5, 6);
        Period max = Period.of(1, 2, 3);

        assertThatThrownBy(() -> timeAndDate.period(min, max))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Max period(P1Y2M3D) should be not less than min (P4Y5M6D)");
    }
}
