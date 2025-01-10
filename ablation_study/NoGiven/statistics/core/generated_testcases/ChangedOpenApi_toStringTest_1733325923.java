
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedOpenApi_toStringTest {

    @Test
    public void testToString() {
        OpenAPI oldSpec = new OpenAPI();
        oldSpec.setInfo(new io.swagger.v3.oas.models.info.Info().title("Old Spec"));

        OpenAPI newSpec = new OpenAPI();
        newSpec.setInfo(new io.swagger.v3.oas.models.info.Info().title("New Spec"));

        ChangedOpenApi changedOpenApi = new ChangedOpenApi(new OpenApiDiffOptions());
        changedOpenApi.setOldSpecOpenApi(oldSpec)
                      .setNewSpecOpenApi(newSpec)
                      .setNewEndpoints(List.of(new Endpoint()))
                      .setMissingEndpoints(List.of(new Endpoint()))
                      .setChangedOperations(List.of(new ChangedOperation()))
                      .setChangedExtensions(new ChangedExtensions());

        String expected = "ChangedOpenApi(oldSpecOpenApi=OpenAPI{info=Info{title='Old Spec', description='null', termsOfService='null', contact=null, license=null, version='null'}, servers=null, paths=null, components=null, security=null, tags=null, externalDocs=null}, newSpecOpenApi=OpenAPI{info=Info{title='New Spec', description='null', termsOfService='null', contact=null, license=null, version='null'}, servers=null, paths=null, components=null, security=null, tags=null, externalDocs=null}, newEndpoints=[Endpoint{path='null', method='null', operation=null}], missingEndpoints=[Endpoint{path='null', method='null', operation=null}], changedOperations=[ChangedOperation{pathUrl='null', httpMethod='null', newOperation=null, oldOperation=null, changes=null, deprecated=false}], changedExtensions=ChangedExtensions{extensions=null})";

        assertEquals(expected, changedOpenApi.toString());
    }
}
