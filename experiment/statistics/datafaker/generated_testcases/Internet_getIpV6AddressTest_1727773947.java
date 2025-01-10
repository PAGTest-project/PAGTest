
package net.datafaker.providers.base;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class Internet_getIpV6AddressTest {

    @Test
    public void testGetIpV6Address() throws UnknownHostException {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        RandomService randomService = Mockito.mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.hex(4, false)).thenReturn("abcd");

        Internet internet = new Internet(faker);

        // When
        InetAddress result = internet.getIpV6Address();

        // Then
        assertEquals("abcd:abcd:abcd:abcd:abcd:abcd:abcd:abcd", result.getHostAddress());
    }

    @Test
    public void testGetIpV6Address_UnknownHostException() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        RandomService randomService = Mockito.mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.hex(4, false)).thenReturn("invalid");

        Internet internet = new Internet(faker);

        // When and Then
        assertThrows(RuntimeException.class, internet::getIpV6Address);
    }
}
