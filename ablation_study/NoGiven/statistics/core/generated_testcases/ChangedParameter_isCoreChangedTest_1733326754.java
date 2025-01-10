
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class ChangedParameter_isCoreChangedTest {

    private ChangedParameter changedParameter;
    private DiffContext context;
    private Parameter oldParameter;
    private Parameter newParameter;

    @BeforeEach
    public void setUp() {
        context = mock(DiffContext.class);
        oldParameter = mock(Parameter.class);
        newParameter = mock(Parameter.class);
        changedParameter = new ChangedParameter("paramName", "in", context);
        changedParameter.setOldParameter(oldParameter);
        changedParameter.setNewParameter(newParameter);
    }

    @Test
    public void testIsCoreChanged_NoChanges() {
        // Given
        changedParameter.setChangeRequired(false);
        changedParameter.setDeprecated(false);
        changedParameter.setChangeAllowEmptyValue(false);
        changedParameter.setChangeStyle(false);
        changedParameter.setChangeExplode(false);

        // When
        DiffResult result = changedParameter.isCoreChanged();

        // Then
        assertEquals(DiffResult.NO_CHANGES, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible_AllowEmptyValueDecreased() {
        // Given
        changedParameter.setChangeRequired(false);
        changedParameter.setDeprecated(false);
        changedParameter.setChangeAllowEmptyValue(true);
        changedParameter.setChangeStyle(false);
        changedParameter.setChangeExplode(false);
        when(newParameter.getAllowEmptyValue()).thenReturn(false);
        when(REQUEST_PARAM_ALLOWEMPTY_DECREASED.enabled(context)).thenReturn(true);

        // When
        DiffResult result = changedParameter.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible_ExplodeChanged() {
        // Given
        changedParameter.setChangeRequired(false);
        changedParameter.setDeprecated(false);
        changedParameter.setChangeAllowEmptyValue(false);
        changedParameter.setChangeStyle(false);
        changedParameter.setChangeExplode(true);
        when(REQUEST_PARAM_EXPLODE_CHANGED.enabled(context)).thenReturn(true);

        // When
        DiffResult result = changedParameter.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible_RequiredIncreased() {
        // Given
        changedParameter.setChangeRequired(true);
        changedParameter.setDeprecated(false);
        changedParameter.setChangeAllowEmptyValue(false);
        changedParameter.setChangeStyle(false);
        changedParameter.setChangeExplode(false);
        when(oldParameter.getRequired()).thenReturn(false);
        when(REQUEST_PARAMS_REQUIRED_INCREASED.enabled(context)).thenReturn(true);

        // When
        DiffResult result = changedParameter.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Incompatible_StyleChanged() {
        // Given
        changedParameter.setChangeRequired(false);
        changedParameter.setDeprecated(false);
        changedParameter.setChangeAllowEmptyValue(false);
        changedParameter.setChangeStyle(true);
        changedParameter.setChangeExplode(false);
        when(REQUEST_PARAM_STYLE_CHANGED.enabled(context)).thenReturn(true);

        // When
        DiffResult result = changedParameter.isCoreChanged();

        // Then
        assertEquals(DiffResult.INCOMPATIBLE, result);
    }

    @Test
    public void testIsCoreChanged_Compatible() {
        // Given
        changedParameter.setChangeRequired(true);
        changedParameter.setDeprecated(false);
        changedParameter.setChangeAllowEmptyValue(true);
        changedParameter.setChangeStyle(true);
        changedParameter.setChangeExplode(true);
        when(oldParameter.getRequired()).thenReturn(true);
        when(newParameter.getAllowEmptyValue()).thenReturn(true);
        when(REQUEST_PARAM_ALLOWEMPTY_DECREASED.enabled(context)).thenReturn(false);
        when(REQUEST_PARAM_EXPLODE_CHANGED.enabled(context)).thenReturn(false);
        when(REQUEST_PARAMS_REQUIRED_INCREASED.enabled(context)).thenReturn(false);
        when(REQUEST_PARAM_STYLE_CHANGED.enabled(context)).thenReturn(false);

        // When
        DiffResult result = changedParameter.isCoreChanged();

        // Then
        assertEquals(DiffResult.COMPATIBLE, result);
    }
}
