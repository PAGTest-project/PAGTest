
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedResponse_equalsTest {

    private ChangedResponse changedResponse;
    private ApiResponse oldApiResponse;
    private ApiResponse newApiResponse;
    private DiffContext context;

    @BeforeEach
    public void setUp() {
        oldApiResponse = new ApiResponse().description("Old Response");
        newApiResponse = new ApiResponse().description("New Response");
        context = new DiffContext(new OpenApiDiffOptions());
        changedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
    }

    @Test
    public void testEquals_SameInstance() {
        assertTrue(changedResponse.equals(changedResponse));
    }

    @Test
    public void testEquals_NullObject() {
        assertFalse(changedResponse.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        assertFalse(changedResponse.equals(new Object()));
    }

    @Test
    public void testEquals_DifferentOldApiResponse() {
        ApiResponse differentOldApiResponse = new ApiResponse().description("Different Old Response");
        ChangedResponse differentChangedResponse = new ChangedResponse(differentOldApiResponse, newApiResponse, context);
        assertFalse(changedResponse.equals(differentChangedResponse));
    }

    @Test
    public void testEquals_DifferentNewApiResponse() {
        ApiResponse differentNewApiResponse = new ApiResponse().description("Different New Response");
        ChangedResponse differentChangedResponse = new ChangedResponse(oldApiResponse, differentNewApiResponse, context);
        assertFalse(changedResponse.equals(differentChangedResponse));
    }

    @Test
    public void testEquals_DifferentContext() {
        DiffContext differentContext = new DiffContext(new OpenApiDiffOptions());
        ChangedResponse differentChangedResponse = new ChangedResponse(oldApiResponse, newApiResponse, differentContext);
        assertFalse(changedResponse.equals(differentChangedResponse));
    }

    @Test
    public void testEquals_DifferentDescription() {
        ChangedMetadata differentDescription = new ChangedMetadata().setLeft("Different Description");
        changedResponse.setDescription(differentDescription);
        ChangedResponse differentChangedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        differentChangedResponse.setDescription(new ChangedMetadata().setLeft("Another Description"));
        assertFalse(changedResponse.equals(differentChangedResponse));
    }

    @Test
    public void testEquals_DifferentHeaders() {
        ChangedHeaders differentHeaders = new ChangedHeaders(null, null, context);
        changedResponse.setHeaders(differentHeaders);
        ChangedResponse differentChangedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        differentChangedResponse.setHeaders(new ChangedHeaders(null, null, context));
        assertFalse(changedResponse.equals(differentChangedResponse));
    }

    @Test
    public void testEquals_DifferentContent() {
        ChangedContent differentContent = new ChangedContent(null, null, context);
        changedResponse.setContent(differentContent);
        ChangedResponse differentChangedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        differentChangedResponse.setContent(new ChangedContent(null, null, context));
        assertFalse(changedResponse.equals(differentChangedResponse));
    }

    @Test
    public void testEquals_DifferentExtensions() {
        ChangedExtensions differentExtensions = new ChangedExtensions(null, null, context);
        changedResponse.setExtensions(differentExtensions);
        ChangedResponse differentChangedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        differentChangedResponse.setExtensions(new ChangedExtensions(null, null, context));
        assertFalse(changedResponse.equals(differentChangedResponse));
    }

    @Test
    public void testEquals_SameFields() {
        ChangedResponse sameChangedResponse = new ChangedResponse(oldApiResponse, newApiResponse, context);
        assertTrue(changedResponse.equals(sameChangedResponse));
    }
}
