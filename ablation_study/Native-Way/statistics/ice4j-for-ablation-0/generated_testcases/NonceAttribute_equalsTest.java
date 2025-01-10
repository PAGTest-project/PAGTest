
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NonceAttribute_equalsTest {

    private NonceAttribute nonceAttribute;

    @BeforeEach
    public void setUp() {
        nonceAttribute = new NonceAttribute();
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(nonceAttribute.equals(nonceAttribute), "An object should be equal to itself");
    }

    @Test
    public void testEqualsDifferentClass() {
        Object obj = new Object();
        assertFalse(nonceAttribute.equals(obj), "An object of different class should not be equal");
    }

    @Test
    public void testEqualsDifferentAttributeType() {
        NonceAttribute other = new NonceAttribute() {
            @Override
            public char getAttributeType() {
                return (char) (super.getAttributeType() + 1);
            }
        };
        assertFalse(nonceAttribute.equals(other), "Attributes with different types should not be equal");
    }

    @Test
    public void testEqualsDifferentDataLength() {
        NonceAttribute other = new NonceAttribute();
        other.setNonce(new byte[10]);
        nonceAttribute.setNonce(new byte[5]);
        assertFalse(nonceAttribute.equals(other), "Attributes with different data lengths should not be equal");
    }

    @Test
    public void testEqualsDifferentNonce() {
        NonceAttribute other = new NonceAttribute();
        other.setNonce(new byte[]{1, 2, 3});
        nonceAttribute.setNonce(new byte[]{4, 5, 6});
        assertFalse(nonceAttribute.equals(other), "Attributes with different nonce values should not be equal");
    }

    @Test
    public void testEqualsIdenticalAttributes() {
        NonceAttribute other = new NonceAttribute();
        byte[] nonce = new byte[]{1, 2, 3};
        other.setNonce(nonce);
        nonceAttribute.setNonce(nonce);
        assertTrue(nonceAttribute.equals(other), "Identical attributes should be equal");
    }
}
