
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Internet_getPrivateIpV4AddressTest {

    @Test
    public void testGetPrivateIpV4Address() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        RandomService randomService = Mockito.mock(RandomService.class);
        Internet internet = new Internet(faker);

        when(faker.random()).thenReturn(randomService);
        when(randomService.nextInt(256)).thenReturn(1); // Mock second, third, fourth octets
        when(randomService.nextInt(PRIVATE_FIRST_OCTET.length)).thenReturn(0); // Mock first octet to be 10

        // When
        InetAddress result = internet.getPrivateIpV4Address();

        // Then
        assertEquals("10.1.1.1", result.getHostAddress());
    }

    private static final Byte[] PRIVATE_FIRST_OCTET = {10, 127, (byte) 169, (byte) 192, (byte) 172};
    private static final Byte[] PRIVATE_SECOND_OCTET_172 = {16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
}
