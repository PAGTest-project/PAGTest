
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class Internet_userAgentTest {

    @Test
    public void testUserAgentWithNullAgent() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Internet internet = new Internet(faker);
        UserAgent mockUserAgent = mock(UserAgent.class);
        when(mockUserAgent.toString()).thenReturn("chrome");
        when(UserAgent.any(faker)).thenReturn(mockUserAgent);
        when(internet.resolve("internet.user_agent.chrome")).thenReturn("MockUserAgentString");

        // When
        String result = internet.userAgent(null);

        // Then
        assertNotNull(result);
    }

    @Test
    public void testUserAgentWithNonNullAgent() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Internet internet = new Internet(faker);
        UserAgent mockUserAgent = mock(UserAgent.class);
        when(mockUserAgent.toString()).thenReturn("firefox");
        when(internet.resolve("internet.user_agent.firefox")).thenReturn("MockUserAgentString");

        // When
        String result = internet.userAgent(mockUserAgent);

        // Then
        assertNotNull(result);
    }
}
