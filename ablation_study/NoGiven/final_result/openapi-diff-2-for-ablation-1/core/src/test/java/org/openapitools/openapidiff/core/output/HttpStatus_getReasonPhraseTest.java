package org.openapitools.openapidiff.core.output;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HttpStatus_getReasonPhraseTest {

  @Test
  void testGetReasonPhrase_ValidStatusCode() {
    assertEquals("OK", HttpStatus.getReasonPhrase(200));
  }

  @Test
  void testGetReasonPhrase_NegativeStatusCode() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              HttpStatus.getReasonPhrase(-1);
            });
    assertEquals("status code may not be negative", exception.getMessage());
  }
}
