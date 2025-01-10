
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vehicle_engineTest {
    private Vehicle vehicle;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                // Mock implementation for testing purposes
                return "mock_" + key;
            }

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation to satisfy the abstract method
            }
        };
        vehicle = new Vehicle(faker);
    }

    @Test
    public void testEngine() {
        String engineSize = faker.resolve("vehicle.engine_sizes");
        String cylinderEngine = faker.resolve("vehicle.cylinder_engine");
        String expectedEngine = engineSize + " " + cylinderEngine;

        assertEquals(expectedEngine, vehicle.engine());
    }
}
