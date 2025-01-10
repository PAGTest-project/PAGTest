
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Field_setDependsTest {

    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    @Test
    public void testSetDepends_SingleDependency() {
        // Given
        String depends = "dependency1";

        // When
        field.setDepends(depends);

        // Then
        List<String> dependencyList = field.getDependencyList();
        assertEquals(1, dependencyList.size());
        assertTrue(dependencyList.contains("dependency1"));
    }

    @Test
    public void testSetDepends_MultipleDependencies() {
        // Given
        String depends = "dependency1, dependency2, dependency3";

        // When
        field.setDepends(depends);

        // Then
        List<String> dependencyList = field.getDependencyList();
        assertEquals(3, dependencyList.size());
        assertTrue(dependencyList.contains("dependency1"));
        assertTrue(dependencyList.contains("dependency2"));
        assertTrue(dependencyList.contains("dependency3"));
    }

    @Test
    public void testSetDepends_EmptyDependency() {
        // Given
        String depends = "";

        // When
        field.setDepends(depends);

        // Then
        List<String> dependencyList = field.getDependencyList();
        assertTrue(dependencyList.isEmpty());
    }

    @Test
    public void testSetDepends_NullDependency() {
        // Given
        String depends = null;

        // When
        field.setDepends(depends);

        // Then
        List<String> dependencyList = field.getDependencyList();
        assertTrue(dependencyList.isEmpty());
    }

    @Test
    public void testSetDepends_WhitespaceDependency() {
        // Given
        String depends = "   ";

        // When
        field.setDepends(depends);

        // Then
        List<String> dependencyList = field.getDependencyList();
        assertTrue(dependencyList.isEmpty());
    }
}
