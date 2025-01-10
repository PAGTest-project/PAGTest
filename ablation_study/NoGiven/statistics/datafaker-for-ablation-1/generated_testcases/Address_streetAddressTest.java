
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import net.datafaker.service.FakeValuesService;
import net.datafaker.service.FakerContext;

public class Address_streetAddressTest {

    @Test
    public void testStreetAddressWithoutSecondary() {
        BaseFaker faker = mock(BaseFaker.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        FakerContext context = mock(FakerContext.class);
        when(faker.fakeValuesService()).thenReturn(fakeValuesService);
        when(faker.context()).thenReturn(context);

        Address address = new Address(faker);

        when(fakeValuesService.resolve("address.street_address", address, context)).thenReturn("123 Main St");

        String result = address.streetAddress(false);
        assertEquals("123 Main St", result);
    }

    @Test
    public void testStreetAddressWithSecondary() {
        BaseFaker faker = mock(BaseFaker.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        FakerContext context = mock(FakerContext.class);
        when(faker.fakeValuesService()).thenReturn(fakeValuesService);
        when(faker.context()).thenReturn(context);

        Address address = new Address(faker);

        when(fakeValuesService.resolve("address.street_address", address, context)).thenReturn("123 Main St");
        when(address.secondaryAddress()).thenReturn("Apt 4B");

        String result = address.streetAddress(true);
        assertEquals("123 Main St Apt 4B", result);
    }
}
