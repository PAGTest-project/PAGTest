
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Field_cloneTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    @Test
    public void testClone() {
        // Given
        field.addArg(createArg("key1", "name1", 0));
        field.addArg(createArg("key2", "name2", 1));
        field.hVars.put("var1", new Var("var1", "value1", "jsType1"));
        field.hMsgs.put("msg1", new Msg("msg1", "message1"));

        // When
        Field clonedField = (Field) field.clone();

        // Then
        assertNotNull(clonedField);
        assertNotSame(field, clonedField);
        assertEquals(field.args.length, clonedField.args.length);
        for (int i = 0; i < field.args.length; i++) {
            assertEquals(field.args[i], clonedField.args[i]);
        }
        assertEquals(field.hVars.size(), clonedField.hVars.size());
        for (Map.Entry<String, Var> entry : field.hVars.entrySet()) {
            assertEquals(entry.getValue().value, clonedField.hVars.get(entry.getKey()).value);
        }
        assertEquals(field.hMsgs.size(), clonedField.hMsgs.size());
        for (Map.Entry<String, Msg> entry : field.hMsgs.entrySet()) {
            assertEquals(entry.getValue().message, clonedField.hMsgs.get(entry.getKey()).message);
        }
    }

    private Arg createArg(String key, String name, int position) {
        Arg arg = new Arg();
        arg.setKey(key);
        arg.setName(name);
        arg.setPosition(position);
        return arg;
    }

    private static class Var {
        private String name;
        private String value;
        private String jsType;

        public Var(String name, String value, String jsType) {
            this.name = name;
            this.value = value;
            this.jsType = jsType;
        }

        // Getters and setters (if needed)
    }

    private static class Msg {
        private String name;
        private String message;

        public Msg(String name, String message) {
            this.name = name;
            this.message = message;
        }

        // Getters and setters (if needed)
    }
}
