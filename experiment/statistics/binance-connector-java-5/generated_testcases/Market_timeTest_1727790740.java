
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ProxyAuth;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Market_timeTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Market market;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.market = new Market(baseUrl, "apiKey", true, new ProxyAuth(null, null));
    }

    @Test
    public void testTime() {
        String path = "/api/v3/time";

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = market.time();
        String expectedResponse = "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}";
        assertEquals(expectedResponse, result);
    }
}
