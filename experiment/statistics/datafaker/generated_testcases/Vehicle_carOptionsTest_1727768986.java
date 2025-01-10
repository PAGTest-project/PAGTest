
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Vehicle_carOptionsTest {

    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        vehicle = new Vehicle(faker);
    }

    @Test
    public void testCarOptions() {
        int min = 5;
        int max = 10;
        List<String> options = vehicle.carOptions(min, max);

        // Verify the size of the list is within the expected range
        assertTrue(options.size() >= min && options.size() <= max);

        // Verify that each element in the list is not null or empty
        for (String option : options) {
            assertTrue(option != null && !option.isEmpty());
        }
    }
}
