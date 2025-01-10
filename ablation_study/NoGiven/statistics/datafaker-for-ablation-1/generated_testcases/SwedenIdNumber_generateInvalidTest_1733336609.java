
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class SwedenIdNumber_generateInvalidTest {

    private SwedenIdNumber swedenIdNumber;
    private BaseProviders baseProviders;

    @BeforeEach
    public void setUp() {
        swedenIdNumber = new SwedenIdNumber();
        baseProviders = mock(BaseProviders.class);
    }

    @Test
    public void testGenerateInvalid_InvalidSsn() {
        // Mock the behavior of isValidSwedishSsn to return false for a specific invalid SSN
        when(baseProviders.numerify(anyString())).thenReturn("811228-9873");

        String invalidSsn = swedenIdNumber.generateInvalid(baseProviders);

        assertFalse(SwedenIdNumber.isValidSwedishSsn(invalidSsn));
    }

    @Test
    public void testGenerateInvalid_InvalidSsnWithInvalidPattern() {
        // Mock the behavior of isValidSwedishSsn to return false for another invalid SSN
        when(baseProviders.numerify(anyString())).thenReturn("foo228-9873");

        String invalidSsn = swedenIdNumber.generateInvalid(baseProviders);

        assertFalse(SwedenIdNumber.isValidSwedishSsn(invalidSsn));
    }

    @Test
    public void testGenerateInvalid_InvalidSsnWithInvalidChecksum() {
        // Mock the behavior of isValidSwedishSsn to return false for an SSN with invalid checksum
        when(baseProviders.numerify(anyString())).thenReturn("811228-9875");

        String invalidSsn = swedenIdNumber.generateInvalid(baseProviders);

        assertFalse(SwedenIdNumber.isValidSwedishSsn(invalidSsn));
    }
}
