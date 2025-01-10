
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DomainValidator_getInstanceTest {
    private DomainValidator validator;

    @BeforeEach
    public void setUp() {
        validator = DomainValidator.getInstance(false);
    }

    @Test
    public void testGetInstanceWithLocalAllowed() {
        DomainValidator localValidator = DomainValidator.getInstance(true);
        assertNotNull(localValidator);
        assertTrue(localValidator.isAllowLocal());
    }

    @Test
    public void testGetInstanceWithLocalNotAllowed() {
        DomainValidator nonLocalValidator = DomainValidator.getInstance(false);
        assertNotNull(nonLocalValidator);
        assertFalse(nonLocalValidator.isAllowLocal());
    }

    @Test
    public void testGetInstanceWithCustomTLDs() {
        DomainValidator.Item item = new DomainValidator.Item(DomainValidator.ArrayType.GENERIC_PLUS, "custom");
        DomainValidator customValidator = DomainValidator.getInstance(false, List.of(item));
        assertNotNull(customValidator);
        assertTrue(customValidator.isValidTld("custom"));
    }

    @Test
    public void testGetInstanceWithInvalidCustomTLDs() {
        DomainValidator.Item item = new DomainValidator.Item(DomainValidator.ArrayType.GENERIC_PLUS, "invalid");
        DomainValidator customValidator = DomainValidator.getInstance(false, List.of(item));
        assertNotNull(customValidator);
        assertFalse(customValidator.isValidTld("invalid"));
    }

    @Test
    public void testGetInstanceWithLocalAndCustomTLDs() {
        DomainValidator.Item item = new DomainValidator.Item(DomainValidator.ArrayType.GENERIC_PLUS, "localcustom");
        DomainValidator localCustomValidator = DomainValidator.getInstance(true, List.of(item));
        assertNotNull(localCustomValidator);
        assertTrue(localCustomValidator.isAllowLocal());
        assertTrue(localCustomValidator.isValidTld("localcustom"));
    }
}
