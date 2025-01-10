
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

class FromBuilder_addAllTest {

    @Test
    void testAddAll() {
        FromBuilder fromBuilder = new FromBuilder(null, null);
        Core.FromStep step1 = new Core.FromStep() {
            @Override
            public void accept(Visitor visitor) {
                // Mock implementation
            }
        };
        Core.FromStep step2 = new Core.FromStep() {
            @Override
            public void accept(Visitor visitor) {
                // Mock implementation
            }
        };

        fromBuilder.addAll(Arrays.asList(step1, step2));

        assertEquals(2, fromBuilder.steps.size());
    }

    @Test
    void testAddAllEmpty() {
        FromBuilder fromBuilder = new FromBuilder(null, null);

        fromBuilder.addAll(Collections.emptyList());

        assertEquals(0, fromBuilder.steps.size());
    }
}
