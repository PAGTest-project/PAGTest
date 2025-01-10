package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.swagger.v3.oas.models.media.Schema;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ChangedSchema_setIncreasedPropertiesTest {

  @Test
  public void testSetIncreasedProperties() {
    // Given
    ChangedSchema changedSchema = new ChangedSchema();
    Map<String, Schema<?>> increasedProperties = new HashMap<>();
    increasedProperties.put("property1", new Schema<>().description("description1"));

    // When
    ChangedSchema result = changedSchema.setIncreasedProperties(increasedProperties);

    // Then
    assertNotNull(result);
    assertEquals(increasedProperties, result.getIncreasedProperties());
  }
}
