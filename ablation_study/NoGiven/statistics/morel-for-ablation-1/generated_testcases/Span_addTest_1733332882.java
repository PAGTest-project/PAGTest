
package net.hydromatic.morel.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import net.hydromatic.morel.ast.Pos;
import org.junit.jupiter.api.Test;

class Span_addTest {

  @Test
  void testAddWithParser() {
    // Given
    MorelParser parser = mock(MorelParser.class);
    Pos mockPos = new Pos("file", 1, 1, 1, 1);
    when(parser.pos()).thenReturn(mockPos);

    Span span = Span.of();

    // When
    span.add(parser);

    // Then
    assertEquals(mockPos, span.pos());
  }
}
