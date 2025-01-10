
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.*;
import org.ice4j.attribute.*;
import org.junit.jupiter.api.*;

public class MessageFactory_createBindingErrorResponseTest {

    @Test
    public void testCreateBindingErrorResponseWithReasonPhrase() {
        char errorCode = ErrorCodeAttribute.UNKNOWN_ATTRIBUTE;
        String reasonPhrase = "Unknown attribute";
        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_ERROR_RESPONSE);
        ErrorCodeAttribute errorCodeAttribute = AttributeFactory.createErrorCodeAttribute(errorCode, reasonPhrase);
        expectedResponse.putAttribute(errorCodeAttribute);

        Response actualResponse = MessageFactory.createBindingErrorResponse(errorCode, reasonPhrase);
        assertEquals(expectedResponse.getMessageType(), actualResponse.getMessageType());
        assertEquals(expectedResponse.getAttribute(Attribute.ERROR_CODE), actualResponse.getAttribute(Attribute.ERROR_CODE));
    }

    @Test
    public void testCreateBindingErrorResponseWithoutReasonPhrase() {
        char errorCode = ErrorCodeAttribute.UNKNOWN_ATTRIBUTE;
        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_ERROR_RESPONSE);
        ErrorCodeAttribute errorCodeAttribute = AttributeFactory.createErrorCodeAttribute(errorCode, null);
        expectedResponse.putAttribute(errorCodeAttribute);

        Response actualResponse = MessageFactory.createBindingErrorResponse(errorCode);
        assertEquals(expectedResponse.getMessageType(), actualResponse.getMessageType());
        assertEquals(expectedResponse.getAttribute(Attribute.ERROR_CODE), actualResponse.getAttribute(Attribute.ERROR_CODE));
    }

    @Test
    public void testCreateBindingErrorResponseUnknownAttributes() {
        char[] unknownAttributes = {0x0001, 0x0002};
        Response expectedResponse = new Response();
        expectedResponse.setMessageType(Message.BINDING_ERROR_RESPONSE);
        ErrorCodeAttribute errorCodeAttribute = AttributeFactory.createErrorCodeAttribute(ErrorCodeAttribute.UNKNOWN_ATTRIBUTE, null);
        expectedResponse.putAttribute(errorCodeAttribute);
        UnknownAttributesAttribute unknownAttributesAttribute = AttributeFactory.createUnknownAttributesAttribute();
        for (char attr : unknownAttributes) {
            unknownAttributesAttribute.addAttributeID(attr);
        }
        expectedResponse.putAttribute(unknownAttributesAttribute);

        Response actualResponse = MessageFactory.createBindingErrorResponseUnknownAttributes(unknownAttributes);
        assertEquals(expectedResponse.getMessageType(), actualResponse.getMessageType());
        assertEquals(expectedResponse.getAttribute(Attribute.ERROR_CODE), actualResponse.getAttribute(Attribute.ERROR_CODE));
        assertEquals(expectedResponse.getAttribute(Attribute.UNKNOWN_ATTRIBUTES), actualResponse.getAttribute(Attribute.UNKNOWN_ATTRIBUTES));
    }
}
