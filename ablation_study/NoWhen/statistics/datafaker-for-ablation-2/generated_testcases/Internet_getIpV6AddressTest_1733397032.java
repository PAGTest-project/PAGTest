
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_getIpV6AddressTest {

    @Test
    public void testGetIpV6Address() {
        // Given
        RandomService randomService = mock(RandomService.class);
        when(randomService.hex(4, false)).thenReturn("abcd", "ef01", "2345", "6789", "abcd", "ef01", "2345", "6789");

        Internet internet = new Internet(null) {
            @Override
            public RandomService random() {
                return randomService;
            }
        };

        // When
        InetAddress result = internet.getIpV6Address();

        // Then
        assertEquals("abcd:ef01:2345:6789:abcd:ef01:2345:6789", result.getHostAddress());
    }
}
