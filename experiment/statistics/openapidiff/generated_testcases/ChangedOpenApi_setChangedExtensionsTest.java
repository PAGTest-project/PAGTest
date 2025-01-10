
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.apache.commons.configuration2.CompositeConfiguration;

public class ChangedOpenApi_setChangedExtensionsTest {

    private ChangedOpenApi changedOpenApi;
    private ChangedExtensions changedExtensions;

    @BeforeEach
    public void setUp() {
        CompositeConfiguration config = new CompositeConfiguration();
        OpenApiDiffOptions options = OpenApiDiffOptions.fromConfig(config);
        changedOpenApi = new ChangedOpenApi(options);
        changedExtensions = new ChangedExtensions(null, null, null);
    }

    @Test
    public void testSetChangedExtensions() {
        ChangedOpenApi result = changedOpenApi.setChangedExtensions(changedExtensions);
        assertEquals(changedExtensions, changedOpenApi.getChangedExtensions());
        assertEquals(changedOpenApi, result);
    }
}
