package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedParameter_toStringTest {

  @Test
  public void testToString() {
    // Given
    DiffContext context = new DiffContext(new OpenApiDiffOptions());
    Parameter oldParameter = new Parameter().name("oldParam").required(true);
    Parameter newParameter = new Parameter().name("newParam").required(false);
    ChangedParameter changedParameter =
        new ChangedParameter("paramName", "query", context)
            .setOldParameter(oldParameter)
            .setNewParameter(newParameter)
            .setChangeRequired(true)
            .setDeprecated(false)
            .setChangeStyle(false)
            .setChangeExplode(false)
            .setChangeAllowEmptyValue(false)
            .setDescription(new ChangedMetadata())
            .setSchema(new ChangedSchema())
            .setContent(new ChangedContent(null, null, context))
            .setExtensions(new ChangedExtensions(null, null, context));

    // When
    String result = changedParameter.toString();

    // Then
    String expected =
        "ChangedParameter(context="
            + context
            + ", oldParameter="
            + oldParameter
            + ", newParameter="
            + newParameter
            + ", name=paramName, in=query"
            + ", changeRequired=true, deprecated=false, changeStyle=false, changeExplode=false, changeAllowEmptyValue=false"
            + ", description="
            + new ChangedMetadata()
            + ", schema="
            + new ChangedSchema()
            + ", content="
            + new ChangedContent(null, null, context)
            + ", extensions="
            + new ChangedExtensions(null, null, context)
            + ")";
    assertEquals(expected, result);
  }
}
