
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import java.net.InetAddress;
import java.net.UnknownHostException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_getIpV6AddressTest {

    @Test
    public void testGetIpV6Address() throws UnknownHostException {
        // Given
        Internet internet = new Internet(new BaseProviders() {
            @Override
            public RandomService random() {
                RandomService mockRandom = mock(RandomService.class);
                when(mockRandom.hex(4, false)).thenReturn("abcd");
                return mockRandom;
            }
        });

        // When
        InetAddress result = internet.getIpV6Address();

        // Then
        assertEquals("abcd:abcd:abcd:abcd:abcd:abcd:abcd:abcd", result.getHostAddress());
    }
}
