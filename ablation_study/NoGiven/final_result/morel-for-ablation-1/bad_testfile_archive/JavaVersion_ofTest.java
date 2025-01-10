
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JavaVersion_ofTest {

    @Test
    void testOfWithSingleComponent() {
        JavaVersion version = JavaVersion.of(1);
        assertEquals(1, version.components.get(0));
    }

    @Test
    void testOfWithMultipleComponents() {
        JavaVersion version = JavaVersion.of(1, 8, 0);
        assertEquals(1, version.components.get(0));
        assertEquals(8, version.components.get(1));
        assertEquals(0, version.components.get(2));
    }

    @Test
    void testOfWithJDK1_8Transformation() {
        JavaVersion version = JavaVersion.of(1, 8, 0);
        assertEquals(8, version.components.get(0));
        assertEquals(0, version.components.get(1));
    }
}
