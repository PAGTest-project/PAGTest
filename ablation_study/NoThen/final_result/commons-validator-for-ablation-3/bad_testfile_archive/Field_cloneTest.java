
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class Field_cloneTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    @Test
    public void testCloneWithArgs() {
        // Given
        field.addArg(createArg("key1", "name1", 0));
        field.addArg(createArg("key2", "name2", 1));

        // When
        Field clonedField = (Field) field.clone();

        // Then
        assertNotNull(clonedField);
        assertEquals(2, clonedField.getArgs("required").length);
        assertEquals("key1", clonedField.getArg("required", 0).getKey());
        assertEquals("key2", clonedField.getArg("required", 1).getKey());
    }

    @Test
    public void testCloneWithEmptyArgs() {
        // Given
        // No args added

        // When
        Field clonedField = (Field) field.clone();

        // Then
        assertNotNull(clonedField);
        assertEquals(0, clonedField.getArgs("required").length);
    }

    @Test
    public void testCloneWithNullArgs() {
        // Given
        field.addArg(null);

        // When
        Field clonedField = (Field) field.clone();

        // Then
        assertNotNull(clonedField);
        assertEquals(0, clonedField.getArgs("required").length);
    }

    @Test
    public void testCloneWithMixedArgs() {
        // Given
        field.addArg(createArg("key1", "name1", 0));
        field.addArg(null);
        field.addArg(createArg("key2", "name2", 1));

        // When
        Field clonedField = (Field) field.clone();

        // Then
        assertNotNull(clonedField);
        assertEquals(2, clonedField.getArgs("required").length);
        assertEquals("key1", clonedField.getArg("required", 0).getKey());
        assertEquals("key2", clonedField.getArg("required", 1).getKey());
    }

    private Arg createArg(String key, String name, int position) {
        Arg arg = new Arg();
        arg.setKey(key);
        arg.setName(name);
        arg.setPosition(position);
        return arg;
    }
}
