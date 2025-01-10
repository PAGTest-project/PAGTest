
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Vehicle_carOptionsTest {
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        vehicle = new Vehicle(new BaseProviders());
    }

    @Test
    public void testCarOptionsWithMinMax() {
        int min = 5;
        int max = 10;
        List<String> options = vehicle.carOptions(min, max);
        assertTrue(options.size() >= min && options.size() <= max);
    }

    @Test
    public void testCarOptionsWithMinEqualToMax() {
        int min = 7;
        int max = 7;
        List<String> options = vehicle.carOptions(min, max);
        assertTrue(options.size() == min);
    }

    @Test
    public void testCarOptionsWithMinGreaterThanMax() {
        int min = 10;
        int max = 5;
        List<String> options = vehicle.carOptions(min, max);
        assertTrue(options.size() >= 0 && options.size() <= min);
    }
}
