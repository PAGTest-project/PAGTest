
package org.openapitools.openapidiff.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.openapitools.openapidiff.core.model.Endpoint;
import org.openapitools.openapidiff.core.model.ChangedOperation;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;
import org.openapitools.openapidiff.core.utils.EndpointUtils;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Operation;
import java.util.Arrays;
import java.util.List;

public class ChangedOpenApi_getDeprecatedEndpointsTest {

    private ChangedOpenApi changedOpenApi;

    @BeforeEach
    public void setUp() {
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        changedOpenApi = new ChangedOpenApi(options);
    }

    @Test
    public void testGetDeprecatedEndpoints_NoDeprecatedOperations() {
        ChangedOperation op1 = new ChangedOperation("path1", PathItem.HttpMethod.GET, new Operation(), new Operation());
        op1.setDeprecated(false);
        ChangedOperation op2 = new ChangedOperation("path2", PathItem.HttpMethod.POST, new Operation(), new Operation());
        op2.setDeprecated(false);

        changedOpenApi.setChangedOperations(Arrays.asList(op1, op2));

        List<Endpoint> deprecatedEndpoints = changedOpenApi.getDeprecatedEndpoints();
        assertThat(deprecatedEndpoints).isEmpty();
    }

    @Test
    public void testGetDeprecatedEndpoints_WithDeprecatedOperations() {
        ChangedOperation op1 = new ChangedOperation("path1", PathItem.HttpMethod.GET, new Operation(), new Operation());
        op1.setDeprecated(true);
        ChangedOperation op2 = new ChangedOperation("path2", PathItem.HttpMethod.POST, new Operation(), new Operation());
        op2.setDeprecated(false);

        changedOpenApi.setChangedOperations(Arrays.asList(op1, op2));

        List<Endpoint> deprecatedEndpoints = changedOpenApi.getDeprecatedEndpoints();
        assertThat(deprecatedEndpoints).hasSize(1);
        assertThat(deprecatedEndpoints.get(0).getPathUrl()).isEqualTo("path1");
        assertThat(deprecatedEndpoints.get(0).getMethod()).isEqualTo(PathItem.HttpMethod.GET);
    }

    @Test
    public void testGetDeprecatedEndpoints_AllDeprecatedOperations() {
        ChangedOperation op1 = new ChangedOperation("path1", PathItem.HttpMethod.GET, new Operation(), new Operation());
        op1.setDeprecated(true);
        ChangedOperation op2 = new ChangedOperation("path2", PathItem.HttpMethod.POST, new Operation(), new Operation());
        op2.setDeprecated(true);

        changedOpenApi.setChangedOperations(Arrays.asList(op1, op2));

        List<Endpoint> deprecatedEndpoints = changedOpenApi.getDeprecatedEndpoints();
        assertThat(deprecatedEndpoints).hasSize(2);
        assertThat(deprecatedEndpoints.get(0).getPathUrl()).isEqualTo("path1");
        assertThat(deprecatedEndpoints.get(0).getMethod()).isEqualTo(PathItem.HttpMethod.GET);
        assertThat(deprecatedEndpoints.get(1).getPathUrl()).isEqualTo("path2");
        assertThat(deprecatedEndpoints.get(1).getMethod()).isEqualTo(PathItem.HttpMethod.POST);
    }
}
