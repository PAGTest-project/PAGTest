
package org.openapitools.openapidiff.core;

import io.swagger.v3.parser.core.models.AuthorizationValue;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpenApiCompare_fromContentsTest {

    @Test
    public void testFromContentsWithAuths() {
        // Given
        String oldContent = "oldContent";
        String newContent = "newContent";
        List<AuthorizationValue> auths = Collections.emptyList();

        // When
        ChangedOpenApi result = OpenApiCompare.fromContents(oldContent, newContent, auths);

        // Then
        assertNotNull(result);
    }
}
