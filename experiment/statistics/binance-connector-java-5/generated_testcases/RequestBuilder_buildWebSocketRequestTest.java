
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import okhttp3.Request;

public class RequestBuilder_buildWebSocketRequestTest {

    @Test
    public void testBuildWebSocketRequest() {
        String fullUrl = "ws://example.com/websocket";
        Request request = RequestBuilder.buildWebSocketRequest(fullUrl);
        assertEquals(fullUrl, request.url().toString());
    }
}
