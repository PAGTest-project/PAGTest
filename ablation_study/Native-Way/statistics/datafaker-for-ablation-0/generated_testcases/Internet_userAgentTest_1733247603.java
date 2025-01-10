
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_userAgentTest {

    @Test
    public void testUserAgentWithNull() {
        Internet internet = new Internet(mock(BaseProviders.class));
        UserAgent mockUserAgent = mock(UserAgent.class);
        when(mockUserAgent.toString()).thenReturn("chrome");
        when(internet.faker.random().nextDouble()).thenReturn(0.0); // Ensure UserAgent.any returns the first enum value
        when(internet.resolve("internet.user_agent.chrome")).thenReturn("Chrome User Agent");

        String result = internet.userAgent(null);

        assertEquals("Chrome User Agent", result);
    }

    @Test
    public void testUserAgentWithNonNull() {
        Internet internet = new Internet(mock(BaseProviders.class));
        UserAgent mockUserAgent = mock(UserAgent.class);
        when(mockUserAgent.toString()).thenReturn("firefox");
        when(internet.resolve("internet.user_agent.firefox")).thenReturn("Firefox User Agent");

        String result = internet.userAgent(mockUserAgent);

        assertEquals("Firefox User Agent", result);
    }
}
