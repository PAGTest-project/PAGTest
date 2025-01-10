
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Address_streetAddressTest {

    @Test
    public void testStreetAddressWithoutSecondary() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Address address = new Address(faker);
        when(address.resolve("address.street_address")).thenReturn("123 Main St");

        // When
        String result = address.streetAddress(false);

        // Then
        assertEquals("123 Main St", result);
    }

    @Test
    public void testStreetAddressWithSecondary() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Address address = new Address(faker);
        when(address.resolve("address.street_address")).thenReturn("123 Main St");
        when(address.secondaryAddress()).thenReturn("Apt 4B");

        // When
        String result = address.streetAddress(true);

        // Then
        assertEquals("123 Main St Apt 4B", result);
    }
}
