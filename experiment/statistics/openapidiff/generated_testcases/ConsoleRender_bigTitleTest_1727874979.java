
package org.openapitools.openapidiff.core.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ConsoleRender_bigTitleTest {

    @Test
    public void testBigTitle() {
        ConsoleRender consoleRender = new ConsoleRender();
        String result = consoleRender.bigTitle("Test Title");
        assertEquals("======================================================================\n==        TEST TITLE        ==\n======================================================================\n", result);
    }
}
