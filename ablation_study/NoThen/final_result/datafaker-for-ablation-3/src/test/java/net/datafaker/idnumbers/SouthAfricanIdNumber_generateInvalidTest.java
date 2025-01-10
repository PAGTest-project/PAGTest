
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseFaker;
import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SouthAfricanIdNumber_generateInvalidTest {

    private SouthAfricanIdNumber southAfricanIdNumber;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        southAfricanIdNumber = new SouthAfricanIdNumber();
        faker = new BaseFaker(new Locale("en", "ZA"));
    }

    @Test
    public void testGenerateInvalidSsn() {
        String invalidSsn = southAfricanIdNumber.generateInvalid(faker);
        assertFalse(SouthAfricanIdNumber.isValidEnZASsn(invalidSsn));
    }

    @Test
    public void testGenerateValidSsn() {
        String validSsn = southAfricanIdNumber.generateValid(faker);
        assertTrue(SouthAfricanIdNumber.isValidEnZASsn(validSsn));
    }
}
