
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class ValidatorResults_getResultValueMapTest {

    private ValidatorResults validatorResults;

    @BeforeEach
    public void setUp() {
        validatorResults = new ValidatorResults();
    }

    @Test
    public void testGetResultValueMap_WithNonBooleanResult() {
        // Arrange
        Field field = new Field();
        field.setKey("testKey");
        validatorResults.add(field, "testAction", true, "testValue");

        // Act
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Assert
        assertEquals(1, resultMap.size());
        assertEquals("testValue", resultMap.get("testKey"));
    }

    @Test
    public void testGetResultValueMap_WithBooleanResult() {
        // Arrange
        Field field = new Field();
        field.setKey("testKey");
        validatorResults.add(field, "testAction", true);

        // Act
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Assert
        assertTrue(resultMap.isEmpty());
    }

    @Test
    public void testGetResultValueMap_WithNullResult() {
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
    public void testGetResultValueMap_WithMultipleResults() {
        // Arrange
        Field field1 = new Field();
        field1.setKey("key1");
        validatorResults.add(field1, "action1", true, "value1");

        Field field2 = new Field();
        field2.setKey("key2");
        validatorResults.add(field2, "action2", true, "value2");

        // Act
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Assert
        assertEquals(2, resultMap.size());
        assertEquals("value1", resultMap.get("key1"));
        assertEquals("value2", resultMap.get("key2"));
    }

    @Test
    public void testGetResultValueMap_WithEmptyResults() {
        // Act
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Assert
        assertTrue(resultMap.isEmpty());
    }
}
