
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_userAgentTest {

    @Test
    public void testUserAgentWithNullInput() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Internet internet = new Internet(faker);
        UserAgent mockUserAgent = mock(UserAgent.class);
        when(mockUserAgent.toString()).thenReturn("chrome");
        when(UserAgent.any(faker)).thenReturn(mockUserAgent);
        when(internet.resolve("internet.user_agent.chrome")).thenReturn("Mocked Chrome User Agent");

        // When
        String result = internet.userAgent(null);

        // Then
        assertEquals("Mocked Chrome User Agent", result);
    }

    @Test
    public void testUserAgentWithNonNullInput() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Internet internet = new Internet(faker);
        UserAgent userAgent = UserAgent.CHROME;
        when(internet.resolve("internet.user_agent.chrome")).thenReturn("Mocked Chrome User Agent");

        // When
        String result = internet.userAgent(userAgent);

        // Then
        assertEquals("Mocked Chrome User Agent", result);
    }
}
