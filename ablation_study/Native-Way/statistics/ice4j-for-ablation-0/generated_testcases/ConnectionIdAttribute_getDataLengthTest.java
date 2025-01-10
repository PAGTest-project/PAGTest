
package org.ice4j.attribute;

import org.ice4j.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ConnectionIdAttribute_getDataLengthTest {
    private ConnectionIdAttribute connectionIdAttribute;

    @BeforeEach
    public void setUp() throws Exception {
        connectionIdAttribute = new ConnectionIdAttribute();
    }

    @Test
    public void testGetDataLength() {
        char expectedReturn = 4;
        char actualReturn = connectionIdAttribute.getDataLength();
        assertEquals(expectedReturn, actualReturn, "Data length is not 4");
    }
}
