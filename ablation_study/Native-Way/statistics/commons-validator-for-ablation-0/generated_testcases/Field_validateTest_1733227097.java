
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

public class Field_validateTest {

    @Test
    public void testValidate_NoDepends() throws ValidatorException {
        Field field = new Field();
        Map<String, Object> params = new HashMap<>();
        Map<String, ValidatorAction> actions = new HashMap<>();

        ValidatorResults results = field.validate(params, actions);

        assertEquals(0, results.getPropertyNames().size());
    }

    @Test
    public void testValidate_WithDepends_ActionMissing() throws ValidatorException {
        Field field = Mockito.spy(new Field());
        field.setDepends("action1");
        Map<String, Object> params = new HashMap<>();
        Map<String, ValidatorAction> actions = new HashMap<>();

        ValidatorResults results = field.validate(params, actions);

        assertEquals(0, results.getPropertyNames().size());
        verify(field, times(1)).handleMissingAction("action1");
    }

    @Test
    public void testValidate_WithDepends_ActionPresent_ValidationFails() throws ValidatorException {
        Field field = new Field();
        field.setDepends("action1");
        Map<String, Object> params = new HashMap<>();
        Map<String, ValidatorAction> actions = new HashMap<>();
        ValidatorAction action = mock(ValidatorAction.class);
        actions.put("action1", action);

        when(action.executeValidationMethod(any(), any(), any(), anyInt())).thenReturn(false);

        ValidatorResults results = field.validate(params, actions);

        assertEquals(1, results.getPropertyNames().size());
    }

    @Test
    public void testValidate_WithDepends_ActionPresent_ValidationSucceeds() throws ValidatorException {
        Field field = new Field();
        field.setDepends("action1");
        Map<String, Object> params = new HashMap<>();
        Map<String, ValidatorAction> actions = new HashMap<>();
        ValidatorAction action = mock(ValidatorAction.class);
        actions.put("action1", action);

        when(action.executeValidationMethod(any(), any(), any(), anyInt())).thenReturn(true);

        ValidatorResults results = field.validate(params, actions);

        assertEquals(1, results.getPropertyNames().size());
    }
}
