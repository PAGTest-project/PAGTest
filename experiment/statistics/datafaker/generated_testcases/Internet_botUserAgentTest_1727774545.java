
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class Internet_botUserAgentTest {

    @Test
    public void testBotUserAgentWithNullVendor() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Internet internet = new Internet(faker);
        BotUserAgent mockAgent = mock(BotUserAgent.class);
        when(mockAgent.toString()).thenReturn("mockBot");
        when(BotUserAgent.any(faker)).thenReturn(mockAgent);
        when(faker.resolve("internet.bot_user_agent.mockBot")).thenReturn("MockBotUserAgent");

        // When
        String result = internet.botUserAgent(null);

        // Then
        assertNotNull(result);
        verify(faker).resolve("internet.bot_user_agent.mockBot");
    }

    @Test
    public void testBotUserAgentWithNonNullVendor() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Internet internet = new Internet(faker);
        BotUserAgent vendor = mock(BotUserAgent.class);
        when(vendor.toString()).thenReturn("specificBot");
        when(faker.resolve("internet.bot_user_agent.specificBot")).thenReturn("SpecificBotUserAgent");

        // When
        String result = internet.botUserAgent(vendor);

        // Then
        assertNotNull(result);
        verify(faker).resolve("internet.bot_user_agent.specificBot");
    }
}
