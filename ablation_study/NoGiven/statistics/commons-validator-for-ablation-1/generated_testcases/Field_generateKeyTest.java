
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Field_generateKeyTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    @Test
    public void testGenerateKeyIndexed() {
        field.setIndexedListProperty("indexedListProperty");
        field.setProperty("property");
        field.generateKey();
        assertEquals("indexedListProperty[]." + "property", field.getKey());
    }

    @Test
    public void testGenerateKeyNotIndexed() {
        field.setProperty("property");
        field.generateKey();
        assertEquals("property", field.getKey());
    }
}
