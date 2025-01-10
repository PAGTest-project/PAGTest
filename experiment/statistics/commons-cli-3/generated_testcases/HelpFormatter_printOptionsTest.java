
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        // Given
        Options options = new Options();
        options.addOption("a", "alpha", false, "Option alpha");
        options.addOption("b", "beta", true, "Option beta with argument");

        PrintWriter pw = new PrintWriter(sw);

        // When
        formatter.printOptions(pw, HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);
        pw.flush();

        // Then
        String expected = "  -a,--alpha       Option alpha\n" +
                          "  -b,--beta <arg>  Option beta with argument\n";
        assertEquals(expected, sw.toString());
    }
}
