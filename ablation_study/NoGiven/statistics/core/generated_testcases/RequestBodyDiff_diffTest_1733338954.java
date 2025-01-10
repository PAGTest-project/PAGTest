
package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.parameters.RequestBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;
import org.openapitools.openapidiff.core.model.ChangedRequestBody;

public class RequestBodyDiff_diffTest {

    private RequestBodyDiff requestBodyDiff;
    private RequestBody left;
    private RequestBody right;
    private DiffContext context;

    @BeforeEach
    public void setUp() {
        OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
        requestBodyDiff = new RequestBodyDiff(openApiDiff);
        left = mock(RequestBody.class);
        right = mock(RequestBody.class);
        context = mock(DiffContext.class);
    }

    @Test
    public void testDiffWithNonNullRequestBodies() {
        when(left.get$ref()).thenReturn("leftRef");
        when(right.get$ref()).thenReturn("rightRef");

        DeferredChanged<ChangedRequestBody> result = requestBodyDiff.diff(left, right, context);

        assertNotNull(result);
    }

    @Test
    public void testDiffWithNullLeftRequestBody() {
        when(right.get$ref()).thenReturn("rightRef");

        DeferredChanged<ChangedRequestBody> result = requestBodyDiff.diff(null, right, context);

        assertNotNull(result);
    }

    @Test
    public void testDiffWithNullRightRequestBody() {
        when(left.get$ref()).thenReturn("leftRef");

        DeferredChanged<ChangedRequestBody> result = requestBodyDiff.diff(left, null, context);

        assertNotNull(result);
    }

    @Test
    public void testDiffWithBothNullRequestBodies() {
        DeferredChanged<ChangedRequestBody> result = requestBodyDiff.diff(null, null, context);

        assertNotNull(result);
    }
}
