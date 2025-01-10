
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SwedenIdNumber_generateInvalidTest {
    private SwedenIdNumber swedenIdNumber;
    private BaseProviders baseProviders;

    @BeforeEach
    public void setUp() {
        swedenIdNumber = new SwedenIdNumber();
        baseProviders = mock(BaseProviders.class);
    }

    @Test
    public void testGenerateInvalid() {
        // Mock the behavior of getPattern to return a specific pattern
        when(baseProviders.options().option(SwedenIdNumber.class.getDeclaredField("VALID_PATTERNS").get(null))).thenReturn("######-####");

        // Generate an invalid SSN
        String invalidSsn = swedenIdNumber.generateInvalid(baseProviders);

        // Assert that the generated SSN is invalid
        assertFalse(SwedenIdNumber.isValidSwedishSsn(invalidSsn));
    }
}
