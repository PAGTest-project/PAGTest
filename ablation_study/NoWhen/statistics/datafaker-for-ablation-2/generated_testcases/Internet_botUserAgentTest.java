
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_botUserAgentTest {

    @Test
    public void testBotUserAgentWithNullVendor() {
        BaseProviders faker = mock(BaseProviders.class);
        Internet internet = new Internet(faker);
        BotUserAgent mockAgent = mock(BotUserAgent.class);
        when(mockAgent.toString()).thenReturn("mockbot");
        when(BotUserAgent.any(faker)).thenReturn(mockAgent);
        when(internet.resolve("internet.bot_user_agent.mockbot")).thenReturn("MockBotUserAgent");

        String result = internet.botUserAgent(null);

        assertEquals("MockBotUserAgent", result);
    }

    @Test
    public void testBotUserAgentWithNonNullVendor() {
        BaseProviders faker = mock(BaseProviders.class);
        Internet internet = new Internet(faker);
        BotUserAgent mockAgent = mock(BotUserAgent.class);
        when(mockAgent.toString()).thenReturn("mockbot");
        when(internet.resolve("internet.bot_user_agent.mockbot")).thenReturn("MockBotUserAgent");

        String result = internet.botUserAgent(mockAgent);

        assertEquals("MockBotUserAgent", result);
    }
}
