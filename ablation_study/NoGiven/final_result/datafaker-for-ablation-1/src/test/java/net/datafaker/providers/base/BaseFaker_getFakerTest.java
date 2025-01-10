
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BaseFaker_getFakerTest {

    private BaseFaker baseFaker;

    @BeforeEach
    void setupEnvironment() {
        baseFaker = new BaseFaker();
    }

    @Test
    void testGetFaker() {
        BaseFaker result = baseFaker.getFaker();
        assertTrue(result instanceof BaseFaker);
        assertEquals(baseFaker, result);
    }
}
