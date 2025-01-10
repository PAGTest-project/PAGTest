
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedParameter_toStringTest {

    @Test
    public void testToString() {
        // Given
        DiffContext context = new DiffContext();
        Parameter oldParameter = new Parameter().name("oldParam").required(true);
        Parameter newParameter = new Parameter().name("newParam").required(false);
        ChangedParameter changedParameter = new ChangedParameter("paramName", "query", context)
                .setOldParameter(oldParameter)
                .setNewParameter(newParameter)
                .setChangeRequired(true)
                .setDeprecated(false)
                .setChangeStyle(false)
                .setChangeExplode(false)
                .setChangeAllowEmptyValue(false)
                .setDescription(new ChangedMetadata())
                .setSchema(new ChangedSchema())
                .setContent(new ChangedContent())
                .setExtensions(new ChangedExtensions());

        // When
        String result = changedParameter.toString();

        // Then
        String expected = "ChangedParameter(context=" + context
                + ", oldParameter=" + oldParameter
                + ", newParameter=" + newParameter
                + ", name=paramName, in=query"
                + ", changeRequired=true, deprecated=false, changeStyle=false, changeExplode=false, changeAllowEmptyValue=false"
                + ", description=" + new ChangedMetadata()
                + ", schema=" + new ChangedSchema()
                + ", content=" + new ChangedContent()
                + ", extensions=" + new ChangedExtensions()
                + ")";
        assertEquals(expected, result);
    }
}
