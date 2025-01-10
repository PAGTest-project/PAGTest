
package org.ice4j.attribute;

import org.ice4j.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ConnectionIdAttribute_getNameTest {
    private ConnectionIdAttribute connectionIdAttribute;
    private MsgFixture msgFixture;

    @BeforeEach
    public void setUp() throws Exception {
        this.connectionIdAttribute = new ConnectionIdAttribute();
        this.msgFixture = new MsgFixture();
    }

    @Test
    public void testGetName() {
        String expectedName = "CONNECTION-ID";
        String actualName = connectionIdAttribute.getName();
        assertEquals(expectedName, actualName, "ConnectionIdAttribute.getName() did not return the expected name");
    }
}
