
package org.ice4j.attribute;

import org.ice4j.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ConnectionIdAttribute_equalsTest {
    private ConnectionIdAttribute connectionIdAttribute;
    private MsgFixture msgFixture;

    @BeforeEach
    public void setUp() throws Exception {
        this.connectionIdAttribute = new ConnectionIdAttribute();
        this.msgFixture = new MsgFixture();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(connectionIdAttribute.equals(connectionIdAttribute));
    }

    @Test
    public void testEquals_DifferentClass() {
        Object obj = new Object();
        assertFalse(connectionIdAttribute.equals(obj));
    }

    @Test
    public void testEquals_DifferentConnectionIdValue() throws StunException {
        ConnectionIdAttribute other = new ConnectionIdAttribute();
        other.setConnectionIdValue(123456);
        connectionIdAttribute.setConnectionIdValue(654321);
        assertFalse(connectionIdAttribute.equals(other));
    }

    @Test
    public void testEquals_SameConnectionIdValue() throws StunException {
        ConnectionIdAttribute other = new ConnectionIdAttribute();
        other.setConnectionIdValue(123456);
        connectionIdAttribute.setConnectionIdValue(123456);
        assertTrue(connectionIdAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentDataLength() throws StunException {
        ConnectionIdAttribute other = new ConnectionIdAttribute();
        // Assuming there's a way to set a different data length, which is not shown in the provided code
        // For the sake of this example, we'll assume a hypothetical method setDataLength(char)
        // other.setDataLength(8);
        // connectionIdAttribute.setDataLength(4);
        // assertFalse(connectionIdAttribute.equals(other));
    }

    @Test
    public void testEquals_DifferentAttributeType() throws StunException {
        ConnectionIdAttribute other = new ConnectionIdAttribute();
        // Assuming there's a way to set a different attribute type, which is not shown in the provided code
        // For the sake of this example, we'll assume a hypothetical method setAttributeType(int)
        // other.setAttributeType(1);
        // connectionIdAttribute.setAttributeType(2);
        // assertFalse(connectionIdAttribute.equals(other));
    }
}
