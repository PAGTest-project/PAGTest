
package org.apache.commons.validator.routines;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DomainValidator_updateTLDOverrideTest {
    private DomainValidator domainValidator;

    @Before
    public void setUp() {
        domainValidator = DomainValidator.getInstance(false);
    }

    @Test
    public void testUpdateTLDOverrideValid() {
        String[] tlds = {"test1", "test2"};
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, tlds);

        assertTrue(domainValidator.isValidGenericTld("test1"));
        assertTrue(domainValidator.isValidGenericTld("test2"));
    }

    @Test
    public void testUpdateTLDOverrideInvalid() {
        String[] tlds = {"invalid1", "invalid2"};
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, tlds);

        assertFalse(domainValidator.isValidGenericTld("invalid1"));
        assertFalse(domainValidator.isValidGenericTld("invalid2"));
    }

    @Test(expected = IllegalStateException.class)
    public void testUpdateTLDOverrideAfterGetInstance() {
        DomainValidator.getInstance(false);
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"test"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTLDOverrideInvalidTable() {
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_RO, new String[]{"test"});
    }

    @Test
    public void testUpdateTLDOverrideCountryCode() {
        String[] tlds = {"cc1", "cc2"};
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, tlds);

        assertTrue(domainValidator.isValidCountryCodeTld("cc1"));
        assertTrue(domainValidator.isValidCountryCodeTld("cc2"));
    }

    @Test
    public void testUpdateTLDOverrideLocal() {
        String[] tlds = {"local1", "local2"};
        DomainValidator.updateTLDOverride(ArrayType.LOCAL_PLUS, tlds);

        assertTrue(domainValidator.isValidLocalTld("local1"));
        assertTrue(domainValidator.isValidLocalTld("local2"));
    }

    @Test
    public void testUpdateTLDOverrideMixedCase() {
        String[] tlds = {"MixedCase"};
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, tlds);

        assertTrue(domainValidator.isValidGenericTld("mixedcase"));
    }
}
