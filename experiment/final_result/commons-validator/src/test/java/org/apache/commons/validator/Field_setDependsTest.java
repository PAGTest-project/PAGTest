
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Field_setDependsTest {

    @Test
    public void testSetDepends() {
        Field field = new Field();
        field.setDepends("dependency1, dependency2, dependency3");

        List<String> dependencyList = field.getDependencyList();
        assertEquals(3, dependencyList.size());
        assertTrue(dependencyList.contains("dependency1"));
        assertTrue(dependencyList.contains("dependency2"));
        assertTrue(dependencyList.contains("dependency3"));
    }

    @Test
    public void testSetDependsWithEmptyTokens() {
        Field field = new Field();
        field.setDepends("dependency1,, dependency3");

        List<String> dependencyList = field.getDependencyList();
        assertEquals(2, dependencyList.size());
        assertTrue(dependencyList.contains("dependency1"));
        assertTrue(dependencyList.contains("dependency3"));
    }

    @Test
    public void testSetDependsWithNullAndEmpty() {
        Field field = new Field();
        field.setDepends("dependency1, , dependency3");

        List<String> dependencyList = field.getDependencyList();
        assertEquals(2, dependencyList.size());
        assertTrue(dependencyList.contains("dependency1"));
        assertTrue(dependencyList.contains("dependency3"));
    }
}
