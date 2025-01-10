
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import java.net.InetAddress;
import java.net.UnknownHostException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class Internet_getPrivateIpV4AddressTest {

    @Test
    public void testGetPrivateIpV4Address() throws UnknownHostException {
        // Given
        Internet internet = new Internet(mock(BaseProviders.class));
        RandomService randomService = mock(RandomService.class);
        when(internet.faker.random()).thenReturn(randomService);

        // When
        when(randomService.nextInt(256)).thenReturn(1); // Ensure second, third, fourth octets are 1
        when(randomService.nextInt(16)).thenReturn(0); // Ensure second octet for 172.x.x.x is 16

        InetAddress result = internet.getPrivateIpV4Address();

        // Then
        byte[] address = result.getAddress();
        assertTrue(address[0] == 10 || address[0] == 127 || address[0] == (byte) 169 || address[0] == (byte) 192 || address[0] == (byte) 172);
        if (address[0] == (byte) 172) {
            assertEquals(16, address[1]);
        } else if (address[0] == (byte) 192) {
            assertEquals(168, address[1]);
        } else if (address[0] == (byte) 169) {
            assertEquals(254, address[1]);
        }
        assertEquals(1, address[2]);
        assertEquals(1, address[3]);
    }
}
