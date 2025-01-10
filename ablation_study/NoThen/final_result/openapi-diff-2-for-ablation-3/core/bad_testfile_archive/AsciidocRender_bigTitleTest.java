package org.openapitools.openapidiff.core.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AsciidocRender_bigTitleTest {
  private AsciidocRender asciidocRender;

  @BeforeEach
  public void setUp() {
    asciidocRender = new AsciidocRender();
  }

  @Test
  public void testBigTitleWithValidInput() {
    String title = "OpenAPI Diff";
    String version = "1.0.0";
    String expected = "= OPENAPI DIFF (v 1.0.0)";
    assertEquals(expected, asciidocRender.bigTitle(title, version));
  }

  @Test
  public void testBigTitleWithEmptyTitle() {
    String title = "";
    String version = "1.0.0";
    String expected = "=  (v 1.0.0)";
    assertEquals(expected, asciidocRender.bigTitle(title, version));
  }

  @Test
  public void testBigTitleWithEmptyVersion() {
    String title = "OpenAPI Diff";
    String version = "";
    String expected = "= OPENAPI DIFF (v )";
    assertEquals(expected, asciidocRender.bigTitle(title, version));
  }

  @Test
  public void testBigTitleWithNullTitle() {
    String title = null;
    String version = "1.0.0";
    String expected = "= null (v 1.0.0)";
    assertEquals(expected, asciidocRender.bigTitle(title, version));
  }

  @Test
  public void testBigTitleWithNullVersion() {
    String title = "OpenAPI Diff";
    String version = null;
    String expected = "= OPENAPI DIFF (v null)";
    assertEquals(expected, asciidocRender.bigTitle(title, version));
  }
}
