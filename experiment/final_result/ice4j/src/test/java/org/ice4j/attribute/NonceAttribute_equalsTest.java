
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class NonceAttribute_equalsTest {
    private NonceAttribute nonceAttribute1;
    private NonceAttribute nonceAttribute2;
    private byte[] nonceValue1 = "nonce1".getBytes();
    private byte[] nonceValue2 = "nonce2".getBytes();

    @BeforeEach
    public void setUp() throws Exception {
        nonceAttribute1 = new NonceAttribute();
        nonceAttribute1.setNonce(nonceValue1);

        nonceAttribute2 = new NonceAttribute();
        nonceAttribute2.setNonce(nonceValue2);
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(nonceAttribute1.equals(nonceAttribute1));
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(nonceAttribute1.equals("not a NonceAttribute"));
    }

    @Test
    public void testEqualsDifferentNonce() {
        assertFalse(nonceAttribute1.equals(nonceAttribute2));
    }

    @Test
    public void testEqualsSameNonce() {
        nonceAttribute2.setNonce(nonceValue1);
        assertTrue(nonceAttribute1.equals(nonceAttribute2));
    }
}
