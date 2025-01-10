package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.headers.Header;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class ChangedHeader_hashCodeTest {

  @Test
  public void testHashCode() {
    Header oldHeader = new Header();
    Header newHeader = new Header();
    DiffContext context = new DiffContext(null);
    ChangedHeader changedHeader = new ChangedHeader(oldHeader, newHeader, context);

    int expectedHashCode =
        Objects.hash(
            oldHeader, newHeader, context, false, false, false, false, null, null, null, null);

    assertEquals(expectedHashCode, changedHeader.hashCode());
  }
}
