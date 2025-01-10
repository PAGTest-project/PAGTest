
package org.ice4j.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangeRequestAttribute_getChangePortFlagTest {
    private ChangeRequestAttribute changeRequestAttribute;

    @BeforeEach
    public void setUp() {
        changeRequestAttribute = new ChangeRequestAttribute();
    }

    @Test
    public void testGetChangePortFlag() {
        // Given
        changeRequestAttribute.setChangePortFlag(true);

        // When
        boolean result = changeRequestAttribute.getChangePortFlag();

        // Then
        assertTrue(result, "getChangePortFlag() should return true when set to true");

        // Given
        changeRequestAttribute.setChangePortFlag(false);

        // When
        result = changeRequestAttribute.getChangePortFlag();

        // Then
        assertFalse(result, "getChangePortFlag() should return false when set to false");
    }
}
