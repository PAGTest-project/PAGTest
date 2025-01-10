package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.media.Schema;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ChangedSchema_setMissingPropertiesTest {

  @Test
  public void testSetMissingProperties() {
    // Given
    ChangedSchema changedSchema = new ChangedSchema();
    Map<String, Schema<?>> missingProperties = new HashMap<>();
    missingProperties.put("property1", new Schema<>().example("example1"));
    missingProperties.put("property2", new Schema<>().example("example2"));

    // When
    ChangedSchema result = changedSchema.setMissingProperties(missingProperties);

    // Then
    assertEquals(missingProperties, changedSchema.getMissingProperties());
    assertEquals(changedSchema, result);
  }
}
