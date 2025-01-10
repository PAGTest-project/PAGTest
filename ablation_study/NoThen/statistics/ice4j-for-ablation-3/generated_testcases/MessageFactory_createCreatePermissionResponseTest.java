
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageFactory_createCreatePermissionResponseTest {

    @Test
    public void testCreateCreatePermissionResponse() {
        Response response = MessageFactory.createCreatePermissionResponse();
        assertEquals(Message.CREATEPERMISSION_RESPONSE, response.getMessageType());
    }
}
