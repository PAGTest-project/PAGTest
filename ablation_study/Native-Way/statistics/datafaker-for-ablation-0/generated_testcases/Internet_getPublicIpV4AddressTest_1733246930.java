
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Internet_getPublicIpV4AddressTest {

    @Test
    public void testGetPublicIpV4Address() {
        // Given
        Internet internet = new Internet(null);
        RandomService randomService = Mockito.mock(RandomService.class);
        internet.faker = Mockito.mock(BaseProviders.class);
        when(internet.faker.random()).thenReturn(randomService);

        // When
        when(randomService.nextInt(256)).thenReturn(11); // First octet not in PRIVATE_FIRST_OCTET
        when(randomService.nextInt(256)).thenReturn(1, 2, 3); // Second, Third, Fourth octets

        InetAddress result = internet.getPublicIpV4Address();

        // Then
        assertEquals("11.1.2.3", result.getHostAddress());
    }
}
