
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
        Internet internet = new Internet(mock(BaseProviders.class));
        RandomService randomService = mock(RandomService.class);
        when(internet.faker.random()).thenReturn(randomService);
        when(randomService.hex(4, false)).thenReturn("abcd", "ef01", "2345", "6789", "abcd", "ef01", "2345", "6789");

        // When
        InetAddress result = internet.getIpV6Address();

        // Then
        assertEquals("abcd:ef01:2345:6789:abcd:ef01:2345:6789", result.getHostAddress());
    }
}
