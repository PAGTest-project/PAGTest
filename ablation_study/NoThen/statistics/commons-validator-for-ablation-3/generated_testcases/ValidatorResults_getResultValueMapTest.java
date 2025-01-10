
package org.apache.commons.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidatorResults_getResultValueMapTest {

    private ValidatorResults validatorResults;

    @BeforeEach
    public void setUp() {
        validatorResults = new ValidatorResults();
    }

    @Test
    public void testGetResultValueMapWithNonBooleanResult() {
        // Given
        Field field = new Field();
        field.setKey("fieldKey");
        validatorResults.add(field, "validatorName", true, "nonBooleanResult");

        // When
        Map<String, Object> resultValueMap = validatorResults.getResultValueMap();

        // Then
        assertNotNull(resultValueMap);
        assertEquals(1, resultValueMap.size());
        assertEquals("nonBooleanResult", resultValueMap.get("fieldKey"));
    }

    @Test
    public void testGetResultValueMapWithBooleanResult() {
        // Given
        Field field = new Field();
        field.setKey("fieldKey");
        validatorResults.add(field, "validatorName", true, true);

        // When
        Map<String, Object> resultValueMap = validatorResults.getResultValueMap();

        // Then
        assertNotNull(resultValueMap);
        assertTrue(resultValueMap.isEmpty());
    }

    @Test
    public void testGetResultValueMapWithMultipleResults() {
        // Given
        Field field1 = new Field();
        field1.setKey("fieldKey1");
        validatorResults.add(field1, "validatorName1", true, "result1");

        Field field2 = new Field();
        field2.setKey("fieldKey2");
        validatorResults.add(field2, "validatorName2", true, true);

        Field field3 = new Field();
        field3.setKey("fieldKey3");
        validatorResults.add(field3, "validatorName3", true, "result3");

        // When
        Map<String, Object> resultValueMap = validatorResults.getResultValueMap();

        // Then
        assertNotNull(resultValueMap);
        assertEquals(2, resultValueMap.size());
        assertEquals("result1", resultValueMap.get("fieldKey1"));
        assertEquals("result3", resultValueMap.get("fieldKey3"));
    }

    @Test
    public void testGetResultValueMapWithNullResult() {
        // Given
        Field field = new Field();
        field.setKey("fieldKey");
        validatorResults.add(field, "validatorName", true, null);

        // When
        Map<String, Object> resultValueMap = validatorResults.getResultValueMap();

        // Then
        assertNotNull(resultValueMap);
        assertTrue(resultValueMap.isEmpty());
    }

    @Test
    public void testGetResultValueMapWithEmptyResults() {
        // When
        Map<String, Object> resultValueMap = validatorResults.getResultValueMap();

        // Then
        assertNotNull(resultValueMap);
        assertTrue(resultValueMap.isEmpty());
    }
}
