
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.InputStream;
import java.util.Map;

public class ValidatorResources_addConstantTest {

    private ValidatorResources resources;

    @BeforeEach
    protected void setUp() throws Exception {
        try (InputStream in = this.getClass().getResourceAsStream("/ValidatorResourcesTest-config.xml")) {
            if (in == null) {
                // Provide a default configuration or mock data if the resource is not found
                resources = new ValidatorResources();
            } else {
                resources = new ValidatorResources(in);
            }
        }
    }

    @Test
    public void testAddConstant() {
        // Given
        String constantName = "TEST_CONSTANT";
        String constantValue = "testValue";

        // When
        resources.addConstant(constantName, constantValue);

        // Then
        Map<String, String> constants = resources.getConstants();
        assertNotNull(constants);
        assertEquals(constantValue, constants.get(constantName));
    }

    @Test
    public void testAddConstantWithDebugLogging() {
        // Given
        String constantName = "DEBUG_CONSTANT";
        String constantValue = "debugValue";

        // When
        resources.addConstant(constantName, constantValue);

        // Then
        Map<String, String> constants = resources.getConstants();
        assertNotNull(constants);
        assertEquals(constantValue, constants.get(constantName));
    }
}
