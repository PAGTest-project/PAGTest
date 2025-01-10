
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
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
    public void testGetResultValueMap() {
        // Given
        Field field1 = new Field();
        field1.setKey("field1");
        Field field2 = new Field();
        field2.setKey("field2");

        validatorResults.add(field1, "action1", true, "result1");
        validatorResults.add(field2, "action2", true, 123);

        // When
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Then
        assertEquals(2, resultMap.size());
        assertEquals("result1", resultMap.get("field1"));
        assertEquals(123, resultMap.get("field2"));
    }

    @Test
    public void testGetResultValueMapWithBooleanResult() {
        // Given
        Field field1 = new Field();
        field1.setKey("field1");

        validatorResults.add(field1, "action1", true, true);

        // When
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Then
        assertTrue(resultMap.isEmpty());
    }

    @Test
    public void testGetResultValueMapWithNullResult() {
        // Given
        Field field1 = new Field();
        field1.setKey("field1");

        validatorResults.add(field1, "action1", true, null);

        // When
        Map<String, Object> resultMap = validatorResults.getResultValueMap();

        // Then
        assertTrue(resultMap.isEmpty());
    }
}
