
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Field_getMsgTest {
    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field();
    }

    @Test
    public void testGetMsgWithExistingKey() {
        Msg msg = new Msg();
        msg.setKey("testKey");
        field.addMsg(msg);

        assertEquals("testKey", field.getMsg("testKey"));
    }

    @Test
    public void testGetMsgWithNonExistingKey() {
        assertNull(field.getMsg("nonExistingKey"));
    }
}
