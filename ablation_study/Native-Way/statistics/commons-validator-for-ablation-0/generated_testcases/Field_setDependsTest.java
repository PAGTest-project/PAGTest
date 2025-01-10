
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Field_setDependsTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    @Test
    public void testSetDependsSingleDependency() {
        field.setDepends("required");
        List<String> dependencyList = field.getDependencyList();
        assertEquals(1, dependencyList.size());
        assertTrue(dependencyList.contains("required"));
    }

    @Test
    public void testSetDependsMultipleDependencies() {
        field.setDepends("required,minlength,maxlength");
        List<String> dependencyList = field.getDependencyList();
        assertEquals(3, dependencyList.size());
        assertTrue(dependencyList.contains("required"));
        assertTrue(dependencyList.contains("minlength"));
        assertTrue(dependencyList.contains("maxlength"));
    }

    @Test
    public void testSetDependsEmptyString() {
        field.setDepends("");
        List<String> dependencyList = field.getDependencyList();
        assertEquals(0, dependencyList.size());
    }

    @Test
    public void testSetDependsNullString() {
        field.setDepends(null);
        List<String> dependencyList = field.getDependencyList();
        assertEquals(0, dependencyList.size());
    }

    @Test
    public void testSetDependsWithSpaces() {
        field.setDepends(" required , minlength , maxlength ");
        List<String> dependencyList = field.getDependencyList();
        assertEquals(3, dependencyList.size());
        assertTrue(dependencyList.contains("required"));
        assertTrue(dependencyList.contains("minlength"));
        assertTrue(dependencyList.contains("maxlength"));
    }
}
