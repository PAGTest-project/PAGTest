
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vehicle_engineTest {
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        vehicle = new Vehicle(faker);
    }

    @Test
    public void testEngine() {
        String engine = vehicle.engine();
        String[] parts = engine.split(" ");
        assertEquals(2, parts.length);
        assertEquals(vehicle.resolve("vehicle.engine_sizes"), parts[0]);
        assertEquals(vehicle.resolve("vehicle.cylinder_engine"), parts[1]);
    }
}
