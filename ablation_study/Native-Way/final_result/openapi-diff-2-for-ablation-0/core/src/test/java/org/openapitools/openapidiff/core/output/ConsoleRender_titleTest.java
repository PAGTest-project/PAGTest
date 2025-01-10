package org.openapitools.openapidiff.core.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConsoleRender_titleTest {

  @Test
  public void testTitle() {
    ConsoleRender consoleRender = new ConsoleRender();
    String expected =
        "==========================================================================\n"
            + "==                              Title Test                              ==\n"
            + "==========================================================================\n";
    String actual = consoleRender.title("Title Test", '=');
    assertEquals(expected, actual);
  }
}
