
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
        assertEquals(4, connectionIdAttribute.getDataLength(), "Data length should be 4 bytes.");
    }

    @Test
    public void testEquals() {
        ConnectionIdAttribute other = new ConnectionIdAttribute();
        assertTrue(connectionIdAttribute.equals(other), "Attributes should be equal.");
    }

    @Test
    public void testEncode() {
        byte[] encoded = connectionIdAttribute.encode();
        assertEquals(8, encoded.length, "Encoded length should be 8 bytes.");
    }

    @Test
    public void testDecodeAttributeBody() throws StunException {
        byte[] attributeValue = msgFixture.connectionId;
        char offset = Attribute.HEADER_LENGTH;
        char length = (char) (attributeValue.length - offset);

        connectionIdAttribute.decodeAttributeBody(attributeValue, offset, length);

        assertEquals(MsgFixture.CONNECTION_ID, connectionIdAttribute.getConnectionIdValue(), "ConnectionIdAttribute.decode() did not properly decode the connection id field.");
    }
}
