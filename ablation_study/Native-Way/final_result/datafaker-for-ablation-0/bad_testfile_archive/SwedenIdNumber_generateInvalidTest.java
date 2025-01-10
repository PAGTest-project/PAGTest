
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class SwedenIdNumber_generateInvalidTest {

    private SwedenIdNumber swedenIdNumber;
    private BaseProviders mockBaseProviders;

    @BeforeEach
    public void setUp() {
        swedenIdNumber = new SwedenIdNumber();
        mockBaseProviders = mock(BaseProviders.class);
    }

    @Test
    public void testGenerateInvalid_InvalidSsn() {
        // Mock the behavior of isValidSwedishSsn to return false for a specific invalid SSN
        when(mockBaseProviders.numerify(anyString())).thenReturn("121212-1212");
        when(mockBaseProviders.options()).thenReturn(new BaseProviders.Options() {
            @Override
            public String option(String[] options) {
                return options[0];
            }
        });

        String invalidSsn = swedenIdNumber.generateInvalid(mockBaseProviders);
        assertFalse(SwedenIdNumber.isValidSwedishSsn(invalidSsn));
    }

    @Test
    public void testGenerateInvalid_ValidSsn() {
        // Mock the behavior of isValidSwedishSsn to return true for a specific valid SSN
        when(mockBaseProviders.numerify(anyString())).thenReturn("670919-9530");
        when(mockBaseProviders.options()).thenReturn(new BaseProviders.Options() {
            @Override
            public String option(String[] options) {
                return options[0];
            }
        });

        String invalidSsn = swedenIdNumber.generateInvalid(mockBaseProviders);
        assertFalse(SwedenIdNumber.isValidSwedishSsn(invalidSsn));
    }
}
