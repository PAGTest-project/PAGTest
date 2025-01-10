
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.InetAddress;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Internet_getPublicIpV4AddressTest {

    @Test
    public void testGetPublicIpV4Address() {
        // Given
        Internet internet = new Internet(Mockito.mock(BaseProviders.class));
        RandomService randomService = Mockito.mock(RandomService.class);
        when(internet.faker.random()).thenReturn(randomService);

        // Mocking the random values to avoid private IP ranges
        when(randomService.nextInt(256)).thenReturn(1, 2, 3, 4); // First call returns 1, second returns 2, etc.

        // When
        InetAddress result = internet.getPublicIpV4Address();

        // Then
        byte[] expectedAddress = new byte[]{1, 2, 3, 4};
        assertEquals(Arrays.toString(expectedAddress), Arrays.toString(result.getAddress()));
    }
}
