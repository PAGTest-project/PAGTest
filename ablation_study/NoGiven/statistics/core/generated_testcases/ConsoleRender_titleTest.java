
package org.openapitools.openapidiff.core.output;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleRender_titleTest {

    @Test
    public void testTitle() {
        ConsoleRender consoleRender = new ConsoleRender();
        String expected = String.format(
            "%s--%s--%n%s",
            StringUtils.repeat('-', 74) + System.lineSeparator(),
            StringUtils.center("Test Title", 70),
            StringUtils.repeat('-', 74) + System.lineSeparator()
        );
        String actual = consoleRender.title("Test Title", '-');
        assertEquals(expected, actual);
    }
}
