
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class Twitter_createdTimeTest {

    private Twitter twitter;
    private RandomService randomService;

    @BeforeEach
    public void setUp() {
        BaseProviders baseProviders = Mockito.mock(BaseProviders.class);
        randomService = Mockito.mock(RandomService.class);
        when(baseProviders.random()).thenReturn(randomService);
        twitter = new Twitter(baseProviders);
    }

    @Test
    public void testCreatedTimeForward() {
        Date base = new Date(1000000L);
        Date constraints = new Date(2000000L);
        when(randomService.nextDouble()).thenReturn(0.5);

        Date result = twitter.createdTime(true, base, constraints);

        assertTrue(result.after(base) && result.before(constraints));
    }

    @Test
    public void testCreatedTimeBackward() {
        Date base = new Date(2000000L);
        Date constraints = new Date(1000000L);
        when(randomService.nextDouble()).thenReturn(0.5);

        Date result = twitter.createdTime(false, base, constraints);

        assertTrue(result.before(base) && result.after(constraints));
    }
}
