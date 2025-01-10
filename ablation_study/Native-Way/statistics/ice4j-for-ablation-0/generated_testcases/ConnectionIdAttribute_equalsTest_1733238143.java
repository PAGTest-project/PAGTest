
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
    public void testEquals_DifferentAttributeType() {
        ConnectionIdAttribute att = new ConnectionIdAttribute();
        att.setAttributeType(12345); // Assuming setAttributeType method exists
        assertFalse(connectionIdAttribute.equals(att), "Attributes with different types should not be equal");
    }

    @Test
    public void testEquals_DifferentDataLength() {
        ConnectionIdAttribute att = new ConnectionIdAttribute();
        att.setDataLength((char) 8); // Assuming setDataLength method exists
        assertFalse(connectionIdAttribute.equals(att), "Attributes with different data lengths should not be equal");
    }

    @Test
    public void testEquals_DifferentConnectionIdValue() {
        ConnectionIdAttribute att = new ConnectionIdAttribute();
        att.setConnectionIdValue(123456789);
        assertFalse(connectionIdAttribute.equals(att), "Attributes with different connection ID values should not be equal");
    }

    @Test
    public void testEquals_EqualAttributes() {
        ConnectionIdAttribute att = new ConnectionIdAttribute();
        att.setConnectionIdValue(123456789);
        connectionIdAttribute.setConnectionIdValue(123456789);
        assertTrue(connectionIdAttribute.equals(att), "Attributes with the same values should be equal");
    }
}
