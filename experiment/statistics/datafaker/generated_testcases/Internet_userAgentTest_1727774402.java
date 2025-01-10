
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_userAgentTest {

    @Test
    public void testUserAgentWithNullInput() {
        Internet internet = new Internet(mock(BaseProviders.class));
        String result = internet.userAgent(null);
        assertNotNull(result);
    }

    @Test
    public void testUserAgentWithNonNullInput() {
        Internet internet = new Internet(mock(BaseProviders.class));
        UserAgent mockUserAgent = mock(UserAgent.class);
        when(mockUserAgent.toString()).thenReturn("chrome");
        String result = internet.userAgent(mockUserAgent);
        assertEquals("internet.user_agent.chrome", result);
    }
}
