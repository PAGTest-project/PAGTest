
package org.ice4j.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangeRequestAttribute_setChangePortFlagTest {
    private ChangeRequestAttribute changeRequestAttribute;

    @BeforeEach
    public void setUp() {
        changeRequestAttribute = new ChangeRequestAttribute();
    }

    @Test
    public void testSetChangePortFlag() {
        // Given
        boolean changePort = true;

        // When
        changeRequestAttribute.setChangePortFlag(changePort);

        // Then
        assertTrue(changeRequestAttribute.getChangePortFlag(), "The changePortFlag should be set to true");

        // Given
        changePort = false;

        // When
        changeRequestAttribute.setChangePortFlag(changePort);

        // Then
        assertFalse(changeRequestAttribute.getChangePortFlag(), "The changePortFlag should be set to false");
    }
}
