
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Address_streetAddressTest {

    @Test
    public void testStreetAddressWithoutSecondary() {
        BaseFaker faker = mock(BaseFaker.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        when(faker.fakeValuesService()).thenReturn(fakeValuesService);

        Address address = new Address(faker);

        when(fakeValuesService.resolve("address.street_address", address, faker.context)).thenReturn("123 Main St");

        String result = address.streetAddress(false);
        assertEquals("123 Main St", result);
    }

    @Test
    public void testStreetAddressWithSecondary() {
        BaseFaker faker = mock(BaseFaker.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        when(faker.fakeValuesService()).thenReturn(fakeValuesService);

        Address address = new Address(faker);

        when(fakeValuesService.resolve("address.street_address", address, faker.context)).thenReturn("123 Main St");
        when(fakeValuesService.resolve("address.secondary_address", address, faker.context)).thenReturn("Apt 4B");

        String result = address.streetAddress(true);
        assertEquals("123 Main St Apt 4B", result);
    }
}
