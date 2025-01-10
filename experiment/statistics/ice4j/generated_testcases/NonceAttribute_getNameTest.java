
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NonceAttribute_getNameTest {
    private NonceAttribute nonceAttribute;

    @BeforeEach
    public void setUp() {
        nonceAttribute = new NonceAttribute();
    }

    @Test
    public void testGetName() {
        assertEquals("NONCE", nonceAttribute.getName(), "getName() should return 'NONCE'");
    }
}
