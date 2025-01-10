
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class Time_futureTest {

    private Time time;
    private BaseProviders faker;
    private Number number;

    @BeforeEach
    public void setUp() {
        faker = Mockito.mock(BaseProviders.class);
        number = Mockito.mock(Number.class);
        when(faker.number()).thenReturn(number);
        time = new Time(faker);
    }

    @Test
    public void testFuture() {
        // Given
        int atMost = 1;
        ChronoUnit unit = ChronoUnit.HOURS;
        LocalTime now = LocalTime.of(12, 0);
        long expectedAtMostTime = now.plus(atMost, unit).toNanoOfDay();
        long expectedTime = now.toNanoOfDay();

        // When
        when(number.numberBetween(expectedTime, expectedAtMostTime)).thenReturn(expectedTime + 1);

        // Then
        long result = time.future(atMost, unit);
        assertTrue(result > expectedTime && result < expectedAtMostTime);
    }
}
