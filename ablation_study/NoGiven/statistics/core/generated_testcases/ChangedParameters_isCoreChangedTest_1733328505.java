
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ChangedParameters_isCoreChangedTest {

    private ChangedParameters changedParameters;
    private DiffContext context;
    private Parameter requiredParameter;
    private Parameter optionalParameter;

    @BeforeEach
    public void setUp() {
        context = mock(DiffContext.class);
        requiredParameter = new Parameter();
        requiredParameter.setRequired(true);
        optionalParameter = new Parameter();
        optionalParameter.setRequired(false);
        changedParameters = new ChangedParameters(Collections.emptyList(), Collections.emptyList(), context);
    }

    @Test
    public void testIsCoreChanged_NoChanges() {
        changedParameters.setIncreased(Collections.emptyList());
        changedParameters.setMissing(Collections.emptyList());
        assertEquals(DiffResult.NO_CHANGES, changedParameters.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_Incompatible_MissingParams() {
        when(REQUEST_PARAMS_DECREASED.enabled(context)).thenReturn(true);
        changedParameters.setIncreased(Collections.emptyList());
        changedParameters.setMissing(Arrays.asList(optionalParameter));
        assertEquals(DiffResult.INCOMPATIBLE, changedParameters.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_Incompatible_RequiredParamsIncreased() {
        when(REQUEST_PARAMS_REQUIRED_INCREASED.enabled(context)).thenReturn(true);
        changedParameters.setIncreased(Arrays.asList(requiredParameter));
        changedParameters.setMissing(Collections.emptyList());
        assertEquals(DiffResult.INCOMPATIBLE, changedParameters.isCoreChanged());
    }

    @Test
    public void testIsCoreChanged_Compatible() {
        when(REQUEST_PARAMS_DECREASED.enabled(context)).thenReturn(false);
        when(REQUEST_PARAMS_REQUIRED_INCREASED.enabled(context)).thenReturn(false);
        changedParameters.setIncreased(Arrays.asList(optionalParameter));
        changedParameters.setMissing(Collections.emptyList());
        assertEquals(DiffResult.COMPATIBLE, changedParameters.isCoreChanged());
    }
}
