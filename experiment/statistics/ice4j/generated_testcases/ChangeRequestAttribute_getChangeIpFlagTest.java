
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ChangeRequestAttribute_getChangeIpFlagTest {
    private ChangeRequestAttribute changeRequestAttribute;

    @BeforeEach
    public void setUp() {
        changeRequestAttribute = new ChangeRequestAttribute();
    }

    @Test
    public void testGetChangeIpFlag() {
        // Given
        changeRequestAttribute.setChangeIpFlag(true);

        // When
        boolean result = changeRequestAttribute.getChangeIpFlag();

        // Then
        assertTrue(result);
    }

    @Test
    public void testGetChangeIpFlagFalse() {
        // Given
        changeRequestAttribute.setChangeIpFlag(false);

        // When
        boolean result = changeRequestAttribute.getChangeIpFlag();

        // Then
        assertFalse(result);
    }
}
