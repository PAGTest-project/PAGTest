
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Time_pastTest {

    private Time time;

    @BeforeEach
    public void setup() {
        BaseProviders faker = new BaseProviders();
        time = new Time(faker);
    }

    @Test
    public void testPast() {
        int atLeast = 1;
        ChronoUnit unit = ChronoUnit.HOURS;

        long result = time.past(atLeast, unit);

        LocalTime now = LocalTime.now();
        long atLeastTime = now.minus(atLeast, unit).toNanoOfDay();
        long timeNano = now.toNanoOfDay();

        assertTrue(result >= atLeastTime && result < timeNano);
    }
}
