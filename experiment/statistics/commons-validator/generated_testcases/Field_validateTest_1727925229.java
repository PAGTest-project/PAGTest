
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class Field_validateTest {

    private Field field;
    private Map<String, Object> params;
    private Map<String, ValidatorAction> actions;

    @BeforeEach
    public void setUp() {
        field = new Field();
        params = new HashMap<>();
        actions = new HashMap<>();
    }

    @Test
    public void testValidateNoDepends() throws ValidatorException {
        field.setDepends(null);
        ValidatorResults results = field.validate(params, actions);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testValidateWithDepends() throws ValidatorException {
        field.setDepends("required");
        ValidatorAction requiredAction = new ValidatorAction();
        requiredAction.setName("required");
        actions.put("required", requiredAction);

        ValidatorResults results = field.validate(params, actions);
        assertFalse(results.isEmpty());
    }

    @Test
    public void testValidateMissingAction() {
        field.setDepends("missingAction");
        assertThrows(ValidatorException.class, () -> field.validate(params, actions));
    }

    @Test
    public void testValidateIndexedProperty() throws ValidatorException {
        field.setIndexedListProperty("names");
        params.put(Validator.BEAN_PARAM, new NameBean());

        ValidatorResults results = field.validate(params, actions);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testValidateIndexedPropertySize() throws ValidatorException {
        field.setIndexedListProperty("names");
        NameBean bean = new NameBean();
        bean.setNames(new String[]{"John", "Doe"});
        params.put(Validator.BEAN_PARAM, bean);

        ValidatorResults results = field.validate(params, actions);
        assertFalse(results.isEmpty());
    }

    @Test
    public void testValidateIndexedPropertySizeZero() throws ValidatorException {
        field.setIndexedListProperty("names");
        NameBean bean = new NameBean();
        bean.setNames(new String[]{});
        params.put(Validator.BEAN_PARAM, bean);

        ValidatorResults results = field.validate(params, actions);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testValidateIndexedPropertyNotIndexed() {
        field.setIndexedListProperty("name");
        NameBean bean = new NameBean();
        bean.setName("John");
        params.put(Validator.BEAN_PARAM, bean);

        assertThrows(ValidatorException.class, () -> field.validate(params, actions));
    }
}
