
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Address_streetAddressTest {

    @Test
    void testStreetAddressWithoutSecondary() {
        BaseProviders faker = mock(BaseProviders.class);
        Address address = new Address(faker);

        when(address.resolve("address.street_address")).thenReturn("123 Main St");

        String result = address.streetAddress(false);
        assertEquals("123 Main St", result);
    }

    @Test
    void testStreetAddressWithSecondary() {
        BaseProviders faker = mock(BaseProviders.class);
        Address address = new Address(faker);

        when(address.resolve("address.street_address")).thenReturn("123 Main St");
        when(address.secondaryAddress()).thenReturn("Apt 4B");

        String result = address.streetAddress(true);
        assertEquals("123 Main St Apt 4B", result);
    }
}
