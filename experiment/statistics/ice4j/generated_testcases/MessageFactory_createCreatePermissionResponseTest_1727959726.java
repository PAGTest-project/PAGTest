
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.ice4j.Message;

public class MessageFactory_createCreatePermissionResponseTest {

    @Test
    public void testCreateCreatePermissionResponse() {
        Response response = MessageFactory.createCreatePermissionResponse();
        assertEquals(Message.CREATEPERMISSION_RESPONSE, response.getMessageType());
    }
}
