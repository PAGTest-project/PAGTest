
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Vehicle_engineTest {

    private Vehicle vehicle;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseProviders.class);
        when(faker.fakeValuesService()).thenReturn(mock(net.datafaker.service.FakeValuesService.class));
        vehicle = new Vehicle(faker);
    }

    @Test
    public void testEngine() {
        when(faker.resolve("vehicle.engine_sizes")).thenReturn("2.0L");
        when(faker.resolve("vehicle.cylinder_engine")).thenReturn("Inline 4");

        String expected = "2.0L Inline 4";
        String actual = vehicle.engine();

        assertEquals(expected, actual);
    }
}
