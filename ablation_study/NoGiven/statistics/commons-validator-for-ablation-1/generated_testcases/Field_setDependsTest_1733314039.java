
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Field_setDependsTest {

    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field();
    }

    @Test
    void testSetDepends_SingleDependency() {
        // Given
        String depends = "required";

        // When
        field.setDepends(depends);

        // Then
        assertEquals(depends, field.depends);
        assertEquals(1, field.dependencyList.size());
        assertTrue(field.dependencyList.contains("required"));
    }

    @Test
    void testSetDepends_MultipleDependencies() {
        // Given
        String depends = "required,minlength,maxlength";

        // When
        field.setDepends(depends);

        // Then
        assertEquals(depends, field.depends);
        assertEquals(3, field.dependencyList.size());
        assertTrue(field.dependencyList.contains("required"));
        assertTrue(field.dependencyList.contains("minlength"));
        assertTrue(field.dependencyList.contains("maxlength"));
    }

    @Test
    void testSetDepends_EmptyDependency() {
        // Given
        String depends = "";

        // When
        field.setDepends(depends);

        // Then
        assertEquals(depends, field.depends);
        assertTrue(field.dependencyList.isEmpty());
    }

    @Test
    void testSetDepends_NullDependency() {
        // Given
        String depends = null;

        // When
        field.setDepends(depends);

        // Then
        assertEquals(depends, field.depends);
        assertTrue(field.dependencyList.isEmpty());
    }

    @Test
    void testSetDepends_WithSpaces() {
        // Given
        String depends = " required , minlength , maxlength ";

        // When
        field.setDepends(depends);

        // Then
        assertEquals(depends, field.depends);
        assertEquals(3, field.dependencyList.size());
        assertTrue(field.dependencyList.contains("required"));
        assertTrue(field.dependencyList.contains("minlength"));
        assertTrue(field.dependencyList.contains("maxlength"));
    }

    @Test
    void testSetDepends_WithEmptyTokens() {
        // Given
        String depends = "required,,minlength,,";

        // When
        field.setDepends(depends);

        // Then
        assertEquals(depends, field.depends);
        assertEquals(2, field.dependencyList.size());
        assertTrue(field.dependencyList.contains("required"));
        assertTrue(field.dependencyList.contains("minlength"));
    }
}
