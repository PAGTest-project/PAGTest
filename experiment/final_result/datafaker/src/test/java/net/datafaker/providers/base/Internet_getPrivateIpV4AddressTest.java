
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Internet_getPrivateIpV4AddressTest {

    private Internet internet;
    private RandomService randomService;

    @BeforeEach
    public void setUp() {
        BaseFaker faker = Mockito.mock(BaseFaker.class);
        randomService = Mockito.mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        internet = new Internet(faker);
    }

    @Test
    public void testGetPrivateIpV4Address() throws UnknownHostException {
        // Given
        when(randomService.nextInt(256)).thenReturn(10, 20, 30); // second, third, fourth octets
        when(randomService.nextInt(5)).thenReturn(0); // first octet index

        // When
        InetAddress result = internet.getPrivateIpV4Address();

        // Then
        assertEquals("10.10.20.30", result.getHostAddress());
    }
}
