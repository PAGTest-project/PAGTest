
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import java.net.InetAddress;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class Internet_getIpV6AddressTest {

    @Test
    public void testGetIpV6Address() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        RandomService randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.hex(4, false)).thenReturn("abcd");

        Internet internet = new Internet(faker);

        // When
        InetAddress result = internet.getIpV6Address();

        // Then
        assertNotNull(result);
    }
}
