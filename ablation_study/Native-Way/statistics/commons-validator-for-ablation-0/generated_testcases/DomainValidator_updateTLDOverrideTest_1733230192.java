
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainValidator_updateTLDOverrideTest {

    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance();
    }

    @Test
    public void testUpdateTLDOverride_CountryCodeMinus() {
        String[] tlds = {"com", "net", "org"};
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.COUNTRY_CODE_MINUS, tlds);
        assertFalse(validator.isValidCountryCodeTld("com"));
        assertFalse(validator.isValidCountryCodeTld("net"));
        assertFalse(validator.isValidCountryCodeTld("org"));
    }

    @Test
    public void testUpdateTLDOverride_CountryCodePlus() {
        String[] tlds = {"com", "net", "org"};
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.COUNTRY_CODE_PLUS, tlds);
        assertTrue(validator.isValidCountryCodeTld("com"));
        assertTrue(validator.isValidCountryCodeTld("net"));
        assertTrue(validator.isValidCountryCodeTld("org"));
    }

    @Test
    public void testUpdateTLDOverride_GenericMinus() {
        String[] tlds = {"com", "net", "org"};
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.GENERIC_MINUS, tlds);
        assertFalse(validator.isValidGenericTld("com"));
        assertFalse(validator.isValidGenericTld("net"));
        assertFalse(validator.isValidGenericTld("org"));
    }

    @Test
    public void testUpdateTLDOverride_GenericPlus() {
        String[] tlds = {"com", "net", "org"};
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.GENERIC_PLUS, tlds);
        assertTrue(validator.isValidGenericTld("com"));
        assertTrue(validator.isValidGenericTld("net"));
        assertTrue(validator.isValidGenericTld("org"));
    }

    @Test
    public void testUpdateTLDOverride_LocalMinus() {
        String[] tlds = {"localhost", "localdomain"};
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.LOCAL_MINUS, tlds);
        assertFalse(validator.isValidLocalTld("localhost"));
        assertFalse(validator.isValidLocalTld("localdomain"));
    }

    @Test
    public void testUpdateTLDOverride_LocalPlus() {
        String[] tlds = {"localhost", "localdomain"};
        DomainValidator.updateTLDOverride(DomainValidator.ArrayType.LOCAL_PLUS, tlds);
        assertTrue(validator.isValidLocalTld("localhost"));
        assertTrue(validator.isValidLocalTld("localdomain"));
    }

    @Test
    public void testUpdateTLDOverride_InvalidTable() {
        String[] tlds = {"com", "net", "org"};
        assertThrows(IllegalArgumentException.class, () -> {
            DomainValidator.updateTLDOverride(DomainValidator.ArrayType.GENERIC_RO, tlds);
        });
    }

    @Test
    public void testUpdateTLDOverride_InUse() {
        DomainValidator.getInstance(); // Ensure inUse is true
        assertThrows(IllegalStateException.class, () -> {
            DomainValidator.updateTLDOverride(DomainValidator.ArrayType.COUNTRY_CODE_MINUS, new String[]{"com"});
        });
    }
}
