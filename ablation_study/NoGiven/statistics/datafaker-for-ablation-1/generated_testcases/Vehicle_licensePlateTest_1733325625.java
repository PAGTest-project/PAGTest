
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Locale;

public class Vehicle_licensePlateTest {
    private Vehicle vehicle;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                return ""; // Mock implementation
            }
        };
        vehicle = new Vehicle(faker);
    }

    @Test
    public void testLicensePlateWithValidStateAbbreviation() {
        String stateAbbreviation = "CA";
        String licensePlate = vehicle.licensePlate(stateAbbreviation);
        assertNotNull(licensePlate);
        assertTrue(licensePlate.equals(licensePlate.toUpperCase(Locale.ROOT)));
    }

    @Test
    public void testLicensePlateWithEmptyStateAbbreviation() {
        String stateAbbreviation = "";
        assertNull(vehicle.licensePlate(stateAbbreviation));
    }

    @Test
    public void testLicensePlateWithNullStateAbbreviation() {
        String stateAbbreviation = null;
        assertNull(vehicle.licensePlate(stateAbbreviation));
    }

    @Test
    public void testLicensePlateWithInvalidStateAbbreviation() {
        String stateAbbreviation = "XX";
        assertNull(vehicle.licensePlate(stateAbbreviation));
    }
}
