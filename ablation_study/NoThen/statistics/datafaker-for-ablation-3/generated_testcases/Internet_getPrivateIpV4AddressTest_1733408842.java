
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class Internet_getPrivateIpV4AddressTest {

    private Internet internet;
    private RandomService randomService;

    @BeforeEach
    public void setUp() {
        BaseProviders baseProviders = Mockito.mock(BaseProviders.class);
        randomService = Mockito.mock(RandomService.class);
        when(baseProviders.random()).thenReturn(randomService);
        internet = new Internet(baseProviders);
    }

    @Test
    public void testGetPrivateIpV4Address() throws UnknownHostException {
        // Given
        when(randomService.nextInt(256)).thenReturn(10, 20, 30, 40);
        when(randomService.nextInt(16)).thenReturn(5);

        // When
        InetAddress result = internet.getPrivateIpV4Address();

        // Then
        assertEquals("10.20.30.40", result.getHostAddress());
    }

    @Test
    public void testGetPrivateIpV4Address_172() throws UnknownHostException {
        // Given
        when(randomService.nextInt(256)).thenReturn(172, 20, 30, 40);
        when(randomService.nextInt(16)).thenReturn(5);

        // When
        InetAddress result = internet.getPrivateIpV4Address();

        // Then
        assertEquals("172.21.30.40", result.getHostAddress());
    }

    @Test
    public void testGetPrivateIpV4Address_192() throws UnknownHostException {
        // Given
        when(randomService.nextInt(256)).thenReturn(192, 20, 30, 40);

        // When
        InetAddress result = internet.getPrivateIpV4Address();

        // Then
        assertEquals("192.168.30.40", result.getHostAddress());
    }

    @Test
    public void testGetPrivateIpV4Address_169() throws UnknownHostException {
        // Given
        when(randomService.nextInt(256)).thenReturn(169, 20, 30, 40);

        // When
        InetAddress result = internet.getPrivateIpV4Address();

        // Then
        assertEquals("169.254.30.40", result.getHostAddress());
    }

    @Test
    public void testGetPrivateIpV4Address_UnknownHostException() {
        // Given
        when(randomService.nextInt(256)).thenReturn(10, 20, 30, 40);
        when(randomService.nextInt(16)).thenReturn(5);

        // When and Then
        assertThrows(RuntimeException.class, () -> {
            internet.getPrivateIpV4Address();
        });
    }
}
