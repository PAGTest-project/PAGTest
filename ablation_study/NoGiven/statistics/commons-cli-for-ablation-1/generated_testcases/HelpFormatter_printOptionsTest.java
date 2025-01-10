
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HelpFormatter_printOptionsTest {
    private HelpFormatter formatter;
    private StringWriter sw;

    @BeforeEach
    public void setUp() {
        formatter = new HelpFormatter();
        sw = new StringWriter();
    }

    @Test
    public void testPrintOptions() {
        final Options options = new Options();
        options.addOption("h", "help", false, "This is a looooong description");

        formatter.printOptions(new PrintWriter(sw), 20, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);

        final String expected = " -h,--help   This is" + System.lineSeparator() +
                                "             a" + System.lineSeparator() +
                                "             looooon" + System.lineSeparator() +
                                "             g" + System.lineSeparator() +
                                "             descrip" + System.lineSeparator() +
                                "             tion" + System.lineSeparator();

        assertEquals(expected, sw.toString().replaceAll("\\r\\n", "\n"));
    }
}
