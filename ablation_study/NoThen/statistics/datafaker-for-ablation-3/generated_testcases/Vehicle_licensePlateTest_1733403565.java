
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vehicle_licensePlateTest {

    private Vehicle vehicle;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseProviders();
        vehicle = new Vehicle(faker);
    }

    @Test
    public void testLicensePlateWithValidStateAbbreviation() {
        String stateAbbreviation = "CA";
        String licensePlate = vehicle.licensePlate(stateAbbreviation);
        assertNotNull(licensePlate);
        assertTrue(licensePlate.matches("[A-Z0-9]+"));
    }

    @Test
    public void testLicensePlateWithEmptyStateAbbreviation() {
        String stateAbbreviation = "";
        String licensePlate = vehicle.licensePlate(stateAbbreviation);
        assertNull(licensePlate);
    }

    @Test
    public void testLicensePlateWithNullStateAbbreviation() {
        String stateAbbreviation = null;
        String licensePlate = vehicle.licensePlate(stateAbbreviation);
        assertNull(licensePlate);
    }

    @Test
    public void testLicensePlateWithInvalidStateAbbreviation() {
        String stateAbbreviation = "XYZ";
        String licensePlate = vehicle.licensePlate(stateAbbreviation);
        assertNull(licensePlate);
    }
}
