
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.ice4j.message.MessageFactory;
import org.ice4j.message.Response;

public class MessageFactory_createCreatePermissionResponseTest {

    @Test
    public void testCreateCreatePermissionResponse() {
        Response response = MessageFactory.createCreatePermissionResponse();
        
        assertNotNull(response);
        assertEquals(Message.CREATEPERMISSION_RESPONSE, response.getMessageType());
    }
}
