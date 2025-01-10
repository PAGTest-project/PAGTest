
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ChangeRequestAttribute_equalsTest {
    private ChangeRequestAttribute changeRequestAttribute;

    @BeforeEach
    public void setUp() {
        changeRequestAttribute = new ChangeRequestAttribute();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(changeRequestAttribute.equals(changeRequestAttribute));
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(changeRequestAttribute.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        ChangeRequestAttribute other = new ChangeRequestAttribute() {
            @Override
            public char getAttributeType() {
                return (char) (super.getAttributeType() + 1);
            }
        };
        assertFalse(changeRequestAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        ChangeRequestAttribute other = new ChangeRequestAttribute() {
            @Override
            public char getDataLength() {
                return (char) (super.getDataLength() + 1);
            }
        };
        assertFalse(changeRequestAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentChangeIpFlag() {
        ChangeRequestAttribute other = new ChangeRequestAttribute();
        other.setChangeIpFlag(true);
        assertFalse(changeRequestAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentChangePortFlag() {
        ChangeRequestAttribute other = new ChangeRequestAttribute();
        other.setChangePortFlag(true);
        assertFalse(changeRequestAttribute.equals(other));
    }

    @Test
    public void testEquals_AllAttributesSame() {
        ChangeRequestAttribute other = new ChangeRequestAttribute();
        assertTrue(changeRequestAttribute.equals(other));
    }
}
