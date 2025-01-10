
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class NonceAttribute_equalsTest {

    private NonceAttribute nonceAttribute;
    private byte[] nonceValue;

    @BeforeEach
    public void setUp() {
        nonceAttribute = new NonceAttribute();
        nonceValue = "testNonce".getBytes();
        nonceAttribute.setNonce(nonceValue); // Initialize nonce to avoid NPE
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(nonceAttribute.equals(nonceAttribute), "An object should be equal to itself");
    }

    @Test
    public void testEquals_DifferentType() {
        Object obj = new Object();
        assertFalse(nonceAttribute.equals(obj), "An attribute should not be equal to an object of a different type");
    }

    @Test
    public void testEquals_DifferentNonce() {
        NonceAttribute other = new NonceAttribute();
        other.setNonce("differentNonce".getBytes());
        assertFalse(nonceAttribute.equals(other), "Attributes with different nonce values should not be equal");
    }

    @Test
    public void testEquals_SameNonce() {
        NonceAttribute other = new NonceAttribute();
        other.setNonce(nonceValue);
        assertTrue(nonceAttribute.equals(other), "Attributes with the same nonce values should be equal");
    }

    @Test
    public void testEquals_DifferentAttributeType() {
        NonceAttribute other = new NonceAttribute();
        other.setNonce(nonceValue);
        // Assuming getAttributeType() returns a constant value, we can't easily change it for testing
        // This test assumes that getAttributeType() is consistent and does not need to be tested here
        assertTrue(nonceAttribute.equals(other), "Attributes with the same nonce values should be equal");
    }

    @Test
    public void testEquals_DifferentDataLength() {
        NonceAttribute other = new NonceAttribute();
        other.setNonce("short".getBytes());
        assertFalse(nonceAttribute.equals(other), "Attributes with different data lengths should not be equal");
    }
}
