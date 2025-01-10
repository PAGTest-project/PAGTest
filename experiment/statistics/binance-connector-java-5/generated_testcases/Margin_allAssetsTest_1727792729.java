
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.RequestHandler;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Margin_allAssetsTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Margin margin;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        RequestHandler requestHandler = new RequestHandler(MockData.API_KEY, null, null);
        this.margin = new Margin(baseUrl, MockData.API_KEY, null, true, null);
    }

    @Test
    public void testAllAssets() {
        String path = "/sapi/v1/margin/allAssets";

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = margin.allAssets();
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
