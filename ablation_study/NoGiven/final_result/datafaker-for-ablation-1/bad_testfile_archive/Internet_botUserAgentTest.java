
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
        when(internet.faker.resolve("internet.bot_user_agent.mockbot")).thenReturn("MockBotUserAgent");

        String result = internet.botUserAgent(null);

        assertEquals("MockBotUserAgent", result);
    }

    @Test
    public void testBotUserAgentWithNonNullVendor() {
        Internet internet = new Internet(mock(BaseProviders.class));
        BotUserAgent mockAgent = mock(BotUserAgent.class);
        when(mockAgent.toString()).thenReturn("mockbot");
        when(internet.faker.resolve("internet.bot_user_agent.mockbot")).thenReturn("MockBotUserAgent");

        String result = internet.botUserAgent(mockAgent);

        assertEquals("MockBotUserAgent", result);
    }
}
