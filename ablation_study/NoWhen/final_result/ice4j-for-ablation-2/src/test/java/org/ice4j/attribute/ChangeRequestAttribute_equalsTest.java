
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ChangeRequestAttribute_equalsTest {
    private ChangeRequestAttribute changeRequestAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        changeRequestAttribute = new ChangeRequestAttribute();
    }

    @Test
    public void testEquals_SameObject() {
        assertEquals(changeRequestAttribute, changeRequestAttribute);
    }

    @Test
    public void testEquals_DifferentClass() {
        Object obj = new Object();
        assertNotEquals(changeRequestAttribute, obj);
    }

    @Test
    public void testEquals_DifferentChangeIpFlag() {
        ChangeRequestAttribute target = new ChangeRequestAttribute();
        changeRequestAttribute.setChangeIpFlag(true);
        target.setChangeIpFlag(false);
        assertNotEquals(changeRequestAttribute, target);
    }

    @Test
    public void testEquals_DifferentChangePortFlag() {
        ChangeRequestAttribute target = new ChangeRequestAttribute();
        changeRequestAttribute.setChangePortFlag(true);
        target.setChangePortFlag(false);
        assertNotEquals(changeRequestAttribute, target);
    }

    @Test
    public void testEquals_AllAttributesSame() {
        ChangeRequestAttribute target = new ChangeRequestAttribute();
        changeRequestAttribute.setChangeIpFlag(true);
        changeRequestAttribute.setChangePortFlag(true);
        target.setChangeIpFlag(true);
        target.setChangePortFlag(true);
        assertEquals(changeRequestAttribute, target);
    }
}
