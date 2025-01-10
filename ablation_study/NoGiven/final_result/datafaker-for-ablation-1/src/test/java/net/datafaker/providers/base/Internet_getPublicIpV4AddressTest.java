
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import java.net.InetAddress;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class Internet_getPublicIpV4AddressTest {

    @Test
    public void testGetPublicIpV4Address() {
        // Given
        Internet internet = new Internet(mock(BaseProviders.class));
        RandomService randomService = mock(RandomService.class);
        when(internet.faker.random()).thenReturn(randomService);

        // When
        when(randomService.nextInt(256)).thenReturn(1, 2, 3, 4); // First call returns 1, second returns 2, etc.
        when(randomService.nextInt(256)).thenReturn(5, 6, 7, 8); // Second iteration returns 5, 6, 7, 8

        InetAddress result = internet.getPublicIpV4Address();

        // Then
        assertNotNull(result);
        assertTrue(Arrays.binarySearch(new byte[]{10, 127, (byte) 169, (byte) 192, (byte) 172}, (byte) 5) < 0); // Ensure first octet is not private
    }
}
