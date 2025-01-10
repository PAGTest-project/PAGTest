
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_userAgentTest {

    @Test
    public void testUserAgentWithNullInput() {
        Internet internet = new Internet(mock(BaseProviders.class));
        net.datafaker.providers.base.UserAgent mockUserAgent = mock(net.datafaker.providers.base.UserAgent.class);
        when(mockUserAgent.toString()).thenReturn("chrome");
        when(internet.faker.random().nextDouble()).thenReturn(0.0); // Ensure UserAgent.any returns the first enum value
        when(internet.resolve("internet.user_agent.chrome")).thenReturn("Mocked Chrome User Agent");

        String result = internet.userAgent(null);

        assertEquals("Mocked Chrome User Agent", result);
    }

    @Test
    public void testUserAgentWithNonNullInput() {
        Internet internet = new Internet(mock(BaseProviders.class));
        net.datafaker.providers.base.UserAgent mockUserAgent = mock(net.datafaker.providers.base.UserAgent.class);
        when(mockUserAgent.toString()).thenReturn("firefox");
        when(internet.resolve("internet.user_agent.firefox")).thenReturn("Mocked Firefox User Agent");

        String result = internet.userAgent(mockUserAgent);

        assertEquals("Mocked Firefox User Agent", result);
    }
}
