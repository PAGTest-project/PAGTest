
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.PathItem;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiffContext_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        DiffContext context = new DiffContext(new OpenApiDiffOptions());
        assertTrue(context.equals(context));
    }

    @Test
    public void testEquals_DifferentClass() {
        DiffContext context = new DiffContext(new OpenApiDiffOptions());
        assertFalse(context.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentState() {
        DiffContext context1 = new DiffContext(new OpenApiDiffOptions())
                .setUrl("url1")
                .setParameters(new HashMap<>(Map.of("param1", "value1")))
                .setMethod(PathItem.HttpMethod.GET)
                .setRequired(true);

        DiffContext context2 = new DiffContext(new OpenApiDiffOptions())
                .setUrl("url2")
                .setParameters(new HashMap<>(Map.of("param2", "value2")))
                .setMethod(PathItem.HttpMethod.POST)
                .setRequired(false);

        assertFalse(context1.equals(context2));
    }

    @Test
    public void testEquals_SameState() {
        DiffContext context1 = new DiffContext(new OpenApiDiffOptions())
                .setUrl("url")
                .setParameters(new HashMap<>(Map.of("param", "value")))
                .setMethod(PathItem.HttpMethod.GET)
                .setRequired(true);

        DiffContext context2 = new DiffContext(new OpenApiDiffOptions())
                .setUrl("url")
                .setParameters(new HashMap<>(Map.of("param", "value")))
                .setMethod(PathItem.HttpMethod.GET)
                .setRequired(true);

        assertTrue(context1.equals(context2));
    }
}
