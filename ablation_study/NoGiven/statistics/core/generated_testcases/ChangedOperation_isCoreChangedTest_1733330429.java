
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ChangedOperation_isCoreChangedTest {

    @Test
    public void testIsCoreChanged_Deprecated() {
        ChangedOperation changedOperation = new ChangedOperation("path", PathItem.HttpMethod.GET, null, null);
        changedOperation.setDeprecated(true);
        assertEquals(DiffResult.COMPATIBLE, changedOperation.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_NotDeprecated() {
        ChangedOperation changedOperation = new ChangedOperation("path", PathItem.HttpMethod.GET, null, null);
        changedOperation.setDeprecated(false);
        assertEquals(DiffResult.NO_CHANGES, changedOperation.isCoreChanged());
    }
}
