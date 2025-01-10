
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Twitter_createdTimeTest {
    private Twitter twitter;

    @BeforeEach
    public void setUp() {
        BaseProviders baseProviders = new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService();
            }
        };
        twitter = new Twitter(baseProviders);
    }

    @Test
    public void testCreatedTimeForward() {
        Date base = new Date(1609459200000L); // January 1, 2021
        Date constraints = new Date(1640995200000L); // January 1, 2022
        Date result = twitter.createdTime(true, base, constraints);
        assertTrue(result.after(base) && result.before(constraints));
    }

    @Test
    public void testCreatedTimeBackward() {
        Date base = new Date(1640995200000L); // January 1, 2022
        Date constraints = new Date(1609459200000L); // January 1, 2021
        Date result = twitter.createdTime(false, base, constraints);
        assertTrue(result.before(base) && result.after(constraints));
    }
}
