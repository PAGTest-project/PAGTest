
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorResults_getResultValueMapTest {

    private ValidatorResults validatorResults;

    @BeforeEach
    public void setUp() {
        validatorResults = new ValidatorResults();
    }

    @Test
    public void testGetResultValueMapWithNonBooleanResult() {
        // Arrange
        Field field = new Field();
        field.setKey("testKey");
        validatorResults.add(field, "testAction", true, "testValue");

        // Act
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Assert
        assertEquals(1, resultMap.size());
        assertTrue(resultMap.containsKey("testKey"));
        assertEquals("testValue", resultMap.get("testKey"));
    }

    @Test
    public void testGetResultValueMapWithBooleanResult() {
        // Arrange
        Field field = new Field();
        field.setKey("testKey");
        validatorResults.add(field, "testAction", true, true);

        // Act
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Assert
        assertTrue(resultMap.isEmpty());
    }

    @Test
    public void testGetResultValueMapWithNullResult() {
        // Arrange
        Field field = new Field();
        field.setKey("testKey");
        validatorResults.add(field, "testAction", true, null);

        // Act
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Assert
        assertTrue(resultMap.isEmpty());
    }

    @Test
    public void testGetResultValueMapWithMultipleResults() {
        // Arrange
        Field field1 = new Field();
        field1.setKey("key1");
        validatorResults.add(field1, "action1", true, "value1");

        Field field2 = new Field();
        field2.setKey("key2");
        validatorResults.add(field2, "action2", true, true);

        Field field3 = new Field();
        field3.setKey("key3");
        validatorResults.add(field3, "action3", true, "value3");

        // Act
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Assert
        assertEquals(2, resultMap.size());
        assertTrue(resultMap.containsKey("key1"));
        assertEquals("value1", resultMap.get("key1"));
        assertTrue(resultMap.containsKey("key3"));
        assertEquals("value3", resultMap.get("key3"));
    }

    @Test
    public void testGetResultValueMapWithEmptyResults() {
        // Act
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Assert
        assertTrue(resultMap.isEmpty());
    }
}
