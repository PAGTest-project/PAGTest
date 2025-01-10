
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.Arrays;

public class NonceAttribute_equalsTest {

    private NonceAttribute nonceAttribute;
    private byte[] nonceValue;

    @BeforeEach
    public void setUp() {
        nonceAttribute = new NonceAttribute();
        nonceValue = "testNonce".getBytes();
        nonceAttribute.setNonce(nonceValue);
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(nonceAttribute.equals(nonceAttribute), "An object should be equal to itself");
    }

    @Test
    public void testEqualsDifferentType() {
        Object obj = new Object();
        assertFalse(nonceAttribute.equals(obj), "An object of different type should not be equal");
    }

    @Test
    public void testEqualsDifferentNonce() {
        NonceAttribute differentNonceAttribute = new NonceAttribute();
        differentNonceAttribute.setNonce("differentNonce".getBytes());
        assertFalse(nonceAttribute.equals(differentNonceAttribute), "Attributes with different nonce values should not be equal");
    }

    @Test
    public void testEqualsSameNonce() {
        NonceAttribute sameNonceAttribute = new NonceAttribute();
        sameNonceAttribute.setNonce(nonceValue);
        assertTrue(nonceAttribute.equals(sameNonceAttribute), "Attributes with the same nonce values should be equal");
    }

    @Test
    public void testEqualsDifferentAttributeType() {
        NonceAttribute differentTypeAttribute = new NonceAttribute() {
            @Override
            public char getAttributeType() {
                return (char) (super.getAttributeType() + 1);
            }
        };
        differentTypeAttribute.setNonce(nonceValue);
        assertFalse(nonceAttribute.equals(differentTypeAttribute), "Attributes with different attribute types should not be equal");
    }

    @Test
    public void testEqualsDifferentDataLength() {
        NonceAttribute differentLengthAttribute = new NonceAttribute();
        differentLengthAttribute.setNonce("test".getBytes());
        assertFalse(nonceAttribute.equals(differentLengthAttribute), "Attributes with different data lengths should not be equal");
    }
}
