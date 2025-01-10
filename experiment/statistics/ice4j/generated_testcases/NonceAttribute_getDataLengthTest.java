
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NonceAttribute_getDataLengthTest {
    private NonceAttribute nonceAttribute;

    @BeforeEach
    public void setUp() {
        nonceAttribute = new NonceAttribute();
    }

    @Test
    public void testGetDataLength() {
        byte[] nonce = "testNonce".getBytes();
        nonceAttribute.setNonce(nonce);

        char expectedLength = (char) nonce.length;
        char actualLength = nonceAttribute.getDataLength();

        assertEquals(expectedLength, actualLength, "Incorrect nonce length returned");
    }
}
