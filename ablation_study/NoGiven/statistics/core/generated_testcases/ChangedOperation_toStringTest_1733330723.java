
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOperation_toStringTest {

    @Test
    public void testToString() {
        Operation oldOperation = new Operation();
        oldOperation.setDescription("Old Description");
        Operation newOperation = new Operation();
        newOperation.setDescription("New Description");

        ChangedOperation changedOperation = new ChangedOperation("/path", PathItem.HttpMethod.GET, oldOperation, newOperation);
        changedOperation.setSummary(new ChangedMetadata("Old Summary", "New Summary", DiffResult.INCOMPATIBLE));
        changedOperation.setDescription(new ChangedMetadata("Old Description", "New Description", DiffResult.COMPATIBLE));
        changedOperation.setOperationId(new ChangedMetadata("Old OperationId", "New OperationId", DiffResult.NO_CHANGES));
        changedOperation.setDeprecated(true);
        changedOperation.setParameters(new ChangedParameters());
        changedOperation.setRequestBody(new ChangedRequestBody());
        changedOperation.setApiResponses(new ChangedApiResponse());
        changedOperation.setSecurityRequirements(new ChangedSecurityRequirements());
        changedOperation.setExtensions(new ChangedExtensions());

        String expected = "ChangedOperation(oldOperation=Operation{description='Old Description', summary='null', operationId='null', deprecated=false, externalDocs=null, parameters=null, requestBody=null, responses=null, callbacks=null, security=null, servers=null, extensions={}}, newOperation=Operation{description='New Description', summary='null', operationId='null', deprecated=false, externalDocs=null, parameters=null, requestBody=null, responses=null, callbacks=null, security=null, servers=null, extensions={}}, pathUrl=/path, httpMethod=GET, summary=ChangedMetadata{oldValue='Old Summary', newValue='New Summary', diffResult=INCOMPATIBLE}, description=ChangedMetadata{oldValue='Old Description', newValue='New Description', diffResult=COMPATIBLE}, operationId=ChangedMetadata{oldValue='Old OperationId', newValue='New OperationId', diffResult=NO_CHANGES}, deprecated=true, parameters=ChangedParameters{changed=false, increased=null, missing=null, changedParameters=[]}, requestBody=ChangedRequestBody{changed=false, oldRequestBody=null, newRequestBody=null, changeType=null}, apiResponses=ChangedApiResponse{changed=false, increased=null, missing=null, changedApiResponses=[]}, securityRequirements=ChangedSecurityRequirements{changed=false, increased=null, missing=null}, extensions=ChangedExtensions{changed=false, increased=null, missing=null})";

        assertEquals(expected, changedOperation.toString());
    }
}
