
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
    public void testGetResultValueMap_WithNonBooleanResults() {
        // Given
        Field field1 = new Field();
        field1.setKey("field1");
        validatorResults.add(field1, "action1", true, "result1");

        Field field2 = new Field();
        field2.setKey("field2");
        validatorResults.add(field2, "action2", true, 123);

        // When
        Map<String, Object> resultValueMap = validatorResults.getResultValueMap();

        // Then
        assertEquals(2, resultValueMap.size());
        assertEquals("result1", resultValueMap.get("field1"));
        assertEquals(123, resultValueMap.get("field2"));
    }

    @Test
    public void testGetResultValueMap_WithBooleanResults() {
        // Given
        Field field1 = new Field();
        field1.setKey("field1");
        validatorResults.add(field1, "action1", true, true);

        Field field2 = new Field();
        field2.setKey("field2");
        validatorResults.add(field2, "action2", true, false);

        // When
        Map<String, Object> resultValueMap = validatorResults.getResultValueMap();

        // Then
        assertTrue(resultValueMap.isEmpty());
    }

    @Test
    public void testGetResultValueMap_WithMixedResults() {
        // Given
        Field field1 = new Field();
        field1.setKey("field1");
        validatorResults.add(field1, "action1", true, "result1");
        validatorResults.add(field1, "action2", true, true);

        Field field2 = new Field();
        field2.setKey("field2");
        validatorResults.add(field2, "action3", true, 123);
        validatorResults.add(field2, "action4", true, false);

        // When
        Map<String, Object> resultValueMap = validatorResults.getResultValueMap();

        // Then
        assertEquals(2, resultValueMap.size());
        assertEquals("result1", resultValueMap.get("field1"));
        assertEquals(123, resultValueMap.get("field2"));
    }

    @Test
    public void testGetResultValueMap_WithEmptyResults() {
        // When
        Map<String, Object> resultValueMap = validatorResults.getResultValueMap();

        // Then
        assertTrue(resultValueMap.isEmpty());
    }
}
