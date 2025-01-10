
package org.openapitools.openapidiff.core.output;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleRender_titleTest {

    @Test
    public void testTitle() {
        ConsoleRender consoleRender = new ConsoleRender();
        String expected = "==============================================================================\n" +
                          "--  Title Here  --\n" +
                          "==============================================================================\n";
        String actual = consoleRender.title("Title Here", '=');
        assertEquals(expected, actual);
    }
}
