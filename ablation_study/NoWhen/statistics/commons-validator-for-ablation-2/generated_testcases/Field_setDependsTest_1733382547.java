
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Field_setDependsTest {

    @Test
    void testSetDepends_SingleDependency() {
        Field field = new Field();
        field.setDepends("required");

        List<String> dependencyList = field.dependencyList;
        assertEquals(1, dependencyList.size());
        assertTrue(dependencyList.contains("required"));
    }

    @Test
    void testSetDepends_MultipleDependencies() {
        Field field = new Field();
        field.setDepends("required,minlength,maxlength");

        List<String> dependencyList = field.dependencyList;
        assertEquals(3, dependencyList.size());
        assertTrue(dependencyList.contains("required"));
        assertTrue(dependencyList.contains("minlength"));
        assertTrue(dependencyList.contains("maxlength"));
    }

    @Test
    void testSetDepends_EmptyDependency() {
        Field field = new Field();
        field.setDepends("");

        List<String> dependencyList = field.dependencyList;
        assertTrue(dependencyList.isEmpty());
    }

    @Test
    void testSetDepends_NullDependency() {
        Field field = new Field();
        field.setDepends(null);

        List<String> dependencyList = field.dependencyList;
        assertTrue(dependencyList.isEmpty());
    }

    @Test
    void testSetDepends_WhitespaceDependency() {
        Field field = new Field();
        field.setDepends("  ");

        List<String> dependencyList = field.dependencyList;
        assertTrue(dependencyList.isEmpty());
    }
}
