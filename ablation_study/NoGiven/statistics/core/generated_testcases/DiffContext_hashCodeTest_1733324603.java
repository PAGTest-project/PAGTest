
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.PathItem;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiffContext_hashCodeTest {

    @Test
    public void testHashCode() {
        // Given
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        DiffContext context = new DiffContext(options);
        context.setUrl("http://example.com");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "value1");
        context.setParameters(parameters);
        context.setMethod(PathItem.HttpMethod.GET);
        context.setRequired(true);
        context.setLeftAndRightUrls("http://left.com", "http://right.com");

        // When
        int hashCode = context.hashCode();

        // Then
        int expectedHashCode = new HashCodeBuilder(17, 37)
                .append("http://example.com")
                .append(parameters)
                .append(PathItem.HttpMethod.GET)
                .append(false) // response
                .append(true)  // request
                .append(true)  // required
                .append("http://left.com")
                .append("http://right.com")
                .toHashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}
