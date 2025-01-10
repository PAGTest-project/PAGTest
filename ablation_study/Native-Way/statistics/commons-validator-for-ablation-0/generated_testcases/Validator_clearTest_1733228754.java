
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class Validator_clearTest {

    @Test
    public void testClear() {
        Validator validator = new Validator(new ValidatorResources(), "formName", "fieldName");
        validator.setPage(1);
        validator.setParameter("param1", new Object());

        validator.clear();

        assertNull(validator.getFormName());
        assertNull(validator.getFieldName());
        assertTrue(validator.getParameterValue("param1") == null);
        assertEquals(0, validator.getPage());
    }
}
