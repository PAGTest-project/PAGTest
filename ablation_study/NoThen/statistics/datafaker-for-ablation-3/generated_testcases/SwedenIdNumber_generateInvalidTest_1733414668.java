
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SwedenIdNumber_generateInvalidTest {

    private SwedenIdNumber swedenIdNumber;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        swedenIdNumber = new SwedenIdNumber();
        faker = Mockito.mock(BaseProviders.class);
    }

    @Test
    public void testGenerateInvalid_InvalidSsn() {
        String invalidSsn = swedenIdNumber.generateInvalid(faker);
        assertFalse(SwedenIdNumber.isValidSwedishSsn(invalidSsn));
    }

    @Test
    public void testGenerateInvalid_DifferentInvalidSsns() {
        String invalidSsn1 = swedenIdNumber.generateInvalid(faker);
        String invalidSsn2 = swedenIdNumber.generateInvalid(faker);
        assertNotEquals(invalidSsn1, invalidSsn2);
    }
}
