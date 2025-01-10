
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_botUserAgentTest {

    @Test
    public void testBotUserAgentWithNullVendor() {
        Internet internet = new Internet(mock(BaseProviders.class));
        BotUserAgent mockAgent = mock(BotUserAgent.class);
        when(mockAgent.toString()).thenReturn("mockbot");
        when(BotUserAgent.any(any())).thenReturn(mockAgent);
        when(internet.resolve(anyString())).thenReturn("mockUserAgentString");

        String result = internet.botUserAgent(null);

        assertEquals("mockUserAgentString", result);
    }

    @Test
    public void testBotUserAgentWithNonNullVendor() {
        Internet internet = new Internet(mock(BaseProviders.class));
        BotUserAgent mockAgent = mock(BotUserAgent.class);
        when(mockAgent.toString()).thenReturn("mockbot");
        when(internet.resolve(anyString())).thenReturn("mockUserAgentString");

        String result = internet.botUserAgent(mockAgent);

        assertEquals("mockUserAgentString", result);
    }
}
