
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.ProxyAuth;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Trade_getOpenOCOOrdersTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Trade trade;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        RequestHandler requestHandler = new RequestHandler(MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), new ProxyAuth(null, null));
        this.trade = new Trade(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, new ProxyAuth(null, null));
    }

    @Test
    public void testGetOpenOCOOrders() {
        String path = "/api/v3/openOrderList";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = trade.getOpenOCOOrders(parameters);
        String expectedResponse = "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}";
        assertEquals(expectedResponse, result);
    }
}
