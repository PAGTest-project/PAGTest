package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.headers.Header;
import org.junit.jupiter.api.Test;

public class ChangedHeader_equalsTest {

  @Test
  public void testEquals_SameInstance() {
    Header oldHeader = new Header();
    Header newHeader = new Header();
    DiffContext context = new DiffContext(null);
    ChangedHeader changedHeader = new ChangedHeader(oldHeader, newHeader, context);

    assertTrue(changedHeader.equals(changedHeader));
  }

  @Test
  public void testEquals_NullObject() {
    Header oldHeader = new Header();
    Header newHeader = new Header();
    DiffContext context = new DiffContext(null);
    ChangedHeader changedHeader = new ChangedHeader(oldHeader, newHeader, context);

    assertFalse(changedHeader.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    Header oldHeader = new Header();
    Header newHeader = new Header();
    DiffContext context = new DiffContext(null);
    ChangedHeader changedHeader = new ChangedHeader(oldHeader, newHeader, context);

    assertFalse(changedHeader.equals(new Object()));
  }

  @Test
  public void testEquals_DifferentFields() {
    Header oldHeader = new Header();
    Header newHeader = new Header();
    DiffContext context = new DiffContext(null);
    ChangedHeader changedHeader1 =
        new ChangedHeader(oldHeader, newHeader, context)
            .setRequired(true)
            .setDeprecated(false)
            .setStyle(true)
            .setExplode(false);

    ChangedHeader changedHeader2 =
        new ChangedHeader(oldHeader, newHeader, context)
            .setRequired(false)
            .setDeprecated(true)
            .setStyle(false)
            .setExplode(true);

    assertFalse(changedHeader1.equals(changedHeader2));
  }

  @Test
  public void testEquals_SameFields() {
    Header oldHeader = new Header();
    Header newHeader = new Header();
    DiffContext context = new DiffContext(null);
    ChangedHeader changedHeader1 =
        new ChangedHeader(oldHeader, newHeader, context)
            .setRequired(true)
            .setDeprecated(false)
            .setStyle(true)
            .setExplode(false);

    ChangedHeader changedHeader2 =
        new ChangedHeader(oldHeader, newHeader, context)
            .setRequired(true)
            .setDeprecated(false)
            .setStyle(true)
            .setExplode(false);

    assertTrue(changedHeader1.equals(changedHeader2));
  }
}
