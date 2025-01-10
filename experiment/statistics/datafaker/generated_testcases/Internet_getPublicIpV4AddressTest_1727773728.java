
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Internet_getPublicIpV4AddressTest {

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
    public void testGetPublicIpV4Address() {
        // Given
        when(randomService.nextInt(256)).thenReturn(11, 22, 33, 44); // First octet not in PRIVATE_FIRST_OCTET
        when(randomService.nextInt(256)).thenReturn(55, 66, 77, 88); // Second, Third, Fourth octets

        // When
        InetAddress result = internet.getPublicIpV4Address();

        // Then
        assertEquals("11.55.66.77", result.getHostAddress());
    }

    @Test
    public void testGetPublicIpV4AddressWithPrivateFirstOctet() {
        // Given
        when(randomService.nextInt(256)).thenReturn(10, 127, 169, 192, 172, 22); // First octet in PRIVATE_FIRST_OCTET, then valid first octet
        when(randomService.nextInt(256)).thenReturn(33, 44, 55, 66); // Second, Third, Fourth octets

        // When
        InetAddress result = internet.getPublicIpV4Address();

        // Then
        assertEquals("22.33.44.55", result.getHostAddress());
    }
}
