
package org.ice4j.attribute;

import org.ice4j.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChangeRequestAttribute_equalsTest {
    private ChangeRequestAttribute changeRequestAttribute;
    private MsgFixture binMessagesFixture;

    @BeforeEach
    public void setUp() throws Exception {
        changeRequestAttribute = new ChangeRequestAttribute();
        binMessagesFixture = new MsgFixture();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(changeRequestAttribute.equals(changeRequestAttribute));
    }

    @Test
    public void testEquals_DifferentClass() {
        assertFalse(changeRequestAttribute.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        ChangeRequestAttribute other = new ChangeRequestAttribute();
        other.setChangeIpFlag(true);
        other.setChangePortFlag(true);
        assertFalse(changeRequestAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentDataLength() {
        ChangeRequestAttribute other = new ChangeRequestAttribute();
        other.setChangeIpFlag(true);
        other.setChangePortFlag(true);
        assertFalse(changeRequestAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentChangeIpFlag() {
        ChangeRequestAttribute other = new ChangeRequestAttribute();
        other.setChangeIpFlag(true);
        other.setChangePortFlag(false);
        assertFalse(changeRequestAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentChangePortFlag() {
        ChangeRequestAttribute other = new ChangeRequestAttribute();
        other.setChangeIpFlag(false);
        other.setChangePortFlag(true);
        assertFalse(changeRequestAttribute.equals(other));
    }

    @Test
    public void testEquals_AllFieldsSame() {
        ChangeRequestAttribute other = new ChangeRequestAttribute();
        other.setChangeIpFlag(false);
        other.setChangePortFlag(false);
        assertTrue(changeRequestAttribute.equals(other));
    }
}
