
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ChangedOperation_hashCodeTest {

    @Test
    public void testHashCode() {
        Operation oldOp = new Operation();
        Operation newOp = new Operation();
        ChangedOperation changedOperation = new ChangedOperation("/path", PathItem.HttpMethod.GET, oldOp, newOp);

        int hashCode1 = changedOperation.hashCode();

        changedOperation.setSummary(new ChangedMetadata().setLeft("summary"));
        int hashCode2 = changedOperation.hashCode();

        // The hash codes should not be equal because the summary has changed
        assertNotEquals(hashCode1, hashCode2);
    }
}
