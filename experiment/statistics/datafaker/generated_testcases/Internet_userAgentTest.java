
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_userAgentTest {

    @Test
    public void testUserAgentWithNullInput() {
        BaseProviders mockBaseProviders = mock(BaseProviders.class);
        when(mockBaseProviders.random()).thenReturn(new RandomService());
        Internet internet = new Internet(mockBaseProviders);
        String result = internet.userAgent(null);
        assertNotNull(result);
    }

    @Test
    public void testUserAgentWithNonNullInput() {
        BaseProviders mockBaseProviders = mock(BaseProviders.class);
        Internet internet = new Internet(mockBaseProviders);
        UserAgent mockUserAgent = mock(UserAgent.class);
        when(mockUserAgent.toString()).thenReturn("chrome");
        String result = internet.userAgent(mockUserAgent);
        assertEquals("internet.user_agent.chrome", result);
    }
}
