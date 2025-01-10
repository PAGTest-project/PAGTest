
package org.ice4j.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangeRequestAttribute_setChangeIpFlagTest {
    private ChangeRequestAttribute changeRequestAttribute;

    @BeforeEach
    public void setUp() {
        changeRequestAttribute = new ChangeRequestAttribute();
    }

    @Test
    public void testSetChangeIpFlag() {
        // Given
        boolean changeIP = true;

        // When
        changeRequestAttribute.setChangeIpFlag(changeIP);

        // Then
        assertTrue(changeRequestAttribute.getChangeIpFlag(), "The changeIpFlag should be set to true.");
    }

    @Test
    public void testSetChangeIpFlagFalse() {
        // Given
        boolean changeIP = false;

        // When
        changeRequestAttribute.setChangeIpFlag(changeIP);

        // Then
        assertFalse(changeRequestAttribute.getChangeIpFlag(), "The changeIpFlag should be set to false.");
    }
}
