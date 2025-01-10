
package org.ice4j.attribute;

import org.ice4j.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ConnectionIdAttribute_getDataLengthTest {
    private ConnectionIdAttribute connectionIdAttribute;
    private MsgFixture msgFixture;

    @BeforeEach
    public void setUp() throws Exception {
        this.connectionIdAttribute = new ConnectionIdAttribute();
        this.msgFixture = new MsgFixture();
    }

    @Test
    public void testGetDataLength() {
        assertEquals(4, connectionIdAttribute.getDataLength(), "Data length should be 4");
    }

    @Test
    public void testEncode() {
        byte[] expectedReturn = msgFixture.connectionId;
        connectionIdAttribute.setConnectionIdValue(MsgFixture.CONNECTION_ID);
        byte[] actualReturn = connectionIdAttribute.encode();

        assertArrayEquals(expectedReturn, actualReturn,
            "ConnectionIdAttribute.encode() did not properly encode a sample attribute");
    }

    @Test
    public void testEquals() {
        ConnectionIdAttribute otherAttribute = new ConnectionIdAttribute();
        otherAttribute.setConnectionIdValue(MsgFixture.CONNECTION_ID);
        connectionIdAttribute.setConnectionIdValue(MsgFixture.CONNECTION_ID); // Ensure both attributes have the same value

        assertTrue(connectionIdAttribute.equals(otherAttribute), "Attributes should be equal");
    }

    @Test
    public void testDecodeAttributeBody() throws StunException {
        byte[] attributeValue = msgFixture.connectionId;
        connectionIdAttribute.decodeAttributeBody(attributeValue, (char) 0, (char) 4);

        assertEquals(MsgFixture.CONNECTION_ID, connectionIdAttribute.getConnectionIdValue(),
            "Connection ID value should be decoded correctly");
    }
}
