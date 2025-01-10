
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
        assertTrue(connectionIdAttribute.equals(connectionIdAttribute), "An object should be equal to itself");
    }

    @Test
    public void testEquals_DifferentClass() {
        Object obj = new Object();
        assertFalse(connectionIdAttribute.equals(obj), "An object of different class should not be equal");
    }

    @Test
    public void testEquals_DifferentConnectionIdValue() throws StunException {
        ConnectionIdAttribute other = new ConnectionIdAttribute();
        other.decodeAttributeBody(msgFixture.connectionId, (char) 0, ConnectionIdAttribute.DATA_LENGTH);
        connectionIdAttribute.setConnectionIdValue(MsgFixture.CONNECTION_ID + 1);
        assertFalse(connectionIdAttribute.equals(other), "Attributes with different connectionIdValue should not be equal");
    }

    @Test
    public void testEquals_SameConnectionIdValue() throws StunException {
        ConnectionIdAttribute other = new ConnectionIdAttribute();
        other.decodeAttributeBody(msgFixture.connectionId, (char) 0, ConnectionIdAttribute.DATA_LENGTH);
        connectionIdAttribute.decodeAttributeBody(msgFixture.connectionId, (char) 0, ConnectionIdAttribute.DATA_LENGTH);
        assertTrue(connectionIdAttribute.equals(other), "Attributes with the same connectionIdValue should be equal");
    }
}
